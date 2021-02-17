package com.example.myexpo.service;

import com.example.myexpo.dao.DaoFactory;
import com.example.myexpo.dao.ExpoDao;
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
        try (TicketDao dao = daoFactory.createTicketDao(); ExpoDao eDao = daoFactory.createExpoDao()) {
            return dao.create(
                    new Ticket(eDao.findById(id)
                            .orElseThrow(() -> new Exception("can't find such expo")), user))
                    .orElseThrow(() -> new Exception("no ticket created"));
        }
    }

    public List<StatUtils> getViewStat() throws Exception {
        try (TicketDao dao = daoFactory.createTicketDao()) {
            return dao.countAllByOrderByExpo()
                    .orElseThrow(() -> new Exception("no ticket created"));

        }
    }
}
