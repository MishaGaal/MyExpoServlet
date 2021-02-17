package com.example.myexpo.command;

import com.example.myexpo.entity.User;
import com.example.myexpo.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class TicketBuy implements Command {
    static final Logger log = LogManager.getRootLogger();
    private TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String path = request.getRequestURI();
            String token = path.replaceAll("\\/buy", "");
            Integer id = Integer.parseInt(token.replaceAll(".*\\/ticket\\/", ""));
            ticketService.buyTicket(id, (User) request.getSession().getAttribute("user"));
        } catch (Exception e) {
            log.info("{}", "Cant buy ticket: " + e.getMessage());
            return "redirect:/main";
        }
        return "redirect:/ticket";
    }

}
