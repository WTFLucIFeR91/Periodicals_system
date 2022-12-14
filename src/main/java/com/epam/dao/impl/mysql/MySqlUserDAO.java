package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.DaoFactory;
import com.epam.dao.UserDAO;
import com.epam.entity.Role;
import com.epam.entity.Status;
import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySqlUserDAO implements UserDAO {

    private static final Logger log = LogManager.getLogger(MySqlUserDAO.class);
    private static final String FIND_ALL_USERS = "SELECT * FROM periodicals_system.user_authorization;";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM periodicals_system.user_authorization WHERE email = ?;";
    private static final String INSERT_USER = "INSERT INTO `periodicals_system`.`user_authorization` " +
            "(`email`,`password`,`role`,`status`,`balance`) VALUES(?,?,?,?,?);";
    private static final String UPDATE_USER = "UPDATE user_authorization SET password=?, role=?, status=?, balance=? WHERE email=?;";
    private static final String UPDATE_USER_WITHOUT_BALANCE = "UPDATE user_authorization SET password=?, role=?, status=? WHERE email=?;";

    @Override
    public User findUserByEmail(String email) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
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
        UserDetails ud = DaoFactory.createUserDetailsDao().findUserDetailsByEmail(email);
        user.setUserDetails(ud);
        return user;
    }
    @Override
    public List<User> findAllUsers() throws DBException {

        List<User> users = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_ALL_USERS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(extractUser(rs));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find all users";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return users;
    }

    @Override
    public boolean addUser(User user) throws DBException {

        PreparedStatement pstmt = null;

        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            int k = 0;
            pstmt.setString(++k, user.getEmail());
            pstmt.setString(++k, user.getPassword());
            pstmt.setString(++k, user.getRole().toString());
            pstmt.setString(++k, user.getStatus().toString());
            pstmt.setBigDecimal(++k, user.getBalance());
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
    public boolean updateUser(User user) throws DBException {

        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(UPDATE_USER);

            int k = 0;
            pstmt.setString(++k, user.getPassword());
            pstmt.setString(++k, user.getRole().getName());
            pstmt.setString(++k, user.getStatus().getName());
            pstmt.setBigDecimal(++k, user.getBalance());
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

    @Override
    public boolean updateUserWithoutBalance(User user) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(UPDATE_USER_WITHOUT_BALANCE);

            int k = 0;
            pstmt.setString(++k, user.getPassword());
            pstmt.setString(++k, user.getRole().getName());
            pstmt.setString(++k, user.getStatus().getName());
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

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf((rs.getString("role")).toUpperCase()));
        user.setStatus(Status.valueOf((rs.getString("status")).toUpperCase()));
        user.setBalance(rs.getBigDecimal("balance"));
        return user;
    }
}
