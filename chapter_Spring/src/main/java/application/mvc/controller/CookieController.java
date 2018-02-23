package application.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

    @GetMapping
    @RequestMapping(value = "/writeCookie")
    public String writeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("cookieExample", request.getRequestURL().toString());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return "/cookie/cookie";
    }

    @GetMapping
    @RequestMapping(value = "/readCookie")
    public ModelAndView readCookie(@CookieValue(value = "cookieExample", required = false) Cookie cookie) {
        String cookieResult = "cookie with name 'cookieExample' is empty";
        if (cookie != null) {
            cookieResult = "Object: " + cookie + "<br/> Name: " + cookie.getName() + "<br/> Value: " + cookie.getValue();
        }
        return new ModelAndView("/cookie/cookie", "cookieResult", cookieResult);
    }
}
