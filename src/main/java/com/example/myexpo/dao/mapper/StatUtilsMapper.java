package com.example.myexpo.dao.mapper;

import com.example.myexpo.util.StatUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class StatUtilsMapper implements GenericMapper<StatUtils> {
    @Override
    public StatUtils extractFromResultSet(ResultSet rs) throws SQLException {
        return StatUtils.builder()
                .id(rs.getInt("id"))
                .count(rs.getLong("count"))
                .title(rs.getString("title"))
                .build();
    }

    @Override
    public StatUtils makeUnique(Map<Integer, StatUtils> cache, StatUtils entity) {
        return null;
    }
}
