package application.mvc.exception;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Обработчик исключений.
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {
    private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        LOGGER.error("ErrorLog", e);
        return new ModelAndView("/error/exception", "exceptionMsg", e.toString());
    }
}
