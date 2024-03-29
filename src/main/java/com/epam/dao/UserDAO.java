package com.epam.dao;

import com.epam.entity.User;
import com.epam.exceptions.DBException;

import java.util.List;

public interface UserDAO {

    User findUserByEmail(String email) throws DBException;

    List<User> findAllUsers() throws DBException;

    boolean addUser(User user) throws DBException;

    boolean updateUser(User user) throws DBException;
    boolean updateUserByEmail(String email,User user) throws DBException;

    boolean updateUserWithoutBalance(User user) throws DBException;

    int countUsers();

    List<User> findAllUsersForPaginate(int start, int end) throws DBException;

    boolean emailIsUsing(String email) throws DBException;

}
