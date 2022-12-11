package com.epam.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Subscription extends Entity {

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
}
