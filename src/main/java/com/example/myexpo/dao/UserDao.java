package com.example.myexpo.dao;

import com.example.myexpo.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByUsername(String username);
}
