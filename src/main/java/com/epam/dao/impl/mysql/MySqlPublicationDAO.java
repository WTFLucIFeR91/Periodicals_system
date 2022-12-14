package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.PublicationDAO;
import com.epam.entity.Publication;
import com.epam.entity.Topic;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySqlPublicationDAO implements PublicationDAO {

    private static final Logger log = LogManager.getLogger(MySqlPublicationDAO.class);

    private static final String INSERT_PUBLICATION = "INSERT INTO `periodicals_system`.`publication` " +
            "(`index`,`name`,`description`,`language`,`price`,`img`,`topic_id`) VALUES " +
            "(?,?,?,?,?,?,?);";
    private static final String FIND_ALL_PERIODICALS ="SELECT " +
            "publication.index as 'index', publication.name as name, publication.description as description, " +
            "publication.language as language,publication.price as price,publication.img as img, " +
            "topic_id as id , topic.name as topic " +
            "FROM publication " +
            "LEFT JOIN topic ON topic_id = topic.id "+
            "ORDER BY topic; ";




    @Override
    public List<Publication> findAllPeriodicals() throws DBException {
        List<Publication> periodicals = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_ALL_PERIODICALS);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                periodicals.add(extractPublication(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find all periodicals";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return periodicals;
    }

    private Publication extractPublication(ResultSet rs) throws SQLException {
        Publication publication = new Publication();
        publication.setIndex(rs.getString("index"));
        publication.setName(rs.getString("name"));
        publication.setDescription(rs.getString("description"));
        publication.setLanguage(rs.getString("language"));
        publication.setPrice(rs.getBigDecimal("price"));
        publication.setTitleImgLink(rs.getString("img"));
        Topic topic = new Topic();
        topic.setId(rs.getInt("id"));
        topic.setName(rs.getString("topic"));
        publication.setTopic(topic);
        return publication;
    }

    @Override
    public List<Publication> findPeriodicalsByName(String name) throws DBException {
        return null;
    }

    @Override
    public Publication findPublicationByEmail(String email) throws DBException {
        return null;
    }

    @Override
    public boolean addPublication(Publication periodical) throws DBException {
        return false;
    }

    @Override
    public boolean updatePublication(Publication periodical) throws DBException {
        return false;
    }

    @Override
    public boolean deletePublicationByEmail(String email) throws DBException {
        return false;
    }


    private Connection getConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}
