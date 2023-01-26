package com.epam.model.entities;

import com.epam.model.entities.enums.UserRole;
import com.epam.model.entities.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private transient String password;
    private UserRole role;
    private UserStatus status;
    private BigDecimal balance;
    private String name;
    private String surname;
    private String deliveryAddress;
    private String telephone;

}
