package com.example.myexpo.command;

import com.example.myexpo.service.ExpoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Delete implements Command {

    static final Logger log = LogManager.getRootLogger();
    private ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.getSession().setAttribute("expo", expoService.deleteBy(CommandUtility.extractId(request)));
        } catch (Exception e) {
            log.info("{}", "delete Exception: " + e.getMessage());
        }
        return "redirect:/expo";
    }
}
