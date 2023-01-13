package com.epam.entity.Enum;

import com.epam.entity.User;

public enum Status {

    ACTIVE, BANNED;


    public static Status getStatus(User user) {
        return user.getStatus();
    }

    public String getName() {
        return name().toLowerCase();
    }

    public static Status getStatus(String status){
        return Status.valueOf(status);
    }
}
