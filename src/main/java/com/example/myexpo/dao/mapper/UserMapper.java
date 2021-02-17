package com.example.myexpo.dao.mapper;

import com.example.myexpo.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

public class UserMapper implements GenericMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {

        return User.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .roles(new HashSet<>())
                .build();
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User user) {
        cache.putIfAbsent(user.getId(), user);
        return cache.get(user.getId());
    }
}
