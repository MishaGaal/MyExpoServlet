package com.example.myexpo.command;


import com.example.myexpo.dto.ExpoDto;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class CommandUtility {

    public static User buildUser(HttpServletRequest request) {
        return User.builder()
                .username(request.getParameter("name"))
                .password(request.getParameter("password"))
                .email(request.getParameter("email"))
                .active(true)
                .roles(Collections.singleton(User.Role.USER))
                .build();
    }

    public static Expo buildExpo(HttpServletRequest request) {
        return Expo.builder()
                .holles(extractHolles(Optional.ofNullable(request.getParameterValues("holles")).orElse(new String[0])))
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

    public static ExpoDto createDto(HttpServletRequest request) {
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


    public static Set<Expo.Holle> extractHolles(String[] holles) {
        Set<Expo.Holle> res = new HashSet<>();

            /*for (String holle : holles) {
                if ("RED".equals(holle)) {
                    res.add(Expo.Holle.RED);
                }
                if ("BLUE".equals(holle)) {
                    res.add(Expo.Holle.BLUE);
                }
                if ("GREEN".equals(holle)) {
                    res.add(Expo.Holle.GREEN);
                }
            }*/
        Arrays.stream(holles).filter("RED"::equals)
                .forEach(i -> res.add(Expo.Holle.RED));
        Arrays.stream(holles).filter("BLUE"::equals)
                .forEach(i -> res.add(Expo.Holle.BLUE));
        Arrays.stream(holles).filter("GREEN"::equals)
                .forEach(i -> res.add(Expo.Holle.GREEN));

        return res;
    }


    public static String processPath(Map<String, Command> commands, HttpServletRequest request) {
        String path = request.getRequestURI().replaceAll(".*/app/", "");
        AtomicReference<Command> command = new AtomicReference<>(commands.getOrDefault(path,
                (r) -> "/index.jsp"));
        Stream.of(path)
                .filter(s -> s.matches("ticket\\/[0-9]+\\/buy"))
                .forEach(s -> command.set(commands.get("buy")));
        Stream.of(path)
                .filter(s -> s.matches("expo\\/edit\\/[0-9]+"))
                .forEach(s -> command.set(commands.get("edit")));
        Stream.of(path)
                .filter(s -> s.matches("expo\\/delete\\/[0-9]+"))
                .forEach(s -> command.set(commands.get("delete")));

      /*  if (path.matches("ticket\\/[0-9]+\\/buy")) {
            command.set(commands.get("buy"));
        }
        if (path.matches("expo\\/edit\\/[0-9]+")) {
            command.set(commands.get("edit"));
        }
        if (path.matches("expo\\/delete\\/[0-9]+")) {
            command.set(commands.get("delete"));
        }
*/

        return command.get().execute(request);
    }

    public static int extractId(HttpServletRequest request) {
        String path = request.getRequestURI().replaceAll(".*/app/", "");
        AtomicReference<Integer> id = new AtomicReference<>(0);
        Stream.of(path)
                .filter(s -> s.matches(".*\\/[0-9]+\\/buy"))
                .forEach(s -> id.set(Integer.parseInt(s.replaceAll("[^0-9.]", ""))));
        Stream.of(path)
                .filter(s -> s.matches(".*\\/edit\\/[0-9]+"))
                .forEach(s -> id.set(Integer.parseInt(path.replaceAll("[^0-9.]", ""))));
        Stream.of(path)
                .filter(s -> s.matches(".*\\/delete\\/[0-9]+"))
                .forEach(s -> id.set(Integer.parseInt(path.replaceAll("[^0-9.]", ""))));

      /*  if (path.matches(".*\\/[0-9]+\\/buy")) {
            String token = path.replaceAll("\\/buy", "");
            id.set(Integer.parseInt(token.replaceAll(".*\\/ticket\\/", "")));
        }
        if (path.matches(".*\\/edit\\/[0-9]+")) {
            id.set(Integer.parseInt(path.replaceAll(".*\\/expo\\/edit\\/", "")));
        }
        if (path.matches(".*\\/delete\\/[0-9]+")) {
            id.set(Integer.parseInt(path.replaceAll(".*\\/expo\\/delete\\/", "")));
        }*/
        return id.get();
    }

    static void setUserRole(HttpServletRequest request,
                            User user) {
        HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        context.setAttribute("userName", user.getUsername());
        session.setAttribute("user", user);
        session.setAttribute("roles", User.Role.values());
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    static void logOutUser(HttpServletRequest request, String name) {
        ((HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers")).remove(name);
        request.getSession().removeAttribute("user");
    }
}
