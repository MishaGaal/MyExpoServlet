package com.example.myexpo.command;

import com.example.myexpo.entity.User;
import com.example.myexpo.service.UserService;
import com.example.myexpo.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Registration implements Command {

    static final Logger log = LogManager.getRootLogger();
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            if (request.getParameter("name") == null) {
                return "/registration.jsp";
            }
            User user = CommandUtility.buildUser(request);
            request.getSession().setAttribute("user", user);
            if (Validator.validate(user, request.getParameter("passwordConf"))) {
                request.getSession().setAttribute("user", userService.registerUser(user));
            }
            return "redirect:/login";
        } catch (Exception e) {
            log.info(e.getMessage());
            request.getSession()
                    .setAttribute("valid", e.getMessage());
            return "/registration.jsp";
        }

    }
}
