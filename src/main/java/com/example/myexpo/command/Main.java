package com.example.myexpo.command;

import com.example.myexpo.service.ExpoService;
import com.example.myexpo.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Main implements Command {

    static final Logger log = LogManager.getRootLogger();
    private ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int page = Validator.getPageNum(request);
            request.getSession().setAttribute("pages", expoService.getExhibited(page));
        } catch (Exception e) {
            log.info("{}", "Can't get expos: " + e.getMessage());
        }
        return "/main.jsp";
    }

}
