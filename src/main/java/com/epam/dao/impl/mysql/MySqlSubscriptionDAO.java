package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.DaoFactory;
import com.epam.dao.SubscriptionDAO;
import com.epam.entity.*;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySqlSubscriptionDAO implements SubscriptionDAO {
    private static final Logger log = LogManager.getLogger(MySqlSubscriptionDAO.class);
    private static final String INSERT_SUBSCRIPTION = "INSERT INTO `periodicals_system`.`subscription` " +
            "(`user_authorization_email`,`publication_index`,`payment_id`,`status`,`created_at`, `expired_at`) VALUES (?,?,?,?,?,?)";

    private static final String FIND_ALL_SUBSCRIPTIONS = "SELECT * FROM periodicals_system.subscription;";

    private static final String SQL_FIND_SUBSCRIPTION = "SELECT * FROM periodicals_system.subscription WHERE " +
            "user_authorization_email = ? and publication_index = ? and  " +
            "status = 'active';";

    @Override
    public boolean addSubscription(User user, Publication publication, Payment payment) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_SUBSCRIPTION);
            int k = 0;
            pstmt.setString(++k, user.getEmail());
            pstmt.setString(++k, publication.getIndex());
            pstmt.setInt(++k, payment.getId());
            pstmt.setString(++k, SubscriptionStatus.ACTIVE.getName());
            pstmt.setTimestamp(++k,payment.getDate());
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add subscription";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }

    @Override
    public boolean addSubscription(Subscription subscription, int id) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_SUBSCRIPTION);
            int k = 0;
            pstmt.setString(++k, subscription.getLogin());
            pstmt.setString(++k, subscription.getIndex());
            pstmt.setInt(++k, id);
            pstmt.setString(++k, SubscriptionStatus.ACTIVE.getName());
            pstmt.setTimestamp(++k,subscription.getStartDate());
            pstmt.setTimestamp(++k,subscription.getEndDate());
            pstmt.executeUpdate();
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add subscription";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }

    private static final String FIND_USER_SUBSCRIPTIONS = "SELECT * FROM periodicals_system.subscription WHERE user_authorization_email = ? " +
            "LIMIT ?,?;";

    @Override
    public List<Subscription> findSubscriptionsByUserEmail(String email,int start, int end) throws DBException {
        List<Subscription> users = new CopyOnWriteArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_USER_SUBSCRIPTIONS);
            pstmt.setString(1,email);
            pstmt.setInt(2,start);
            pstmt.setInt(3,end);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Subscription subscription = extractSubscription(rs);
                Publication publication = DaoFactory.createPublicationDAO().findPublicationByIndex(subscription.getIndex());
                subscription.setPublication(publication);
                users.add(subscription);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find users subscription";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return users;
    }

    private Subscription extractSubscription(ResultSet rs) throws SQLException {
       Subscription sb = new Subscription();
       sb.setLogin(rs.getString("user_authorization_email"));
       sb.setIndex(rs.getString("publication_index"));
       sb.setPaymentId(rs.getInt("payment_id"));
       sb.setStartDate(rs.getTimestamp("created_at"));
       sb.setEndDate(rs.getTimestamp("expired_at"));
       sb.setStatus(SubscriptionStatus.valueOf(rs.getString("status").toUpperCase()));
        return sb;
    }

    @Override
    public boolean isSubscribed(String email, String index) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_SUBSCRIPTION);
           int k = 0;
            pstmt.setString(++k, email);
            pstmt.setString(++k, index);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = true;
            }


            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find subscribed...";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }
    private static final String FIND_COUNT_FOR_USER ="SELECT COUNT(*) FROM subscription WHERE user_authorization_email = ?;";
    @Override
    public int count(String email) {
      int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(FIND_COUNT_FOR_USER);
            int k = 0;
            pstmt.setString(++k, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT(*)");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t find subscribed...";
            log.error(message, ex);

        } finally {
            DBManager.getInstance().close(con);
        }
        return count;
    }

    private Connection getConnection() {
        return DBManager.getInstance().getConnection();
    }
}
