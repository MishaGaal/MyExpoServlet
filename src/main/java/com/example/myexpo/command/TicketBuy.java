package com.example.myexpo.command;

import com.example.myexpo.entity.User;
import com.example.myexpo.service.TicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;


public class TicketBuy implements Command {
    static final Logger log = LogManager.getRootLogger();
    private final TicketService ticketService = new TicketService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            ticketService.buyTicket(CommandUtility.extractId(request), (User) request.getSession().getAttribute("user"));
        } catch (Exception e) {
            log.info("{}", "Cant buy ticket: " + e.getMessage());
            return "redirect:/main";
        }
        return "redirect:/ticket";
    }

}
