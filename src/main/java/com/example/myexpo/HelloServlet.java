package com.example.myexpo;

import com.example.myexpo.command.*;
import com.example.myexpo.util.Validator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


//TODO fix cache pages!


public class HelloServlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());


        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("main", new Main());
        commands.put("expos/desc", new FilterDesc());
        commands.put("expos/asc", new FilterAsc());
        commands.put("expos/dates", new FilterDates());
        commands.put("expos/theme", new FilterTheme());
        commands.put("expo", new Expo());
        commands.put("ticket", new Ticket());
        commands.put("buy", new TicketBuy());
        commands.put("stat", new Stat());
        commands.put("user", new User());
        commands.put("edit", new Edit());
        commands.put("expo/add", new Add());
        commands.put("delete", new Delete());
        commands.put("/myexpo/app", new Index());

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String page = Validator.processPath(commands, request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/myexpo/app"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}