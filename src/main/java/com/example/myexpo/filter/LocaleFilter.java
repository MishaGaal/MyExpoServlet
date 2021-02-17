package com.example.myexpo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;


public class LocaleFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.getSession().setAttribute("lang", Optional.ofNullable(req.getParameter("sessionLocale")).orElse("en"));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}