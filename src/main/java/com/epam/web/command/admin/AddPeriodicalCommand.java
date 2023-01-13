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

public class AddPeriodicalCommand implements Command {

    private static final Logger log = LogManager.getLogger(AddPeriodicalCommand.class);
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
        log.debug("AddPeriodicalCommand starts");

        HttpSession session = req.getSession();

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_ADD_PERIODICAL;
        }


        HashMap<String ,String> rq = download(req);
        Map<String, String> parameterMap = getRequestParameters(rq, session);
        initParameterMapToSession(session,parameterMap);

        BigDecimal price;
        String priceStr = parameterMap.get(publicationPrice);

        try {
            price = BigDecimal.valueOf(Double.parseDouble(priceStr));
            validateData(parameterMap);
            isPresentInDB(parameterMap.get(publicationIndex),req);
        } catch (Exception e) {
            req.getSession().setAttribute("errorMessage", e.getMessage());
            e.printStackTrace();
            return Path.COMMAND_ADD_PERIODICAL;
        }
        log.trace("price ==> " + price);

        if (isParametersPresent(parameterMap)) {
            return Path.COMMAND_ADD_PERIODICAL;
        }

        publication = buildPublication(parameterMap,price);

        Topic topic = DaoFactory.createTopicDAO().findTopicByName(parameterMap.get(publicationTopic));
        if (topic.getName() == null) {
            topic.setName(parameterMap.get(publicationDescription));
            topic = DaoFactory.createTopicDAO().addTopic(topic);
        }
        publication.setTopic(topic);


        PublicationDAO publicationDAO = DaoFactory.createPublicationDAO();
        publicationDAO.addPublication(publication);

        log.debug("AddPeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }

    private void isPresentInDB(String index,HttpServletRequest req) {
        try {
            if(DaoFactory.createPublicationDAO().isPresentIndex(index)){
                throw new IllegalArgumentException("this_index_already_used");
            }
        }catch (DBException ex){
            throw new IllegalArgumentException("this_index_already_used");
        }
    }

    private void validateData(Map<String, String> parameterMap) {
        DataValidator.validateMoneyAmount(parameterMap.get(publicationPrice));
        DataValidator.validateText(parameterMap.get(publicationName));
        DataValidator.validateText(parameterMap.get(publicationLanguage));
        DataValidator.validateText(parameterMap.get(publicationTopic));
        DataValidator.validateText(parameterMap.get(publicationDescription));
    }

    private Publication buildPublication(Map<String, String> parameterMap, BigDecimal price) {
        publication = new Publication();
        publication.setIndex(parameterMap.get(publicationIndex));
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
                parameterMap.get(publicationLanguage) == null || parameterMap.get(publicationLanguage).isEmpty() ||
                parameterMap.get(publicationIndex) == null || parameterMap.get(publicationIndex).isEmpty() ;
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

    private HashMap<String, String> download(HttpServletRequest req) {
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
                    rq.put("img",fileName);
                    if (fileName.isEmpty()) {
                        rq.put("img", "");
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

    private void initParameterMapToSession(HttpSession session, Map<String, String> parameterMap) {
        for (Map.Entry<String, String> pair: parameterMap.entrySet()) {
            session.setAttribute(pair.getKey(),pair.getValue());
        }
    }
}
