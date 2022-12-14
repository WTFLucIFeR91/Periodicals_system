package com.epam.dao;

import com.epam.entity.Payment;
import com.epam.entity.Subscription;
import com.epam.exceptions.DBException;

public interface PaymentDAO {

    boolean addPayment(Payment payment) throws DBException;

    boolean addPaymentBySubscription(Payment payment, Subscription subscription) throws DBException;

    boolean createPayment(Payment payment, Subscription subscription) throws DBException;
}
