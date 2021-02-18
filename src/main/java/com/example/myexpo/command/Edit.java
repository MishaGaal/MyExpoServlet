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
    private final ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            Integer id = CommandUtility.extractId(request);

            if (request.getParameter("imgName") == null) {
                session.setAttribute("holles", Expo.Holle.values());
                session.setAttribute("expo", expoService.findById(id));
                return "/edit.jsp";
            }
            if (Validator.validate(request)) {
                Expo ex = CommandUtility.buildExpo(request);
                ex.setId(id);
                session.removeAttribute("expo");
                expoService.expoSubmit(ex);
            }
        } catch (Exception e) {
            log.info("{}", "Validation Exception: " + e.getMessage());
            request.getSession()
                    .setAttribute("valid", e.getMessage());
            return "/edit.jsp";
        }
        return "redirect:/expo";
    }

}
