package com.epam.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@EqualsAndHashCode
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private long id;
    private String publicationIndex;
    private Timestamp date;
    private BigDecimal totalPrice;
    private int period;
}
