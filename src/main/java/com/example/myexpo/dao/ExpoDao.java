package com.example.myexpo.dao;


import com.example.myexpo.entity.Expo;
import com.example.myexpo.util.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface ExpoDao extends GenericDao<Expo> {
    Page<Expo> findAll(int page);

    Page<Expo> findExhibitedAll(int page);

    Page<Expo> findByExhibitedTrueOrderByPriceDesc(int page);

    Page<Expo> findByExhibitedTrueOrderByPriceAsc(int page);

    Page<Expo> findByExhibitedTrueOrderByDates(LocalDate startDate, LocalDate endDate, int page);

    Page<Expo> findByExhibitedTrueOrderByTheme(String theme, int page);

    Optional<Expo> expoSubmit(Expo expo);

    boolean delete(Integer id);
}
