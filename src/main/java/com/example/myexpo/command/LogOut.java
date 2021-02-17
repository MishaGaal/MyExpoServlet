package com.example.myexpo.command;

import com.example.myexpo.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User current = (User) request.getSession().getAttribute("user");
        if (current != null) {
            CommandUtility.logOutUser(request, current.getUsername());
            request.getSession()
                    .setAttribute("login", "You have been logged out!");
        }
        return "redirect:/login";
    }
}
