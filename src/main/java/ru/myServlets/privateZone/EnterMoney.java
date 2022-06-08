package ru.myServlets.privateZone;

import ru.retail.service.MoneyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/private/enterMoney")
public class EnterMoney extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientId = req.getParameter("clientId");
        String sum = req.getParameter("sum");
        int countRows = MoneyService.enterMoney(clientId, sum, "Внесение денег на счет.");
        req.setAttribute("clientBalance", MoneyService.getClientBalance(Integer.parseInt(clientId)));
        req.setAttribute("countAddRows", countRows);
        String path = "/private/account.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
