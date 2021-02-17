package com.example.myexpo.dao;

import com.example.myexpo.dao.implement.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao();

    public abstract ExpoDao createExpoDao();

    public abstract TicketDao createTicketDao();
}
