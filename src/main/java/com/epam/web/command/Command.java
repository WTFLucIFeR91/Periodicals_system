package com.epam.web.command;

import com.epam.exceptions.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public interface Command {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException, ParseException;
}
