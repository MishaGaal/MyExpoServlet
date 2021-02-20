package com.example.myexpo.command;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterDatesTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    FilterDates filterDates = new FilterDates();

    @Before
    public void setUp() {
        when(request.getParameter("startDate")).thenReturn(LocalDate.now().toString());
        when(request.getParameter("endDate")).thenReturn(LocalDate.now().toString());

    }

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("localhost:8080/myexpo/app/expos/dates");
        assertEquals("/main.jsp", filterDates.execute(request));
    }
}