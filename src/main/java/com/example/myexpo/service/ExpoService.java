package com.example.myexpo.service;

import com.example.myexpo.dao.DaoFactory;
import com.example.myexpo.dao.ExpoDao;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.util.Page;

import java.time.LocalDate;

public class ExpoService {

    DaoFactory daoFactory = DaoFactory.getInstance();


    public Page<Expo> getExhibited(int page) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findExhibitedAll(page).orElseThrow(() -> new Exception("no exhibited have been found"));
        }
    }

    public Page<Expo> findByExhibitedTrueOrderByPriceDesc(int page) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findByExhibitedTrueOrderByPriceDesc(page).orElseThrow(() -> new Exception("no expos been found"));
        }
    }

    public Page<Expo> findByExhibitedTrueOrderByPriceAsc(int page) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findByExhibitedTrueOrderByPriceAsc(page).orElseThrow(() -> new Exception("no expos have been found"));
        }
    }

    public Page<Expo> findByExhibitedTrueOrderByDates(LocalDate startDate, LocalDate endDate, int page) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findByExhibitedTrueOrderByDates(startDate, endDate, page).orElseThrow(() -> new Exception("no expos have been found"));
        }
    }

    public Page<Expo> findByExhibitedTrueOrderByTheme(String theme, int page) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findByExhibitedTrueOrderByTheme(theme, page).orElseThrow(() -> new Exception("no expos have been found"));
        }
    }


    public Page<Expo> getAllExpos(int page) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findAll(page).orElseThrow(() -> new Exception("no expos have been found"));
        }
    }


    public Expo expoSubmit(Expo expo) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            if (expo.getHolles().size() > 0) {
                expo.setExhibited(true);
            }
            return dao.expoSubmit(expo).orElseThrow(() -> new Exception("expo couldn't update"));
        }
    }

    public Expo findById(Integer id) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.findById(id).orElseThrow(() -> new Exception("no expo have been found"));
        }
    }


    public Expo addNewExpo(Expo expo) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            if (expo.getHolles().size() > 0) {
                expo.setExhibited(true);
            }
            return dao.create(expo).orElseThrow(() -> new Exception("expo couldn't create"));
        }
    }

    public boolean deleteBy(Integer id) throws Exception {
        try (ExpoDao dao = daoFactory.createExpoDao()) {
            return dao.delete(id);
        }
    }
}
