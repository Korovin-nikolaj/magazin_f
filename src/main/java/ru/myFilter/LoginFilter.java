package ru.myFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/private/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        boolean isAuthorizedUser = session.getAttribute("isAuthorizedUser") != null;
        System.out.println("isAuthorizedUser " + isAuthorizedUser);
        if (!isAuthorizedUser) {
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher("/userLogin.jsp");
            requestDispatcher.forward(request, res);
            return;
        }
        chain.doFilter(request, res);
    }

    @Override
    public void destroy() {

    }
}
