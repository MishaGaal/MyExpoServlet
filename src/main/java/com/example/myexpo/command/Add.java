package com.example.myexpo.command;

import com.example.myexpo.dto.ExpoDto;
import com.example.myexpo.entity.Expo;
import com.example.myexpo.service.ExpoService;
import com.example.myexpo.util.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Add implements Command {

    static final Logger log = LogManager.getRootLogger();
    private final ExpoService expoService = new ExpoService();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("imgName") == null) {
                session.setAttribute("holles", Expo.Holle.values());
                session.setAttribute("expoDTO", new ExpoDto());
                return "/add.jsp";
            }
            session.setAttribute("expoDTO", CommandUtility.createDto(request));
            if (Validator.validate(request)) {
                expoService.addNewExpo(CommandUtility.buildExpo(request));
            }
            session.removeAttribute("expo");
        } catch (Exception e) {
            log.info("{}", "Validation Exception: " + e.getMessage());
            request.getSession()
                    .setAttribute("valid", e.getMessage());
            return "/add.jsp";
        }
        return "redirect:/expo";
    }

}
