package com.example.myexpo.dao.mapper;

import com.example.myexpo.entity.Expo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

public class ExpoMapper implements GenericMapper<Expo> {
    @Override
    public Expo extractFromResultSet(ResultSet rs) throws SQLException {
        return Expo.builder()
                .id(rs.getInt("id"))
                .amount(rs.getInt("amount"))
                .description(rs.getString("description"))
                .descriptionUa(rs.getString("description_ua"))
                .endDate(rs.getDate("end_date").toLocalDate())
                .startDate(rs.getDate("start_date").toLocalDate())
                .exhibited(rs.getBoolean("exhibited"))
                .imgName(rs.getString("img_name"))
                .ticketPrice(rs.getDouble("ticket_price"))
                .title(rs.getString("title"))
                .titleUa(rs.getString("title_ua"))
                .holles(new HashSet<>())
                .build();
    }

    @Override
    public Expo makeUnique(Map<Integer, Expo> cache, Expo entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
