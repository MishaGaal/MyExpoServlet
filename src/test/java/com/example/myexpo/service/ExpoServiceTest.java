package com.example.myexpo.service;

import com.example.myexpo.dao.DaoFactory;
import com.example.myexpo.dao.ExpoDao;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.util.Page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ExpoServiceTest {


    DaoFactory daoFactory;
    ExpoDao dao;
    ExpoService expoService = new ExpoService();
    Page<Expo> res;

    @Before
    public void setUp() throws Exception {
    /*    daoFactory = mock(DaoFactory.class);
        dao = mock(ExpoDao.class);
        Expo[] s = {new Expo()};
        res = new Page(Arrays.asList(s),1);
        when(daoFactory.createExpoDao()).thenReturn(dao);
        when(dao.findAll(0)).thenReturn(res);
*/
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getExhibited() throws Exception {
        Expo[] s = {new Expo()};
        Page<Expo> res = new Page(Arrays.asList(s), 1);
        when(expoService.getExhibited(0)).thenReturn(res);
        Page<Expo> expos = expoService.getExhibited(0);
        Mockito.verify(expoService, Mockito.times(1)).getExhibited(0);
        assertEquals(res, expos);
    }

    @org.junit.Test
    public void findByExhibitedTrueOrderByPriceDesc() {

    }

    @org.junit.Test
    public void findByExhibitedTrueOrderByPriceAsc() {

    }

    @org.junit.Test
    public void findByExhibitedTrueOrderByDates() {

    }

    @org.junit.Test
    public void findByExhibitedTrueOrderByTheme() {

    }

    @Test
    public void getAllExpos() throws Exception {
        Page<Expo> expos = expoService.getAllExpos(0);
        Mockito.verify(dao, Mockito.times(1)).findAll(0);
        assertEquals(res, expos);
    }

    @org.junit.Test
    public void expoSubmit() {

    }

    @org.junit.Test
    public void findById() {

    }

    @org.junit.Test
    public void addNewExpo() {

    }
}