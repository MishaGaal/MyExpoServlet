package com.example.myexpo;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class HelloServletTest {

    private static final HelloServlet servlet = new HelloServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);

    @Test
    public void index() throws Exception {
        when(request.getRequestURI()).thenReturn("localhost:8080/myexpo/app");
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher(anyString());
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }


}