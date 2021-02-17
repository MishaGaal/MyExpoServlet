package com.example.myexpo.command;

import com.example.myexpo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class User implements Command {
    static final Logger log = LogManager.getRootLogger();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.getSession().setAttribute("users", userService.getAllUsers());
        } catch (Exception e) {
            log.info("{}", "Cant get all users: " + e.getMessage());
        }
        return "/user.jsp";
    }
}
