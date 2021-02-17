package com.example.myexpo.command;

import com.example.myexpo.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Stat implements Command {

    static final Logger log = LogManager.getRootLogger();
    private TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("tickets", ticketService.getViewStat());
        } catch (Exception e) {
            log.info("{}", "Cant get stat: " + e.getMessage());
        }
        return "/stat.jsp";
    }
}
