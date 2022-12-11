package com.epam.entity;

public enum Role {
      ADMIN,USER,UNKNOWN;

      public static Role getRole (User user){
          return user.getRole();
      }

      public String getName(){
          return name().toLowerCase();
      }

      public static Role getRole (String role){
          return valueOf(role);
      }
}
