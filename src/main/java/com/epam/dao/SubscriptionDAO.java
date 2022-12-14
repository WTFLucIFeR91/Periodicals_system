package com.epam.dao;

import com.epam.entity.Payment;
import com.epam.entity.Publication;
import com.epam.entity.Subscription;
import com.epam.entity.User;
import com.epam.exceptions.DBException;

import java.math.BigInteger;
import java.util.List;

public interface SubscriptionDAO {

    boolean addSubscription(User user, Publication publication, Payment payment) throws DBException;

    List<Subscription> findSubscriptionsByUserEmail(String email) throws DBException;

    boolean isSubscribed(String email, String  index) throws DBException;
}
