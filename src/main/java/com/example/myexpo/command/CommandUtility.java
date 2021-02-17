package com.example.myexpo.command;


import com.example.myexpo.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {


    static void setUserRole(HttpServletRequest request,
                            User user) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", user.getUsername());
        session.setAttribute("user", user);
        session.setAttribute("roles", User.Role.values());
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    static void logOutUser(HttpServletRequest request, String name) {
        ((HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers")).remove(name);
        request.getSession().removeAttribute("user");
    }
}
