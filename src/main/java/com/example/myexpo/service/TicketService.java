package com.example.myexpo.service;

import com.example.myexpo.dao.DaoFactory;
import com.example.myexpo.dao.TicketDao;
import com.example.myexpo.entity.Ticket;
import com.example.myexpo.entity.User;
import com.example.myexpo.util.StatUtils;

import java.util.List;

public class TicketService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Ticket> getUserTickets(User user) throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.findAllByUser(user).orElseThrow(() -> new Exception("no exhibited have been found"));
        }
    }

    public Ticket buyTicket(Integer id, User user) throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.create(id, user).orElseThrow(() -> new Exception("transaction rollback"));
        }
    }

    public List<StatUtils> getViewStat() throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.countAllByOrderByExpo().orElseThrow(() -> new Exception("could load stat"));
        }
    }
}
