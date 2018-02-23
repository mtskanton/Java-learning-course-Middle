package application.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ScopeController {

    @RequestMapping(value = "/scopeSession", method = RequestMethod.GET)
    public String sessionScopeExample(HttpSession session) {
        session.setMaxInactiveInterval(3600);
        session.setAttribute("sessionObject", "valueOfSessionObject");
        return "/scope/scope";
    }

    @RequestMapping(value = "/scopeRequest", method = RequestMethod.GET)
    public String requestScopeExample(HttpServletRequest request) {
        request.setAttribute("requestObject", "valueOfRequestObject");
        return "/scope/scope";
    }

    @RequestMapping(value = "/invalidateSession", method = RequestMethod.GET)
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        return "/scope/scope";
    }
}
