package com.epam.web.command.user;

import com.epam.dao.DaoFactory;
import com.epam.entity.*;
import com.epam.exceptions.DBException;
import com.epam.web.Path;
import com.epam.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

public class PaymentFormCommand implements Command {

    private static final Logger log = LogManager.getLogger(PaymentFormCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("PaymentFormCommand starts");

        HttpSession session = req.getSession();

        Publication publication = (Publication) session.getAttribute("periodical");
        User user = (User) session.getAttribute("user");

        SubscriptionPeriod subscriptionPeriod = (SubscriptionPeriod) session.getAttribute("subscriptionPeriod");


        Calendar currentDate = Calendar.getInstance();

        Subscription subscription = createSubscription (user.getEmail(),publication.getIndex(),subscriptionPeriod,currentDate);
        Payment payment = createPayment(user.getEmail(),currentDate,publication.getPrice(),subscriptionPeriod.getNumber());

        if(!checkBalance(user.getBalance(),payment.getTotalPrice())){
            log.debug("not enough money on the balance, forward to top up balance");
            return Path.PAGE_TOP_UP_BALANCE;
        }

        DaoFactory.createPaymentDAO().addPaymentBySubscription(payment,subscription);
        DaoFactory.createSubscriptionDAO().addSubscription(subscription,payment.getId());

        updateBalance(user, payment.getTotalPrice());
        session.setAttribute("user", user);

        log.debug("PaymentFormCommand finished");
        return Path.COMMAND_MAIN_PAGE;

    }

    private void updateBalance(User user, BigDecimal totalPrice) throws DBException {
        BigDecimal resultBalance = user.getBalance().subtract(totalPrice);
        user.setBalance(resultBalance);
        DaoFactory.createUserDao().updateUser(user);
    }

    private boolean checkBalance(BigDecimal balance, BigDecimal totalPrice) {
        return balance.compareTo(totalPrice) >=0;
    }

    private Payment createPayment(String email, Calendar currentDate, BigDecimal price, int number) {
        Payment payment = new Payment();

        Timestamp paymentDate = new Timestamp(currentDate.getTime().getTime());

        payment.setLogin(email);
        payment.setDate(paymentDate);
        payment.setTotalPrice(price.multiply(new BigDecimal(number)));
        return payment;
    }

    private Subscription createSubscription(String email, String index, SubscriptionPeriod subscriptionPeriod, Calendar currentDate) {
        Subscription subscription = new Subscription();

        Timestamp startDate = new Timestamp(currentDate.getTime().getTime());
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH,subscriptionPeriod.getNumber());
        Timestamp endDate = new Timestamp(calendar.getTime().getTime());

        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setLogin(email);
        subscription.setIndex(index);

        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);
        return subscription;
    }
}
