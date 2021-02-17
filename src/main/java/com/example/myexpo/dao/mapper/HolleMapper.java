package com.example.myexpo.dao.mapper;

import com.example.myexpo.entity.Expo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class HolleMapper {


    public Expo.Holle extractFromResultSet(ResultSet rs) throws SQLException {
        if ("RED".equals(rs.getString("holles"))) {
            return Expo.Holle.RED;
        }
        if ("GREEN".equals((rs.getString("holles")))) {
            return Expo.Holle.GREEN;
        }
        if ("BLUE".equals((rs.getString("holles")))) {
            return Expo.Holle.BLUE;
        }
        return null;
    }


    public Expo.Holle makeUnique(Map<Integer, Expo.Holle> cache, Expo.Holle entity, Integer expoId) {
        cache.putIfAbsent(expoId, entity);
        return cache.get(expoId);
    }
}
