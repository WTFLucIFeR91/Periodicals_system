package com.epam.web.command;

import com.epam.dao.DaoFactory;
import com.epam.entity.Publication;
import com.epam.entity.Role;
import com.epam.entity.Topic;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;


public class MainPageCommand implements Command {
    private static final Logger log = LogManager.getLogger(MainPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, ParseException {
        log.debug("MainPageCommand starts");
        HttpSession session = req.getSession();
        String address = Path.PAGE_ERROR;
        List<Publication> publications = null;

        List<Topic> topics = DaoFactory.createTopicDAO().findAllTopics();
        session.setAttribute("topics", topics);

        String isSearch = req.getParameter("isSearch");
        int page = 1;
        int recordsPerPage = 6;
        int noOfRecords = DaoFactory.createPublicationDAO().findAllPeriodicals().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        if (isSearch != null) {
            publications = DaoFactory.createPublicationDAO().findPublicationByName(req.getParameter("search"), (page - 1) * recordsPerPage, recordsPerPage);
            initAttribute(req, publications, page, noOfPages);
        } else {
            String sortName = req.getParameter("sortBy");
            if (sortName == null || sortName.isEmpty() || sortName.equals("type")) {
                publications = DaoFactory.createPublicationDAO().getPublicationByTopic((page - 1) * recordsPerPage, recordsPerPage);
                initAttribute(req, publications, page, noOfPages);

                session.setAttribute("sortName", "type");

            } else if (sortName.equals("name")) {
                List<Publication> allPublications = DaoFactory.createPublicationDAO().findAllPeriodicals();
                sortByName(allPublications);
                if (page == 1) {
                    publications = allPublications.subList((page - 1) * recordsPerPage, recordsPerPage);
                } else {
                    publications = allPublications.subList((page - 1) * recordsPerPage, (page) * recordsPerPage);
                }
                initAttribute(req, publications, page, noOfPages);

                session.setAttribute("sortName", "name");

            } else if (sortName.equals("price")) {
                publications = DaoFactory.createPublicationDAO().getPublicationByPrice((page - 1) * recordsPerPage, recordsPerPage);
                initAttribute(req, publications, page, noOfPages);

                session.setAttribute("sortName", "price");

            }
        }
        Role userRole = (Role) session.getAttribute("userRole");
        log.trace("userRole => " + userRole);

        if (userRole == null) {
            address = Path.PAGE_MAIN;
        } else if (userRole == Role.ADMIN) {
            address = Path.COMMAND_ADMIN_PAGE;
        } else if (userRole == Role.USER) {
            address = Path.COMMAND_CLIENT_PAGE;
        }
        log.trace("address => " + address);
        log.debug("MainPageCommand finished");
        return address;
    }

    private static void initAttribute(HttpServletRequest req, List<Publication> publications, int page, int noOfPages) {
        req.setAttribute("publications", publications);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
    }

    private List<Publication> sortByName(List<Publication> periodicals) {
        Collections.sort(periodicals, new Comparator<Publication>() {
            public static final String UkAlphabet =
                    "\u0410\u0411\u0412\u0413\u0490\u0414\u0415\u0404\u0416\u0417\u0418" +
                            "\u0406\u0407\u0419\u041a\u041b\u041c\u041d\u041e\u041f\u0420\u0421" +
                            "\u0422\u0423\u0424\u0425\u0426\u0427\u0428\u0429\u042c\u042e\u042f" +
                            "\u0430\u0431\u0432\u0433\u0491\u0434\u0435\u0454\u0436\u0437\u0438" +
                            "\u0456\u0457\u0439\u043a\u043b\u043c\u043d\u043e\u043f\u0440\u0441" +
                            "\u0442\u0443\u0444\u0445\u0446\u0447\u0448\u0449\u044c\u044e\u044f";

            @Override
            public int compare(Publication o1, Publication o2) {
                return compareStr(o1, o2);
            }

            public int compareStr(Publication o1, Publication o2) {

                String thisString = o1.getName();
                String anotherString = o2.getName();

                int len1 = thisString.length();
                int len2 = anotherString.length();
                int n = Math.min(len1, len2);

                char v1[] = thisString.toCharArray();
                char v2[] = anotherString.toCharArray();

                int i = 0;
                int j = 0;

                int k = i;
                int lim = n + i;
                int p1 = -1, p2 = -1;
                while (k < lim) {
                    char c1 = v1[k];
                    char c2 = v2[k];
                    if (c1 != c2) {
                        if ((p1 = UkAlphabet.indexOf(c1)) >= 0 && (p2 = UkAlphabet.indexOf(c2)) >= 0)
                            return UkAlphabet.indexOf(c1) - UkAlphabet.indexOf(c2);
                        else
                            return c1 - c2;
                    }
                    k++;
                }
                return len1 - len2;
            }
        });
        return periodicals;
    }

}
