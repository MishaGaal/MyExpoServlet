package com.example.myexpo.command;

import com.example.myexpo.service.ExpoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

public class FilterDates implements Command {

    static final Logger log = LogManager.getRootLogger();
    private final ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {

        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        try {
            request.getSession().setAttribute(
                    "pages",
                    expoService
                            .findByExhibitedTrueOrderByDates(
                                    Optional.ofNullable(startDate).orElse(LocalDate.now())
                                    , Optional.ofNullable(endDate).orElse(LocalDate.now())
                                    , Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"))));
        } catch (Exception e) {
            log.info("{}", "Cant find by desc price expos: " + e.getMessage());
        }
        return "/main.jsp";
    }
}
