package com.example.myexpo.filter;

import com.example.myexpo.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        User user = Optional.ofNullable((User) session.getAttribute("user")).orElse(new User());
        Set<User.Role> roles = Optional.ofNullable(user.getRoles()).orElse(new HashSet<>());
        if (!roles.contains(User.Role.ADMIN)) {
            ((HttpServletResponse) servletResponse).sendRedirect("app/logout");
        } else {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            filterChain.doFilter(req, response);
        }
    }

    @Override
    public void destroy() {
    }
}
