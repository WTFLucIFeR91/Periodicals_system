package com.epam.model.entities.enums;

import com.epam.model.entities.User;

public enum UserStatus {
    ACTIVE, BANNED;


    public static UserStatus getStatus(User user) {
        return user.getStatus();
    }

    public String getName() {
        return name().toLowerCase();
    }

    public static UserStatus getStatus(String status){
        return UserStatus.valueOf(status);
    }
}
