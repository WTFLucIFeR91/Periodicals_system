package com.epam.web.command.admin;

import com.epam.dao.DaoFactory;
import com.epam.dao.PublicationDAO;
import com.epam.dao.TopicDAO;
import com.epam.entity.Publication;
import com.epam.entity.Topic;
import com.epam.exceptions.DBException;
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

public class AddPeriodicalCommand implements Command {

    private static final Logger log = LogManager.getLogger(AddPeriodicalCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("AddPeriodicalCommand starts");

        String methodName = req.getMethod();
        log.trace("input method => " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_ADD_PERIODICAL;
        }
        HttpSession session = req.getSession();

        HashMap<String ,String> rq = download(req,session);

        String publicationIndex = rq.get("publicationIndex");
        log.trace("newPublicationIndex => " + publicationIndex);
        String publicationName = rq.get("publicationName");
        log.trace("publicationName => " + publicationName);
        String publicationTopic = rq.get("publicationTopic");
        log.trace("publicationTopic => " + publicationTopic);
        String publicationDescription = rq.get("publicationDescription");
        log.trace("publicationDescription => " + publicationDescription);
        String publicationLanguage = rq.get("publicationLanguage");
        log.trace("publicationLanguage => " + publicationLanguage);
        String img = rq.get("img");

        BigDecimal price;
        String priceStr = rq.get("publicationPrice");

        try {
            price = BigDecimal.valueOf(Double.parseDouble(priceStr));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return Path.COMMAND_ADD_PERIODICAL;
        }
        log.trace("price ==> " + price);

        if (publicationName == null || publicationName.isEmpty() ||
                publicationTopic == null || publicationTopic.isEmpty() ||
                publicationDescription == null || publicationDescription.isEmpty() ||
                publicationIndex == null || publicationIndex.isEmpty() ||
                publicationLanguage == null || publicationLanguage.isEmpty()) {
            return Path.COMMAND_ADD_PERIODICAL;
        }

        Publication publication = new Publication();
        publication.setIndex(publicationIndex);
        publication.setName(publicationName);
        publication.setDescription(publicationDescription);
        publication.setLanguage(publicationLanguage);
        publication.setTitleImgLink(img);
        publication.setPrice(price);

        Topic topic = DaoFactory.createTopicDAO().findTopicByName(publicationTopic);
        if (topic.getName() == null) {
            topic.setName(publicationTopic);
            topic = DaoFactory.createTopicDAO().addTopic(topic);
        }
        publication.setTopic(topic);


        PublicationDAO publicationDAO = DaoFactory.createPublicationDAO();
        publicationDAO.addPublication(publication);

        log.debug("AddPeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }

    private HashMap<String,String> download(HttpServletRequest req, HttpSession session) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        String  filePath = "C:\\Users\\Игорь Ворона\\Desktop\\Schedule(module 2-3)\\FinalTask\\Periodicals_system\\Periodicals_system\\src\\main\\webapp\\resources\\images\\";

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);
        File file;

        HashMap<String ,String> rq = new HashMap<>();

        try {
            List<FileItem> fileItems = upload.parseRequest(req);
            Iterator i = fileItems.iterator();

            while ( i.hasNext () ) {
                FileItem fi = (FileItem)i.next();
                if (fi.isFormField()){
                    byte [] bytes = fi.getString().getBytes(StandardCharsets.ISO_8859_1);
                    String k = new String(bytes, StandardCharsets.UTF_8);
                    rq.put(fi.getFieldName(),k);
                }else {

                    String fileName = fi.getName();
                    rq.put("img",fileName);
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // Write the file
                    if( fileName.lastIndexOf("\\") >= 0 ) {
                        file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                    } else {
                        file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
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
