package com.prokarma.technologyoffice.controller;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Ninod Pillai
 *
 */
@RestController
public class IndexController implements ErrorController{

    private static final String PATH = "/error";

    /**
     * @return
     */
    @RequestMapping(value = PATH)
    public String error() {
        return "Passed URL path Not configured for product service, Please use http://domain/products";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}