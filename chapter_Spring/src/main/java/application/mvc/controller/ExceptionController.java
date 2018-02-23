package application.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * При переходе по ссылке вида /runtimeException в методе будет искусственно выброшено RuntimeException исключение.
 */
@Controller
public class ExceptionController {

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public void throwException() {
        throw new RuntimeException("Exception");
    }
}
