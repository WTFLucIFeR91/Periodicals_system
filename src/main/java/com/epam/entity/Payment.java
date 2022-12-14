package com.epam.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Payment extends Entity{

    private String login;
    private Integer id;
    private Timestamp date;
    private BigDecimal totalPrice;

    public Payment() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(date, payment.date) && Objects.equals(totalPrice, payment.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, totalPrice);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
