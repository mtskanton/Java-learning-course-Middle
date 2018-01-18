package application.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр доступа к изменении информации о пользователе.
 * Доступ, если пользоветель изменяент свои данные или роль пользователя - Admin
 */
public class UpdateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("id").equals(request.getParameter("id")) | session.getAttribute("role").equals("Admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(String.format("%s/", request.getContextPath()));
        }
    }

    @Override
    public void destroy() {

    }
}
