package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.DaoFactory;
import com.epam.dao.PublicationDAO;
import com.epam.dao.TopicDAO;
import com.epam.entity.Publication;
import com.epam.entity.Topic;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
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

    private static final String DELETE_PUBLICATION_BY_INDEX = "DELETE FROM `periodicals_system`.`publication` " +
            "WHERE publication.index = ?;";



    private static final String FIND_PUBLICATION_BY_INDEX = "SELECT   publication.index as 'index', publication.name as name, publication.description as description,  " +
            "            publication.language as language,publication.price as price,publication.img as img,  " +
            "            topic_id as id , topic.name as topic  " +
            "            FROM publication              " +
            "            LEFT JOIN topic ON topic_id = topic.id  " +
            "            WHERE publication.index = ?;";


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

            for (Publication p:periodicals) {
                String name = p.getName().toLowerCase();
                String result = "";
                result = result+name.substring(0,1).toUpperCase()+name.substring(1);
                p.setName(result);
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
    private static final String FIND_PUBLICATION_BY_NAME = "SELECT  " +
            "                publication.index as 'index', publication.name as name, publication.description as description,  " +
            "                publication.language as language,publication.price as price,publication.img as img, " +
            "                topic_id as id , topic.name as topic " +
            "                FROM publication             " +
            "                LEFT JOIN topic ON topic_id = topic.id  " +
            "                WHERE publication.name LIKE ? " +
            "                ORDER BY topic" +
            "                LIMIT ?,?;";
    @Override
    public List<Publication> findPublicationByName(String name, int start, int end) throws DBException {

        List<Publication> publications = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(FIND_PUBLICATION_BY_NAME);

            int k = 0;
            pstmt.setString(++k,"%"+name+"%");
            pstmt.setInt(++k,start);
            pstmt.setInt(++k,end);
            rs=pstmt.executeQuery();

            while (rs.next()) {
                publications.add(extractPublication(rs));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find publications";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return publications;

    }

    @Override
    public Publication findPublicationByIndex(String index) throws DBException {
        Publication publication = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_PUBLICATION_BY_INDEX);

            pstmt.setString(1, index);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                publication = extractPublication(rs);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find publication by index";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return publication;
    }


    @Override
    public boolean addPublication(Publication periodical) throws DBException {
        PreparedStatement pstmt = null;

        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_PUBLICATION);

            int k = 0;
            pstmt.setString(++k, periodical.getIndex());
            pstmt.setString(++k, periodical.getName());
            pstmt.setString(++k, periodical.getDescription());
            pstmt.setString(++k, periodical.getLanguage());
            pstmt.setBigDecimal(++k, periodical.getPrice());
            pstmt.setString(++k, "//");

            TopicDAO topicDAO = DaoFactory.createTopicDAO();
            Topic topic = topicDAO.findTopicByName(periodical.getTopic().getName());
            pstmt.setInt(++k,topic.getId());

            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add user";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }
    private static final String UPDATE_PUBLICATION = "UPDATE `periodicals_system`.`publication` SET " +
            "`name` = ?,`description` = ?,`language` = ?,`price` = ?,`img` = ?,`topic_id` = ? WHERE `index` = ? ";
    @Override
    public boolean updatePublication(Publication periodical) throws DBException {
        PreparedStatement pstmt = null;

        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(UPDATE_PUBLICATION);
            int k = 0;
            pstmt.setString(++k, periodical.getName());
            pstmt.setString(++k, periodical.getDescription());
            pstmt.setString(++k, periodical.getLanguage());
            pstmt.setBigDecimal(++k, periodical.getPrice());
            pstmt.setString(++k, periodical.getTitleImgLink());
            pstmt.setInt(++k,periodical.getTopic().getId());
            pstmt.setString(++k, periodical.getIndex());

            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t update periodical";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;

    }

    @Override
    public boolean deletePublicationByIndex(String index) throws DBException {
        PreparedStatement pstmt = null;

        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(DELETE_PUBLICATION_BY_INDEX);

            int k = 0;
            pstmt.setString(++k, index);
            pstmt.executeUpdate();

            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t delete publication ";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }
    private static final String SELECT_PAGINATION ="SELECT   publication.index as 'index', publication.name as name, publication.description as description,  " +
            "                    publication.language as language,publication.price as price,publication.img as img,  " +
            "                    topic_id as id , topic.name as topic " +
            "                    FROM publication              " +
            "                    LEFT JOIN topic ON topic_id = topic.id " +
            "                    LIMIT ?,?;";
    @Override
    public List<Publication> getPublication(int start, int total) throws DBException {
        List<Publication> periodicals = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SELECT_PAGINATION);

            pstmt.setInt(1,start);
            pstmt.setInt(2,total);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                periodicals.add(extractPublication(rs));
            }

            for (Publication p:periodicals) {
                String name = p.getName().toLowerCase();
                String result = "";
                result = result+name.substring(0,1).toUpperCase()+name.substring(1);
                p.setName(result);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find  periodicals";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return periodicals;
    }
    private static final String SELECT_PAGINATION_BY_TOPIC = "SELECT   publication.index as 'index', publication.name as name, publication.description as description, "+
                                                             " publication.language as language,publication.price as price,publication.img as img, "+
                                                             " topic_id as id , topic.name as topic "+
                                                             " FROM publication "+
                                                             " LEFT JOIN topic ON topic_id = topic.id "+
                                                             " ORDER BY topic"+
                                                             " LIMIT ?,?;" ;
    @Override
    public List<Publication> getPublicationByTopic(int start, int total) throws DBException {
        List<Publication> periodicals = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {

            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(SELECT_PAGINATION_BY_TOPIC);

            pstmt.setInt(1,start);
            pstmt.setInt(2,total);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                periodicals.add(extractPublication(rs));
            }

            for (Publication p:periodicals) {
                String name = p.getName().toLowerCase();
                String result = "";
                result = result+name.substring(0,1).toUpperCase()+name.substring(1);
                p.setName(result);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find  periodicals";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return periodicals;
    }

    private static final String SELECT_BY_TOPIC ="SELECT   publication.index as 'index', publication.name as name, publication.description as description,  " +
            "                          publication.language as language,publication.price as price,publication.img as img, " +
            "                          topic_id as id , topic.name as topic " +
            "                          FROM publication              " +
            "                          LEFT JOIN topic ON topic_id = topic.id " +
            "                          WHERE  topic_id = ?;";
    @Override
    public List<Publication> getPublicationByTopic(Topic topic) throws DBException {
        List<Publication> periodicals = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SELECT_BY_TOPIC);

            pstmt.setInt(1,topic.getId());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                periodicals.add(extractPublication(rs));
            }

            for (Publication p:periodicals) {
                String name = p.getName().toLowerCase();
                String result = "";
                result = result+name.substring(0,1).toUpperCase()+name.substring(1);
                p.setName(result);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find  periodicals";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return periodicals;
    }
    private static final String FIND_PUBLICATION_BY_PRICE_PAGINATE = "SELECT  " +
            "                publication.index as 'index', publication.name as name, publication.description as description,  " +
            "                publication.language as language,publication.price as price,publication.img as img, " +
            "                topic_id as id , topic.name as topic " +
            "                FROM publication             " +
            "                LEFT JOIN topic ON topic_id = topic.id  " +
            "                ORDER BY price" +
            "                LIMIT ?,?;";
    @Override
    public List<Publication> getPublicationByPrice(int start, int recordsPerPage) throws DBException {
        List<Publication> publications = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(FIND_PUBLICATION_BY_PRICE_PAGINATE);
            int k = 0;
            pstmt.setInt(++k,start);
            pstmt.setInt(++k,recordsPerPage);
            rs=pstmt.executeQuery();
            while (rs.next()) {
                publications.add(extractPublication(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find publications";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return publications;
    }


    private Connection getConnection() throws SQLException {
        return DBManager.getInstance().getConnection();
    }
}
