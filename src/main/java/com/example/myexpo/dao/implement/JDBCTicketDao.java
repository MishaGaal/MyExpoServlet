package com.example.myexpo.dao.implement;

import com.example.myexpo.dao.TicketDao;
import com.example.myexpo.dao.mapper.ExpoMapper;
import com.example.myexpo.dao.mapper.HolleMapper;
import com.example.myexpo.dao.mapper.StatUtilsMapper;
import com.example.myexpo.dao.mapper.TicketMapper;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.entity.Ticket;
import com.example.myexpo.entity.User;
import com.example.myexpo.util.StatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class JDBCTicketDao implements TicketDao {

    static final Logger log = LogManager.getRootLogger();
    private final Connection connection;
    private final String TICKET_USER;
    private final String TICKET_BUY;
    private final String UPDATE_AMOUNT;
    private final String TICKET_STAT;


    public JDBCTicketDao(Connection connection) {
        this.connection = connection;
        ResourceBundle prop = ResourceBundle.getBundle("statements");
        TICKET_USER = prop.getString("TICKET_USER");
        TICKET_BUY = prop.getString("TICKET_BUY");
        UPDATE_AMOUNT = prop.getString("UPDATE_AMOUNT");
        TICKET_STAT = prop.getString("TICKET_STAT");

    }


    @Override
    public Optional<Ticket> create(Integer expo_id, User user) {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        Ticket ticket = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = connection.prepareStatement(TICKET_BUY, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setInt(k++, expo_id);
            pstmt.setInt(k++, user.getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                ticket = new Ticket();
                ticket.setId(rs.getInt(1));
            }
            pstmt2 = connection.prepareStatement(UPDATE_AMOUNT);
            int x = 1;
            pstmt2.setInt(x++, expo_id);
            pstmt2.executeUpdate();
            connection.commit();
            return Optional.of(ticket);
        } catch (Exception e) {
            log.info("{}", "Couldn't buy ticket " + e.getMessage());
            makeRollback(connection);
        } finally {
            close(rs, pstmt, pstmt2);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Ticket> create(Ticket ticket) {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = connection.prepareStatement(TICKET_BUY);
            int k = 1;
            pstmt.setInt(k++, ticket.getExpo().getId());
            pstmt.setInt(k++, ticket.getUser().getId());
            pstmt.executeUpdate();
            connection.commit();
            return Optional.of(ticket);
        } catch (Exception e) {
            log.info("{}", "Couldn't buy ticket " + e.getMessage());
            makeRollback(connection);
        } finally {
            close(pstmt);
        }
        return Optional.empty();
    }


    @Override
    public Optional<Ticket> findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<List<Ticket>> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Ticket> update(Ticket entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Ticket> delete(Ticket entity) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<List<Ticket>> findAllByUser(User user) {
        Map<Integer, Expo> expos = new HashMap<>();
        Map<Integer, Ticket> tickets = new HashMap<>();
        ExpoMapper expoMapper = new ExpoMapper();
        TicketMapper ticketMapper = new TicketMapper();
        HolleMapper holleMapper = new HolleMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(TICKET_USER);
            int k = 1;
            pstmt.setInt(k++, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expo expo = expoMapper
                        .extractFromResultSet(rs);
                expo = expoMapper
                        .makeUnique(expos, expo);
                Ticket ticket = ticketMapper
                        .extractFromResultSet(rs);
                ticket = ticketMapper
                        .makeUnique(tickets, ticket);
                Expo.Holle holle = holleMapper
                        .extractFromResultSet(rs);
                expo.getHolles().add(holle);
                ticket.setExpo(expo);
            }
            return Optional.of(new ArrayList<>(tickets.values()));
        } catch (Exception e) {
            log.info("{}", "No tickets has been found " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<StatUtils>> countAllByOrderByExpo() {
        List<StatUtils> res = new ArrayList<>();
        StatUtilsMapper statUtilsMapper = new StatUtilsMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(TICKET_STAT);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StatUtils stat = statUtilsMapper.extractFromResultSet(rs);
                res.add(stat);
            }
            return Optional.of(res);
        } catch (Exception e) {
            log.info("{}", "No tickets has been found " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void close(AutoCloseable... ac) {
        if (ac != null) {
            try {
                for (AutoCloseable a : ac) {
                    a.close();
                }
            } catch (Exception e) {
                log.info("{}", "AutoCloseable: " + e);
            }
        }
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

}
