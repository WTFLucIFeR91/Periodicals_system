package com.epam.model.entities.enums;

import com.epam.model.entities.User;

public enum UserRole {
    ADMIN,USER,UNKNOWN;

    public static UserRole getRole (User user){
        return user.getRole();
    }

    public String getName(){
        return name().toLowerCase();
    }

    public static UserRole getRole (String role){
        return valueOf(role);
    }
}
