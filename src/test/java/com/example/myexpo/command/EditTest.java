package com.example.myexpo.command;

import com.example.myexpo.entity.Expo;
import com.example.myexpo.service.ExpoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditTest {

    Expo expoTest;

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    Edit edit = new Edit();
    ExpoService expoService = new ExpoService();

    @Before
    public void setUp() throws Exception {
        expoTest = Expo.builder()
                .imgName("60.jpg")
                .id(170)
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
        expoTest = expoService.addNewExpo(expoTest);
    }

    @After
    public void tearDown() throws Exception {
        expoService.deleteBy(expoTest.getId());
    }


    @Test
    public void execute() {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn("localhost:8080/myexpo/app/edit/" + expoTest.getId());
        assertEquals("redirect:/expo", edit.execute(request));
    }

    @Test
    public void execute2() {
        request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        assertEquals("/edit.jsp", edit.execute(request));
    }
}