package fun.madeby.mbfrecipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Gra_m on 2022 04 25
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String BAD_REQUEST_400 = "400error";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView numberFormatExceptionHandler(Exception e){

        log.error("Handling number Format exception");
        log.error(e.getMessage());

        ModelAndView badRequestViewAndExceptionModel = new ModelAndView();
        badRequestViewAndExceptionModel.setViewName(BAD_REQUEST_400);
        badRequestViewAndExceptionModel.addObject("exception", e);

        return badRequestViewAndExceptionModel;
    }

}
