package com.example.DVDShop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ControllerExeptions {

    @RequestMapping("/404")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "/errors/404";
    }

    @RequestMapping("/500")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError() {
        return "/error/500";
    }
}
