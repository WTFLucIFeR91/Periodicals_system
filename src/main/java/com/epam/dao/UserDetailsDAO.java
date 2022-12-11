package com.epam.dao;

import com.epam.entity.User;
import com.epam.entity.UserDetails;
import com.epam.exceptions.DBException;

import java.util.List;

public interface UserDetailsDAO {

    UserDetails findUserDetailsByEmail(String email) throws DBException;

    boolean addUserDetails(UserDetails userDetails, User user) throws DBException;

    boolean updateUserDetails(UserDetails userDetails,User user) throws DBException;

}
