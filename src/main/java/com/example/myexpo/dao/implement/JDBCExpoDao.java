package com.example.myexpo.dao.implement;

import com.example.myexpo.dao.ExpoDao;
import com.example.myexpo.dao.mapper.ExpoMapper;
import com.example.myexpo.dao.mapper.HolleMapper;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.util.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class JDBCExpoDao implements ExpoDao {

    static final Logger log = LogManager.getRootLogger();
    private ResourceBundle prop;
    private Connection connection;
    private String EXPO_FIND_ALL;
    private String EXPO_FIND_HOLLES;
    private String EXPO_FIND_BY_ID;
    private String EXPO_CREATE;
    private String EXPO_UPDATE_HOLLES;
    private String EXPO_UPDATE;
    private String EXPO_DELETE;
    private String EXPO_DELETE_HOLLES;
    private String EXPO_FIND_ALL_EXH;
    private String EXPO_FIND_ALL_EXH_PRICE_DESC;
    private String EXPO_FIND_ALL_EXH_PRICE_ASC;
    private String EXPO_FIND_ALL_EXH_DATES;
    private String EXPO_FIND_ALL_EXH_THEME;
    private String EXPO_MAX_PAGES;
    private Integer limit;


    public JDBCExpoDao(Connection connection) {
        this.connection = connection;
        prop = ResourceBundle.getBundle("statements");
        EXPO_FIND_ALL = prop.getString("EXPO_FIND_ALL");
        EXPO_FIND_HOLLES = prop.getString("EXPO_FIND_HOLLES");
        EXPO_FIND_BY_ID = prop.getString("EXPO_FIND_BY_ID");
        EXPO_CREATE = prop.getString("EXPO_CREATE");
        EXPO_UPDATE_HOLLES = prop.getString("EXPO_UPDATE_HOLLES");
        EXPO_UPDATE = prop.getString("EXPO_UPDATE");
        EXPO_DELETE = prop.getString("EXPO_DELETE");
        EXPO_DELETE_HOLLES = prop.getString("EXPO_DELETE_HOLLES");
        EXPO_FIND_ALL_EXH = prop.getString("EXPO_FIND_ALL_EXH");
        EXPO_FIND_ALL_EXH_PRICE_DESC = prop.getString("EXPO_FIND_ALL_EXH_PRICE_DESC");
        EXPO_FIND_ALL_EXH_PRICE_ASC = prop.getString("EXPO_FIND_ALL_EXH_PRICE_ASC");
        EXPO_FIND_ALL_EXH_DATES = prop.getString("EXPO_FIND_ALL_EXH_DATES");
        EXPO_FIND_ALL_EXH_THEME = prop.getString("EXPO_FIND_ALL_EXH_THEME");
        EXPO_MAX_PAGES = prop.getString("EXPO_MAX_PAGES");
        limit = Integer.parseInt(prop.getString("EXPO_PAGE_LIMIT"));

    }

    private static String escapeForLike(String param) {
        return param
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }

    @Override
    public Optional<Expo> create(Expo expo) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = connection.prepareStatement(EXPO_CREATE, Statement.RETURN_GENERATED_KEYS);
            pstmt = fillStatement(pstmt, expo);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                expo.setId(rs.getInt(1));
                pstmt = setUpHolles(expo, connection);
            }
            connection.commit();
            return Optional.of(expo);
        } catch (Exception e) {
            log.info("{}", "Couldn't create expo " + e.getMessage());
            makeRollback(connection);
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Expo> findById(int id) {
        Map<Integer, Expo> expos = new HashMap<>();
        ExpoMapper expoMapper = new ExpoMapper();
        HolleMapper holleMapper = new HolleMapper();
        Expo expo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(EXPO_FIND_BY_ID);
            int k = 1;
            pstmt.setInt(k++, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                expo = expoMapper.extractFromResultSet(rs);
                expo = expoMapper.makeUnique(expos, expo);
                Expo.Holle holle = holleMapper.extractFromResultSet(rs);
                if (holle != null) {
                    expo.getHolles().add(holle);
                }
            }
            return Optional.of(expo);
        } catch (Exception e) {
            log.info("{}", "No expo found " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Expo>> findAll() {
        return Optional.empty();
    }

    public Page<Expo> findAll(int page) {
        Map<Integer, Expo> expos = new HashMap<>();
        ExpoMapper expoMapper = new ExpoMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = connection.prepareStatement(EXPO_FIND_ALL);
            int k = 1;
            pstmt.setInt(k++, page * limit);
            pstmt.setInt(k++, limit);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expo expo = expoMapper
                        .extractFromResultSet(rs);
                expo = expoMapper.makeUnique(expos, expo);
                count = rs.getInt("count");
            }
            return new Page<>(new ArrayList<>(expos.values()), count / limit);
        } catch (Exception e) {
            log.info("{}", "Couldn't find expos " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return new Page<>(new ArrayList<>(expos.values()), count);
    }

    public Page<Expo> findExhibitedAll(int page) {
        return getExpos(page, EXPO_FIND_ALL_EXH);
    }

    public Page<Expo> findByExhibitedTrueOrderByPriceDesc(int page) {
        return getExpos(page, EXPO_FIND_ALL_EXH_PRICE_DESC);
    }

    public Page<Expo> findByExhibitedTrueOrderByPriceAsc(int page) {
        return getExpos(page, EXPO_FIND_ALL_EXH_PRICE_ASC);
    }

    private Page<Expo> getExpos(int page, String expo_find_all_exh) {
        List<Expo> res = new ArrayList<>();
        ExpoMapper expoMapper = new ExpoMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = connection.prepareStatement(expo_find_all_exh);
            int k = 1;
            pstmt.setInt(k++, page * limit);
            pstmt.setInt(k++, limit);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expo expo = expoMapper
                        .extractFromResultSet(rs);
                res.add(expo);
                count = rs.getInt("count");
            }
            return new Page<>(res, count / limit);
        } catch (Exception e) {
            log.info("{}", "Couldn't find expos " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return new Page<>(res, count);
    }

    public Page<Expo> findByExhibitedTrueOrderByDates(LocalDate startDate, LocalDate endDate, int page) {
        List<Expo> res = new ArrayList<>();
        ExpoMapper expoMapper = new ExpoMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = connection.prepareStatement(EXPO_FIND_ALL_EXH_DATES);
            int k = 1;
            pstmt.setObject(k++, startDate);
            pstmt.setObject(k++, endDate);
            pstmt.setObject(k++, startDate);
            pstmt.setObject(k++, endDate);
            pstmt.setInt(k++, page * limit);
            pstmt.setInt(k++, limit);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expo expo = expoMapper
                        .extractFromResultSet(rs);
                res.add(expo);
                count = rs.getInt("count");
            }
            return new Page<>(res, count / limit);
        } catch (Exception e) {
            log.info("{}", "Couldn't find expos " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return new Page<>(res, count);
    }

    public Page<Expo> findByExhibitedTrueOrderByTheme(String theme, int page) {
        List<Expo> res = new ArrayList<>();
        ExpoMapper expoMapper = new ExpoMapper();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = connection.prepareStatement(EXPO_FIND_ALL_EXH_THEME);
            int k = 1;
            pstmt.setString(k++, "%" + escapeForLike(theme) + "%");
            pstmt.setString(k++, "%" + escapeForLike(theme) + "%");
            pstmt.setInt(k++, page * limit);
            pstmt.setInt(k++, limit);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expo expo = expoMapper
                        .extractFromResultSet(rs);
                res.add(expo);
                count = rs.getInt("count");
            }
            return new Page<>(res, count / limit);
        } catch (Exception e) {
            log.info("{}", "Couldn't find expos " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return new Page<>(res, count);
    }

    @Override
    public Optional<Expo> expoSubmit(Expo expo) {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = connection.prepareStatement(EXPO_UPDATE);
            pstmt = fillStatement(pstmt, expo);
            pstmt.setInt(11, expo.getId());
            pstmt.executeUpdate();
            pstmt = setUpHolles(expo, connection);
            connection.commit();
            return Optional.of(expo);
        } catch (Exception e) {
            log.info("{}", "Couldn't find expos " + e.getMessage());
            makeRollback(connection);
        } finally {
            close(pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Expo> update(Expo expo) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(EXPO_UPDATE);
            pstmt = fillStatement(pstmt, expo);
            pstmt.setInt(11, expo.getId());
            rs = pstmt.executeQuery();
            return Optional.of(expo);
        } catch (Exception e) {
            log.info("{}", "Couldn't find expos " + e.getMessage());
        } finally {
            close(rs, pstmt);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Expo> delete(Expo entity) {
        throw new UnsupportedOperationException();
    }

    public boolean delete(Integer id) {
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            pstmt = connection.prepareStatement(EXPO_DELETE_HOLLES);
            pstmt2 = connection.prepareStatement(EXPO_DELETE);
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            int k = 1;
            pstmt.setInt(k, id);
            pstmt2.setInt(k, id);
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            connection.commit();
            return true;
        } catch (Exception e) {
            log.info("{}", "No user deleted " + e.getMessage());
            makeRollback(connection);
        } finally {
            close(pstmt, pstmt2);
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    private PreparedStatement fillStatement(PreparedStatement pstmt, Expo expo) throws SQLException {
        int k = 1;
        pstmt.setInt(k++, expo.getAmount());
        pstmt.setString(k++, expo.getDescription());
        pstmt.setString(k++, expo.getDescriptionUa());
        pstmt.setDate(k++, Date.valueOf(expo.getEndDate()));
        pstmt.setBoolean(k++, expo.isExhibited());
        pstmt.setString(k++, expo.getImgName());
        pstmt.setDate(k++, Date.valueOf(expo.getStartDate()));
        pstmt.setDouble(k++, expo.getTicketPrice());
        pstmt.setString(k++, expo.getTitle());
        pstmt.setString(k++, expo.getTitleUa());
        return pstmt;
    }

    private PreparedStatement setUpHolles(Expo expo, Connection connection) throws SQLException {
        PreparedStatement pstmt = null;
        for (Expo.Holle holle : expo.getHolles()) {
            pstmt = connection.prepareStatement(EXPO_UPDATE_HOLLES);
            int x = 1;
            pstmt.setInt(x++, expo.getId());
            pstmt.setString(x++, holle.name());
            pstmt.executeUpdate();
        }
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


}
