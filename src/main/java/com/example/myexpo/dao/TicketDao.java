package com.example.myexpo.dao;

import com.example.myexpo.entity.Ticket;
import com.example.myexpo.entity.User;
import com.example.myexpo.util.StatUtils;

import java.util.List;
import java.util.Optional;

public interface TicketDao extends GenericDao<Ticket> {
    Optional<List<Ticket>> findAllByUser(User user);

    Optional<List<StatUtils>> countAllByOrderByExpo();

    Optional<Ticket> create(Integer id, User user);

}
