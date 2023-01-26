package com.epam.model.entities.enums;

import com.epam.model.entities.Subscription;

public enum SubscriptionStatus {

    ACTIVE, STOPPED;
    public static SubscriptionStatus getSubscriptionStatus(Subscription subscription) {
        return subscription.getSubscriptionStatus();
    }

    public String getName() {
        return name().toLowerCase();
    }
}
