package ru.myServlets.privateZone;

import ru.retail.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/private/buyPage")
public class BuyPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String clientId = session.getAttribute("clientId").toString();
        String sum = req.getParameter("totalSum");
        String address = req.getParameter("address");
        String comments = req.getParameter("comments");
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer> basketWithQuantity = (HashMap<Integer, Integer>) session.getAttribute("basketWithQuantity");
        boolean requestComplete = false;
        try {
            requestComplete = OrderService.enterOrder(clientId, sum, comments, address, basketWithQuantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (requestComplete) {
            session.setAttribute("basketWithQuantity", new HashMap<Integer, Integer>());
            session.setAttribute("basketForView", new HashMap<Integer, String>());
            session.setAttribute("buyHistory", OrderService.getBuyHistory(clientId));
        }
        String path = "/basket.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
