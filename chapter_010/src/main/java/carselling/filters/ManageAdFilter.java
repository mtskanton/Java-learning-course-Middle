package carselling.filters;

import carselling.logic.DaoAdvertisement;
import carselling.models.Advertisement;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр управления объявлением.
 */
public class ManageAdFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        int id = Integer.valueOf(request.getParameter("id"));
        Advertisement ad = DaoAdvertisement.getInstance().getById(id);

        if (userId == ad.getUser().getId()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/list", request.getContextPath()));
        }
    }

    @Override
    public void destroy() {

    }
}
