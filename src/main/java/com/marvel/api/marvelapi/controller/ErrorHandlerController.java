package com.marvel.api.marvelapi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * This class handles errors in the API.
 */
@RestController
public class ErrorHandlerController implements ErrorController {

    /**
     * Handle any incoming errors.
     *
     * @param request the incoming request that triggered this error.
     * @return a string representation of the error message.
     */
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        return "<html><body><h2>Error Page</h2><div>Status code: <b>" +
                HttpStatus.NOT_FOUND.value() +
                "</b></div><div>Exception Message: <b>" +
                (exception == null ? "N/A" : exception.getMessage()) +
                "</b></div><body></html>";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
