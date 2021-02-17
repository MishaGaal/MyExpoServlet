package com.example.myexpo.command;

import com.example.myexpo.service.ExpoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Delete implements Command {

    static final Logger log = LogManager.getRootLogger();
    private ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String path = request.getRequestURI();
            HttpSession session = request.getSession();
            Integer id = Integer.parseInt(path.replaceAll(".*\\/expo\\/delete\\/", ""));
            session.setAttribute("expo", expoService.deleteBy(id));

        } catch (Exception e) {
            log.info("{}", "delete Exception: " + e.getMessage());
        }
        return "redirect:/expo";
    }
}
