package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error/error404");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
