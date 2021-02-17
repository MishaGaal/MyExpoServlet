package com.example.myexpo.command;

import com.example.myexpo.service.ExpoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class Index implements Command {
    static final Logger log = LogManager.getRootLogger();
    private ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.getSession()
                    .setAttribute("pages",
                            expoService
                                    .getExhibited(Integer.parseInt(Optional.ofNullable(request.getParameter("page"))
                                            .orElse("0"))));
        } catch (Exception e) {
            log.info("{}", "Can't get expos: " + e.getMessage());
        }
        return "/index.jsp";
    }
}
