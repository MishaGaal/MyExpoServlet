package com.example.myexpo.util;

import com.example.myexpo.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class Validator {


    public static boolean validate(User user, String pc) throws Exception {
        if (user.getPassword().length() < 6) {
            throw new Exception("Password is too short");
        }
        if (user.getUsername().length() < 1) {
            throw new Exception("Username is too short");
        }
        if (!user.getPassword().equals(pc)) {
            throw new Exception("Passwords are different!");
        }
        if (!user.getEmail().matches("^[A-Za-z]+@([A-Za-z]+\\.)+[A-Za-z]{2,4}$")) {
            throw new Exception("Enter email like : some@some.com");
        }
        return true;
    }



    public static boolean validate(HttpServletRequest request) throws Exception {

        if (Optional.ofNullable((request.getParameter("title_ua")))
                .orElseThrow(() -> new Exception("Enter title ua!"))
                .length() < 1) {
            throw new Exception("title ua is too short!");
        }

        if (Optional.ofNullable((request.getParameter("title")))
                .orElseThrow(() -> new Exception("Enter title!"))
                .length() < 1) {
            throw new Exception("title is too short!");
        }
        if (Optional.ofNullable((request.getParameter("description")))
                .orElseThrow(() -> new Exception("Enter description!"))
                .length() < 1) {
            throw new Exception("description is too short!");
        }
        if (Optional.ofNullable((request.getParameter("description_ua")))
                .orElseThrow(() -> new Exception("Enter description ua!"))
                .length() < 1) {
            throw new Exception("description ua is too short!");
        }
        if (Optional.ofNullable((request.getParameter("imgName")))
                .orElseThrow(() -> new Exception("Enter image name!"))
                .length() < 1) {
            throw new Exception("default image: 60.jpg");
        }

        if (Optional.ofNullable((request.getParameter("ticket_price")))
                .orElseThrow(() -> new Exception("Enter ticket price!"))
                .length() < 1) {
            throw new Exception("Wrong price!");
        }
        if (Optional.ofNullable((request.getParameter("amount")))
                .orElseThrow(() -> new Exception("Enter amount!"))
                .length() < 1) {
            throw new Exception("Wrong amount!");
        }
        if (Optional.ofNullable((request.getParameter("startDate")))
                .orElseThrow(() -> new Exception("Enter startDate!"))
                .length() < 1) {
            throw new Exception("Set start date!");
        }
        if (Optional.ofNullable((request.getParameter("endDate")))
                .orElseThrow(() -> new Exception("Enter endDate!"))
                .length() < 1) {
            throw new Exception("Set end date!");
        }
        return true;
    }


}
