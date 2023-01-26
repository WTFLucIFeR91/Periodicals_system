package com.epam.model.entities;

import com.epam.model.entities.enums.SubscriptionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@EqualsAndHashCode
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String index;
    private SubscriptionStatus subscriptionStatus;
    private Timestamp startDate;
    private Timestamp endDate;
    private long paymentId;



}
