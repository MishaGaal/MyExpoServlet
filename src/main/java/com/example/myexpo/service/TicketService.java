package com.example.myexpo.service;

import com.example.myexpo.dao.DaoFactory;
import com.example.myexpo.dao.TicketDao;
import com.example.myexpo.entity.Ticket;
import com.example.myexpo.entity.User;
import com.example.myexpo.util.StatUtils;

import java.util.List;
import java.util.ResourceBundle;

public class TicketService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    ResourceBundle prop = ResourceBundle.getBundle("statements");
    Integer amount = Integer.parseInt(prop.getString("BUY_AMOUNT"));

    public List<Ticket> getUserTickets(User user) throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.findAllByUser(user).orElseThrow(() -> new Exception("no user tickets have been found"));
        }
    }

    public Ticket buyTicket(Integer id, User user) throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.create(id, user, amount).orElseThrow(() -> new Exception("transaction rollback"));
        }
    }

    public List<StatUtils> getViewStat() throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.countAllByOrderByExpo().orElseThrow(() -> new Exception("couldn't load stat"));
        }
    }
}
