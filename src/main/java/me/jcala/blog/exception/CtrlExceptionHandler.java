package me.jcala.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理类
 */
@ControllerAdvice
@Slf4j
public class CtrlExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleIOException(Exception e, Model model) {
        if (e != null) {
            log.warn(e.getMessage());
            model.addAttribute("errorMsg", e.getLocalizedMessage());
        }
        return "/error";
    }

}
