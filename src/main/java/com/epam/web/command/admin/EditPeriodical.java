package com.epam.web.command.admin;

import com.epam.dao.DaoFactory;
import com.epam.dao.PublicationDAO;

import com.epam.entity.Publication;
import com.epam.entity.Topic;
import com.epam.exceptions.DBException;
import com.epam.util.DataValidator;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class EditPeriodical implements Command {

    private static final Logger log = LogManager.getLogger(EditPeriodical.class);
    private final String publicationIndex = "publicationIndex";
    private final String publicationName = "publicationName";
    private final String publicationTopic = "publicationTopic";
    private final String publicationDescription = "publicationDescription";
    private final String publicationLanguage = "publicationLanguage";
    private final String publicationPrice = "publicationPrice";
    private final String img = "img";

    private Publication publication;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("EditPeriodicalCommand starts");

        HttpSession session = req.getSession();

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            String publicationIndex = req.getParameter("publicationIndex");
            publication = DaoFactory.createPublicationDAO().findPublicationByIndex(publicationIndex);
            session.setAttribute("publication", publication);
            session.setAttribute("publicationIndex", publicationIndex);
            return Path.PAGE_EDIT_PERIODICAL;
        }

        Publication publicationFromSession = (Publication) session.getAttribute("publication");
        String imgForPublication = publicationFromSession.getTitleImgLink();

        HashMap<String, String> rq = download(req, imgForPublication);
        Map<String, String> parameterMap = getRequestParameters(rq, session);


        BigDecimal price = null;
        try {
            price = BigDecimal.valueOf(Double.parseDouble(parameterMap.get(publicationPrice)));
            validateData(parameterMap);
        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("errorMessage", e.getMessage());

            return Path.COMMAND_EDIT_PERIODICAL_CONTROLLER+session.getAttribute("publicationIndex");
        }

        if (isParametersPresent(parameterMap)) {
            return Path.PAGE_EDIT_PERIODICAL;
        }

        publication = buildPublication(parameterMap, price, session);

        Topic topic = DaoFactory.createTopicDAO().findTopicByName(parameterMap.get(publicationTopic));

        if (isTopicPresent(topic)) {
            topic.setName(parameterMap.get(publicationTopic));
            topic = DaoFactory.createTopicDAO().addTopic(topic);
        }
        publication.setTopic(topic);

        PublicationDAO publicationDAO = DaoFactory.createPublicationDAO();
        publicationDAO.updatePublication(publication);


        log.debug("EditPeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }

    private void validateData(Map<String, String> parameterMap) {
        DataValidator.validateMoneyAmount(parameterMap.get(publicationPrice));
        DataValidator.validateText(parameterMap.get(publicationName));
        DataValidator.validateText(parameterMap.get(publicationLanguage));
        DataValidator.validateText(parameterMap.get(publicationTopic));
        DataValidator.validateText(parameterMap.get(publicationDescription));
    }

    private boolean isTopicPresent(Topic topic) {
        return topic.getName() == null || topic.getName().isEmpty();
    }

    private Publication buildPublication(Map<String, String> parameterMap, BigDecimal price, HttpSession session) {
        publication = new Publication();
        publication.setIndex((String) session.getAttribute(publicationIndex));
        publication.setName(parameterMap.get(publicationName));
        publication.setDescription(parameterMap.get(publicationDescription));
        publication.setLanguage(parameterMap.get(publicationLanguage));
        publication.setTitleImgLink(parameterMap.get(img));
        publication.setPrice(price);
        return publication;
    }

    private boolean isParametersPresent(Map<String, String> parameterMap) {
        return parameterMap.get(publicationName) == null || parameterMap.get(publicationName).isEmpty() ||
                parameterMap.get(publicationTopic) == null || parameterMap.get(publicationTopic).isEmpty() ||
                parameterMap.get(publicationDescription) == null || parameterMap.get(publicationDescription).isEmpty() ||
                parameterMap.get(publicationLanguage) == null || parameterMap.get(publicationLanguage).isEmpty();
    }

    private Map<String, String> getRequestParameters(HashMap<String, String> rq, HttpSession session) {
        Map<String, String> map = new HashMap<>();

        map.put(publicationIndex, rq.get(publicationIndex));
        map.put(publicationName, rq.get(publicationName));
        map.put(publicationTopic, rq.get(publicationTopic));
        map.put(publicationDescription, rq.get(publicationDescription));
        map.put(publicationLanguage, rq.get(publicationLanguage));
        map.put(img, rq.get(img));
        map.put(publicationPrice, rq.get(publicationPrice));
        map.put("errorMessage",(String) session.getAttribute("errorMessage"));
        return map;
    }

    private HashMap<String, String> download(HttpServletRequest req, String imgForPublication) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        String filePath = "C:\\Users\\Игорь Ворона\\Desktop\\Schedule(module 2-3)\\FinalTask\\Periodicals_system\\Periodicals_system\\src\\main\\webapp\\resources\\images\\";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        File file;
        HashMap<String, String> rq = new HashMap<>();

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            Iterator i = fileItems.iterator();

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    byte[] bytes = fi.getString().getBytes(StandardCharsets.ISO_8859_1);
                    String k = new String(bytes, StandardCharsets.UTF_8);
                    rq.put(fi.getFieldName(), k);
                } else {
                    String fileName = fi.getName();
                    if (fileName.isEmpty()) {
                        rq.put("img", imgForPublication);
                        return rq;
                    }
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rq;
    }
}
