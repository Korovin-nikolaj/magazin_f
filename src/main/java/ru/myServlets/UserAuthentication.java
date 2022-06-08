package ru.myServlets;

import ru.retail.BuyHistoryRow;
import ru.retail.service.MoneyService;
import ru.retail.service.OrderService;
import ru.retail.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/userAuthentication")
public class UserAuthentication extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        String path = "/errorLogin.jsp";
        if (UserService.isPasswordCorrect(login, password, session)) {
            path = "/private/account.jsp";
            req.setAttribute("clientBalance", MoneyService.getClientBalance((Integer)session.getAttribute("clientId")));
            session.setAttribute("isAuthorizedUser", true);
            HashMap<Integer, BuyHistoryRow> buyHistory = OrderService.getBuyHistory(((Integer)session.getAttribute("clientId")).toString());
            session.setAttribute("buyHistory", buyHistory);
        } else {
            req.setAttribute("returnPage", "userLogin.jsp");
        }
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}