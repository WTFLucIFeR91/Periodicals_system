package com.epam.entity.Enum;

import com.epam.entity.Subscription;

public enum SubscriptionStatus {
    ACTIVE, STOPPED;
    public static SubscriptionStatus getStatus(Subscription subscription) {
        return subscription.getStatus();
    }

    public String getName() {
        return name().toLowerCase();
    }
}
