package com.example.myexpo.dao;


import com.example.myexpo.entity.Expo;
import com.example.myexpo.util.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface ExpoDao extends GenericDao<Expo> {
    Optional<Page<Expo>> findAll(int page);

    Optional<Page<Expo>> findExhibitedAll(int page);

    Optional<Page<Expo>> findByExhibitedTrueOrderByPriceDesc(int page);

    Optional<Page<Expo>> findByExhibitedTrueOrderByPriceAsc(int page);

    Optional<Page<Expo>> findByExhibitedTrueOrderByDates(LocalDate startDate, LocalDate endDate, int page);

    Optional<Page<Expo>> findByExhibitedTrueOrderByTheme(String theme, int page);

    Optional<Expo> expoSubmit(Expo expo);

    boolean delete(Integer id);
}
