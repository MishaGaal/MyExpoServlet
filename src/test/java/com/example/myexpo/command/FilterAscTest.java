package com.example.myexpo.command;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterAscTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    FilterAsc filterAsc = new FilterAsc();

    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("localhost:8080/myexpo/app/expos/asc");
        assertEquals("/main.jsp", filterAsc.execute(request));
    }
}