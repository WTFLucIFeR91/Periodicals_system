package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.DaoFactory;
import com.epam.dao.TopicDAO;
import com.epam.entity.Topic;
import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySqlTopicDAO implements TopicDAO {

    private static final Logger log = LogManager.getLogger(MySqlUserDAO.class);


    private static final String INSERT_TOPIC = "INSERT INTO `periodicals_system`.`topic` (`name`) VALUES (?);";
    private static final String FIND_TOPIC_BY_NAME ="SELECT * FROM periodicals_system.topic WHERE name =?;";
    private static final String FIND_ALL_TOPIC ="SELECT * FROM periodicals_system.topic";
    private static final String UPDATE_TOPIC = "UPDATE `periodicals_system`.`topic` SET `name` = ? WHERE `id` = ?;";


    @Override
    public Topic findTopicByName(String name) throws DBException {
        Topic topic = new Topic();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_TOPIC_BY_NAME);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
               topic.setId(rs.getInt("id"));
               topic.setName(rs.getString("name"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find topic by name";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return topic;
    }

    @Override
    public List<Topic> findAllTopics() throws DBException {

        List<Topic> topics = new CopyOnWriteArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_TOPIC);
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getInt("id"));
                topic.setName(rs.getString("name"));
                topics.add(topic);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find all topic";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return topics;
    }

    @Override
    public Topic addTopic(Topic topic) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_TOPIC, Statement.RETURN_GENERATED_KEYS);

            int k = 0;
            pstmt.setString(++k, topic.getName());
            pstmt.executeUpdate();
            rs=pstmt.getGeneratedKeys();
            if (rs.next()) {
                topic.setId(rs.getInt(1));
            }
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
        return topic;
    }

    @Override
    public boolean updateTopic(Topic topic) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(UPDATE_TOPIC);

            int k = 0;
            pstmt.setString(++k, topic.getName());
            pstmt.setInt(++k, topic.getId());

            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t update topic";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }



    private Connection getConnection() {
        return DBManager.getInstance().getConnection();
    }
}
