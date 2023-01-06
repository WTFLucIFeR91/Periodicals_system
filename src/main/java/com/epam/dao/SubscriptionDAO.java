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

    boolean addSubscription(Subscription subscription, int id) throws DBException;

    List<Subscription> findSubscriptionsByUserEmail(String email, int start, int end) throws DBException;

    boolean isSubscribed(String email, String  index) throws DBException;

    int count(String email);
}
