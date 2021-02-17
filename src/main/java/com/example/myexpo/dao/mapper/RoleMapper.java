package com.example.myexpo.dao.mapper;

import com.example.myexpo.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoleMapper {


    public User.Role extractFromResultSet(ResultSet rs) throws SQLException {
        if ("USER".equals(rs.getString("roles"))) {
            return User.Role.USER;
        }
        if ("ADMIN".equals((rs.getString("roles")))) {
            return User.Role.ADMIN;
        }
        throw new SQLException("no roles");
    }


    public User.Role makeUnique(Map<Integer, User.Role> cache, User.Role entity, Integer userId) {
        cache.putIfAbsent(userId, entity);
        return cache.get(userId);
    }
}
