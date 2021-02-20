package com.example.myexpo.command;

import com.example.myexpo.entity.Expo;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AddTest {
    Expo expoTest;

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);

    Add add = spy(new Add());

    @Before
    public void setUp() {
        expoTest = Expo.builder()
                .imgName("60.jpg")
                .id(100)
                .holles(new HashSet<>())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .amount(20)
                .ticketPrice(120)
                .descriptionUa("some desc")
                .description("some desc")
                .title("title")
                .titleUa("titleUa")
                .exhibited(true)
                .build();
        when(request.getParameter("imgName")).thenReturn(expoTest.getImgName());
        when(request.getParameter("id")).thenReturn(expoTest.getId().toString());
        when(request.getParameter("holles")).thenReturn(expoTest.getHolles().toString());
        when(request.getParameter("startDate")).thenReturn(expoTest.getStartDate().toString());
        when(request.getParameter("endDate")).thenReturn(expoTest.getEndDate().toString());
        when(request.getParameter("amount")).thenReturn("" + expoTest.getAmount());
        when(request.getParameter("ticket_price")).thenReturn("" + expoTest.getTicketPrice());
        when(request.getParameter("description_ua")).thenReturn(expoTest.getDescriptionUa());
        when(request.getParameter("description")).thenReturn(expoTest.getDescription());
        when(request.getParameter("title")).thenReturn(expoTest.getTitle());
        when(request.getParameter("title_ua")).thenReturn(expoTest.getTitleUa());

    }


    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        add.execute(request);
        verify(add).execute(request);
    }

    @Test
    public void execute2() {
        request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        assertEquals("/add.jsp", add.execute(request));
    }


}