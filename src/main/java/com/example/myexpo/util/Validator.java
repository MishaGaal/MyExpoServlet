package com.example.myexpo.util;

import com.example.myexpo.command.Command;
import com.example.myexpo.dto.ExpoDto;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Validator {

    public static int getPageNum(HttpServletRequest request) {
        if (request.getParameter("page") != null) {
            return Integer.parseInt(request.getParameter("page"));
        } else return 0;
    }

    public static Set<Expo.Holle> extractHolles(String[] holles) {
        Set<Expo.Holle> res = new HashSet<>();
        if (holles != null) {
            for (String holle : holles) {
                if ("RED".equals(holle)) {
                    res.add(Expo.Holle.RED);
                }
                if ("BLUE".equals(holle)) {
                    res.add(Expo.Holle.BLUE);
                }
                if ("GREEN".equals(holle)) {
                    res.add(Expo.Holle.GREEN);
                }
            }
        }
        return res;
    }


    public static String processPath(Map<String, Command> commands, HttpServletRequest request) {
        String path = request.getRequestURI();
        System.out.println(path);
        path = path.replaceAll(".*/app/", "");
        System.out.println(path);
        Command command = commands.getOrDefault(path,
                (r) -> "/index.jsp");
        if (path.matches("ticket\\/[0-9]+\\/buy")) {
            command = commands.get("buy");
        }
        if (path.matches("expo\\/edit\\/[0-9]+")) {
            command = commands.get("edit");
        }
        if (path.matches("expo\\/delete\\/[0-9]+")) {
            command = commands.get("delete");
        }
        return command.execute(request);
    }

    public static boolean validate(User user, String pc) throws Exception {
        if (user.getPassword().length() < 6) {
            throw new Exception("Password is too short");
        }
        if (user.getUsername().length() < 1) {
            throw new Exception("Username is too short");
        }
        if (!user.getPassword().equals(pc)) {
            throw new Exception("Passwords are different!");
        }
        if (!user.getEmail().matches("^[A-Za-z]+@([A-Za-z]+\\.)+[A-Za-z]{2,4}$")) {
            throw new Exception("Enter email like : some@some.com");
        }
        return true;
    }


    public static ExpoDto createDto(HttpServletRequest request) throws Exception {
        return ExpoDto.builder()
                .titleUa(request.getParameter("title_ua"))
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .descriptionUa(request.getParameter("description_ua"))
                .imgName(request.getParameter("imgName"))
                .ticketPrice(request.getParameter("ticket_price"))
                .amount(request.getParameter("amount"))
                .startDate(request.getParameter("startDate"))
                .endDate(request.getParameter("endDate"))
                .build();

    }

    public static boolean validate(HttpServletRequest request) throws Exception {
        if (request.getParameter("title_ua").length() < 1) {
            throw new Exception("title_ua is too short!");
        }
        if (request.getParameter("title").length() < 1) {
            throw new Exception("title is too short!");
        }
        if (request.getParameter("description").length() < 1) {
            throw new Exception("description is too short!");
        }
        if (request.getParameter("description_ua").length() < 1) {
            throw new Exception("description_ua is too short!");
        }
        if (request.getParameter("imgName").length() < 1) {
            throw new Exception("default image: 60.jpg");
        }
        if (request.getParameter("ticket_price").length() < 1) {
            throw new Exception("Wrong price!");
        }
        if (request.getParameter("amount").length() < 1) {
            throw new Exception("Wrong amount!");
        }
        if (request.getParameter("startDate").length() < 1) {
            throw new Exception("Set start date!");
        }
        if (request.getParameter("endDate").length() < 1) {
            throw new Exception("Set end date!");
        }
        return true;
    }


    public static Expo buildExpo(HttpServletRequest request) {
        return Expo.builder()
                .holles(Validator.extractHolles(request.getParameterValues("holles")))
                .titleUa(request.getParameter("title_ua"))
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .descriptionUa(request.getParameter("description_ua"))
                .imgName(request.getParameter("imgName"))
                .ticketPrice(Double.parseDouble(request.getParameter("ticket_price")))
                .amount(Integer.parseInt(request.getParameter("amount")))
                .startDate(LocalDate.parse(request.getParameter("startDate")))
                .endDate(LocalDate.parse(request.getParameter("endDate")))
                .build();
    }
}
