package com.example.myexpo.command;

import com.example.myexpo.entity.User;
import com.example.myexpo.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Ticket implements Command {
    static final Logger log = LogManager.getRootLogger();
    private final TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("tickets", ticketService.getUserTickets((User) session.getAttribute("user")));
        } catch (Exception e) {
            log.info("{}", "Cant get tickets: " + e.getMessage());
        }
        return "/ticket.jsp";
    }
}
