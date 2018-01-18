package application.filters;

import application.CreateUser;
import application.UsersStore;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр изменения кодировки для правильной работы с кириллицей.
 */
public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
