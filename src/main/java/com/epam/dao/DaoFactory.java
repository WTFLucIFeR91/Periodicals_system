package com.epam.dao;

import com.epam.dao.impl.mysql.*;

public class DaoFactory {

    public static SubscriptionDAO createSubscriptionDAO() {
        return new MySqlSubscriptionDAO();
    }

    public static TopicDAO createTopicDAO() {
        return new MySqlTopicDAO();
    }

    public static PublicationDAO createPublicationDAO() {
        return new MySqlPublicationDAO();
    }

    public static PaymentDAO createPaymentDAO() {
        return new MySqlPaymentDAO();
    }

    public static UserDAO createUserDao() {
        return new MySqlUserDAO();
    }

    public static UserDetailsDAO createUserDetailsDao() {
        return new MySqlUserDetailsDAO();
    }
}





