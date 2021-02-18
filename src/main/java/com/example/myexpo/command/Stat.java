package com.example.myexpo.command;

import com.example.myexpo.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Stat implements Command {

    static final Logger log = LogManager.getRootLogger();
    private final TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.getSession().setAttribute("tickets", ticketService.getViewStat());
        } catch (Exception e) {
            log.info("{}", "Cant get stat: " + e.getMessage());
        }
        return "/stat.jsp";
    }
}
