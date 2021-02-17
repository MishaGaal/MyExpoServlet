package com.example.myexpo.command;


import com.example.myexpo.entity.Expo;
import com.example.myexpo.service.ExpoService;
import com.example.myexpo.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Edit implements Command {

    static final Logger log = LogManager.getRootLogger();
    private ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            String path = request.getRequestURI();
            HttpSession session = request.getSession();
            Integer id = Integer.parseInt(path.replaceAll(".*\\/expo\\/edit\\/", ""));
            Expo expo = (Expo) request.getSession().getAttribute("expo");
            if (expo == null) {
                session.setAttribute("id", id);
                session.setAttribute("expo", expoService.findById(id));
                session.setAttribute("holles", Expo.Holle.values());
                return "/edit.jsp";
            }
            session.setAttribute("expoDTO", Validator.createDto(request));
            if (Validator.validate(request)) {
                Expo ex = Validator.buildExpo(request);
                ex.setId(id);
                expoService.expoSubmit(ex);
            }
            session.removeAttribute("expo");
        } catch (Exception e) {
            log.info("{}", "Validation Exception: " + e.getMessage());
            request.getSession().setAttribute("holles", Expo.Holle.values());
            return "/edit.jsp";
        }

        return "redirect:/expo";
    }

}
