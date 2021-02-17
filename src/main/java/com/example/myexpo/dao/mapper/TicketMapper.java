package com.example.myexpo.dao.mapper;

import com.example.myexpo.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TicketMapper implements GenericMapper<Ticket> {
    @Override
    public Ticket extractFromResultSet(ResultSet rs) throws SQLException {

        return Ticket.builder()
                .id(rs.getInt("ticket"))
                .build();
    }

    @Override
    public Ticket makeUnique(Map<Integer, Ticket> cache, Ticket entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }


}
