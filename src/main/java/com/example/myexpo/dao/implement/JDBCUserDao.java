package com.example.myexpo.dao.implement;

import com.example.myexpo.dao.UserDao;
import com.example.myexpo.dao.mapper.RoleMapper;
import com.example.myexpo.dao.mapper.UserMapper;
import com.example.myexpo.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {

    static final Logger log = LogManager.getRootLogger();
    private ResourceBundle prop;
    private Connection connection;
    private String USER_CREATE;
    private String USER_CREATE_ROLE;
    private String USER_FIND_BY_ID;
    private String USER_FIND_BY_USERNAME;
    private String USER_FIND_ALL;
    private String USER_UPDATE;
    private String USER_UPDATE_ROLES;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
        prop = ResourceBundle.getBundle("statements");
        USER_CREATE = prop.getString("USER_CREATE");
        USER_CREATE_ROLE = prop.getString("USER_CREATE_ROLE");
        USER_FIND_BY_ID = prop.getString("USER_FIND_BY_ID");
        USER_FIND_BY_USERNAME = prop.getString("USER_FIND_BY_USERNAME");
        USER_FIND_ALL = prop.getString("USER_FIND_ALL");
        USER_UPDATE = prop.getString("USER_UPDATE");
        USER_UPDATE_ROLES = prop.getString("USER_UPDATE_ROLES");

    }

    @Override
    public Optional<User> create(User user) {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = connection.prepareStatement(USER_CREATE, Statement.RETURN_GENERATED_KEYS);
            pstmt2 = connection.prepareStatement(USER_CREATE_ROLE);
            pstmt = fillStatement(pstmt, user);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                int k = 1;
                pstmt2.setInt(k++, user.getId());
                pstmt2.setString(k++, User.Role.USER.name());
                pstmt2.executeUpdate();
            }
            connection.commit();
            return Optional.of(user);
        } catch (Exception e) {
            log.info("{}", "Couldn't create user " + e.getMessage());
            makeRollback(connection);
        } finally {
            close(rs, pstmt, pstmt2);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(int id) {
        Map<Integer, User> users = new HashMap<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(USER_FIND_BY_ID, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setInt(k++, id);
            return Optional.of(getUser(pstmt.executeQuery(), users));
        } catch (Exception e) {
            log.info("{}", "No user found " + e.getMessage());
        } finally {
            close(pstmt);
        }
        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        Map<Integer, User> users = new HashMap<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(USER_FIND_BY_USERNAME);
            int k = 1;
            pstmt.setString(k++, username);
            return Optional.of(getUser(pstmt.executeQuery(), users));
        } catch (Exception e) {
            log.info("{}", ": " + e.getMessage());
        } finally {
            close(pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        Map<Integer, User> users = new HashMap<>();
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(USER_FIND_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = userMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
                User.Role role = roleMapper.extractFromResultSet(rs);
                user.getRoles().add(role);
            }
            return Optional.of(new ArrayList<>(users.values()));
        } catch (Exception e) {
            log.info("{}", "No users found " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(USER_UPDATE);
            pstmt = fillStatement(pstmt, user);
            rs = pstmt.executeQuery();
            return Optional.of(user);
        } catch (Exception e) {
            log.info("{}", "No user found " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    public Optional<User> updateRoles(User user, User.Role role) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(USER_UPDATE_ROLES);
            int k = 1;
            pstmt.setInt(k++, user.getId());
            pstmt.setObject(k++, role);
            rs = pstmt.executeQuery();
            return Optional.of(user);
        } catch (Exception e) {
            log.info("{}", "No user found " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(User user) {
        throw new UnsupportedOperationException("Users can't be deleted!");
    }


    public void close(AutoCloseable... ac) {
        if (ac != null) {
            try {
                for (AutoCloseable a : ac) {
                    a.close();
                }
            } catch (Exception e) {
                log.info("{}", e.getMessage());
            }
        }
    }

    private User getUser(ResultSet rs, Map<Integer, User> users) throws SQLException {
        UserMapper userMapper = new UserMapper();
        RoleMapper roleMapper = new RoleMapper();
        User user = null;
        while (rs.next()) {
            user = userMapper.extractFromResultSet(rs);
            user = userMapper.makeUnique(users, user);
            User.Role role = roleMapper.extractFromResultSet(rs);
            user.getRoles().add(role);
        }
        return user;
    }


    private PreparedStatement fillStatement(PreparedStatement pstmt, User user) throws SQLException {
        int k = 1;
        pstmt.setBoolean(k++, user.isActive());
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getUsername());
        return pstmt;
    }

    private void makeRollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException throwables) {
            log.info("{}", "Couldnt rollback " + throwables.getMessage());
        }
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
