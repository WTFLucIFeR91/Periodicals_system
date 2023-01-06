package com.epam.dao.impl.mysql;

import com.epam.dao.DBManager;
import com.epam.dao.PaymentDAO;
import com.epam.entity.Payment;
import com.epam.entity.Subscription;
import com.epam.entity.SubscriptionStatus;
import com.epam.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MySqlPaymentDAO implements PaymentDAO {

    private static final Logger log = LogManager.getLogger(MySqlPaymentDAO.class);
    private static final String INSERT_PAYMENT ="INSERT INTO `periodicals_system`.`payment` (`user_authorization_email`,`total_price`) VALUES(?,?);";
    private static final String INSERT_SUBSCRIPTION ="INSERT INTO `periodicals_system`.`subscription` " +
            "(`user_authorization_email`,`publication_index`,`payment_id`,`status`,`created_at`) VALUES (?,?,?,?,?)";

    @Override
    public boolean addPayment(Payment payment) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);

            int k = 0;
            pstmt.setString(++k, payment.getLogin());
            pstmt.setBigDecimal(++k, payment.getTotalPrice());
            pstmt.executeUpdate();
            rs=pstmt.getGeneratedKeys();
            while (rs.next()){
                payment.setId(rs.getInt(1));
            }
            con.commit();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add payment";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }

    @Override
    public boolean addPaymentBySubscription(Payment payment, Subscription subscription) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);
            int k = 0;
            pstmt.setString(++k, payment.getLogin());
            pstmt.setBigDecimal(++k, payment.getTotalPrice());
            pstmt.executeUpdate();
            con.commit();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                payment.setId(rs.getInt(1));
            }

            result = true;
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t add payment by subscription";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return true;
    }

    @Override
    public boolean createPayment(Payment payment, Subscription subscription) throws DBException {
        boolean result = false;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(INSERT_SUBSCRIPTION);
            int k = 0;
            pstmt.setString(++k, subscription.getLogin());
            pstmt.setString(++k, subscription.getIndex());
            pstmt.setLong(++k, payment.getId());
            pstmt.setString(++k, SubscriptionStatus.STOPPED.getName());
            pstmt.setTimestamp(++k, subscription.getStartDate());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();


            pstmt = con.prepareStatement(INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS);
            int m = 0;

            pstmt.setString(++m, payment.getLogin());
            pstmt.setBigDecimal(++m, payment.getTotalPrice());

            pstmt.executeUpdate();
            if (rs.next()) {
                payment.setId(rs.getInt(1));
            }
            con.commit();

            result = true;
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollback(con);
            String message = "Can`t create payment";
            log.error(message, ex);
            throw new DBException(message, ex);
        } finally {
            DBManager.getInstance().close(con);
        }
        return result;
    }

    private Connection getConnection() {
        return DBManager.getInstance().getConnection();
    }
}
