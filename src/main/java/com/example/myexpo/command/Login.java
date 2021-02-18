package com.example.myexpo.command;

import com.example.myexpo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class Login implements Command {

    static final Logger log = LogManager.getRootLogger();
    private final UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            String pass = request.getParameter("pass");
            CommandUtility.logOutUser(request, name);
            if (name == null
                    || pass == null
                    || CommandUtility.checkUserIsLogged(request, name)) {
                return "/login.jsp";
            }
            CommandUtility.setUserRole(request,
                    userService.loginUser(name, pass));
            return "redirect:/main";
        } catch (Exception e) {
            log.info("failed login");
            request.getSession()
                    .setAttribute("login", "Invalid credentials!");
            return "/login.jsp";
        }
    }
}
