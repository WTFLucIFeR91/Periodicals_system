package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.UserDetailsDAO;
import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;

public class MySqlUserDetailsDAO implements UserDetailsDAO {

    private static final Logger log = LogManager.getLogger(MySqlUserDAO.class);


    private static final String INSERT_USER_DETAILS = "INSERT INTO `periodicals_system`.`user_details` " +
            "(`user_authorization_email`,`first_name`,`last_name`,`delivery_address`,`telephone`) VALUES (?,?,?,?,?);";
    private static final String UPDATE_USER_DETAILS = "UPDATE `periodicals_system`.`user_details` " +
            "SET `user_authorization_email` = ?,`first_name` = ?,`last_name` = ?,`delivery_address` = ?,`telephone` = ? " +
            "WHERE `user_authorization_email` = ?;";

    private static final String FIND_USER_DETAILS_BY_EMAIL = "SELECT * FROM periodicals_system.user_details WHERE user_authorization_email = ?";


    @Override
    public UserDetails findUserDetailsByEmail(String email) throws DBException {
        UserDetails userDetails = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_USER_DETAILS_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userDetails = new UserDetails();
                userDetails.setFirstName(rs.getString("first_name"));
                userDetails.setLastName(rs.getString("last_name"));
                userDetails.setDeliveryAddress(rs.getString("delivery_address"));
                userDetails.setTelephone(rs.getString("telephone"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find user by email";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return userDetails;
    }

    @Override
    public boolean addUserDetails(UserDetails userDetails, User user) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_USER_DETAILS);
            int k = 0;
            pstmt.setString(++k, user.getEmail());
            pstmt.setString(++k, userDetails.getFirstName());
            pstmt.setString(++k, userDetails.getLastName());
            pstmt.setString(++k, userDetails.getDeliveryAddress());
            pstmt.setString(++k, userDetails.getTelephone());
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

    @Override
    public boolean updateUserDetails(UserDetails userDetails, User user) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(UPDATE_USER_DETAILS);

            int k = 0;
            pstmt.setString(++k, user.getEmail());
            pstmt.setString(++k, userDetails.getFirstName());
            pstmt.setString(++k, userDetails.getLastName());
            pstmt.setString(++k, userDetails.getDeliveryAddress());
            pstmt.setString(++k, userDetails.getTelephone());
            pstmt.setString(++k, user.getEmail());
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t update user";
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
