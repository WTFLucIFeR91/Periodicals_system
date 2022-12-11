package com.epam.entity;

public enum SubscriptionStatus {
    ACTIVE, STOPPED;


    public static SubscriptionStatus getStatus(Subscription subscription) {
        return subscription.getStatus();
    }

    public String getName() {
        return name().toLowerCase();
    }
}
