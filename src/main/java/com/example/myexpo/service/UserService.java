package com.example.myexpo.service;

import com.example.myexpo.dao.DaoFactory;
import com.example.myexpo.dao.UserDao;
import com.example.myexpo.entity.User;

import java.util.List;


public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.findAll().orElseThrow(() -> new Exception("no users have been found"));
        }
    }

    public User loginUser(String name, String pass) throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            User user = dao.findByUsername(name).orElseThrow(() -> new IllegalAccessException("No such user!"));
            if (user.getPassword().equals(pass)) {
                return user;
            }
            throw new IllegalAccessException("Passwords didn't match!");
        }
    }

    public User registerUser(User user) throws Exception {
        try (UserDao dao = daoFactory.createUserDao()) {
            return dao.create(user).orElseThrow(() -> new Exception("Username already exists!"));
        }
    }
}
