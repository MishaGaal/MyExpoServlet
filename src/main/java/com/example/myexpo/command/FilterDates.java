package com.example.myexpo.command;

import com.example.myexpo.service.ExpoService;
import com.example.myexpo.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class FilterDates implements Command {

    static final Logger log = LogManager.getRootLogger();
    private ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        String start = request.getParameter("startDate");
        String end = request.getParameter("endDate");
        LocalDate startDate = start == null ? LocalDate.now() : LocalDate.parse(start);
        LocalDate endDate = end == null ? LocalDate.now() : LocalDate.parse(end);
        try {
            int page = Validator.getPageNum(request);
            request.getSession().setAttribute("pages", expoService.findByExhibitedTrueOrderByDates(startDate, endDate, page));
        } catch (Exception e) {
            log.info("{}", "Cant find by desc price expos: " + e.getMessage());
        }
        return "/main.jsp";
    }
}
