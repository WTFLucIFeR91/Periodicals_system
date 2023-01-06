package com.epam.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Subscription extends Entity {

   private String login;
   private String index;
   private Integer paymentId;
   private Publication publication;
   private SubscriptionStatus status;
   private Timestamp startDate;
   private Timestamp endDate;

   public Subscription() {
   }

   public SubscriptionStatus getStatus() {
      return status;
   }

   public void setStatus(SubscriptionStatus status) {
      this.status = status;
   }

   public Timestamp getStartDate() {
      return startDate;
   }

   public void setStartDate(Timestamp startDate) {
      this.startDate = startDate;
   }

   public Timestamp getEndDate() {
      return endDate;
   }

   public void setEndDate(Timestamp endDate) {
      this.endDate = endDate;
   }
   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   @Override
   public String toString() {
      return "Subscription{" +
              "status=" + status +
              ", startDate=" + startDate +
              ", endDate=" + endDate +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Subscription that = (Subscription) o;
      return status == that.status && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
   }

   @Override
   public int hashCode() {
      return Objects.hash(status, startDate, endDate);
   }

   public String getIndex() {
      return index;
   }

   public void setIndex(String index) {
      this.index = index;
   }

   public Integer getPaymentId() {
      return paymentId;
   }

   public void setPaymentId(Integer paymentId) {
      this.paymentId = paymentId;
   }

   public Publication getPublication() {
      return publication;
   }

   public void setPublication(Publication publication) {
      this.publication = publication;
   }
}
