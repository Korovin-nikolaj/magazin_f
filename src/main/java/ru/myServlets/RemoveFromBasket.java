package ru.myServlets;

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

@WebServlet(urlPatterns = "/removeFromBasket")
public class RemoveFromBasket extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId != null) {
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            HashMap<Integer, String> basketForView = (HashMap<Integer, String>) session.getAttribute("basketForView");
            if (basketForView == null) {
                basketForView = new HashMap<>();
            }
            basketForView.remove(Integer.valueOf(productId));
            @SuppressWarnings("unchecked")
            HashMap<Integer, Integer> basketWithQuantity = (HashMap<Integer, Integer>) session.getAttribute("basketWithQuantity");
            if (basketWithQuantity == null) {
                basketWithQuantity = new HashMap<>();
            }
            session.setAttribute("basketForView", basketForView);
            session.setAttribute("basketWithQuantity", basketWithQuantity);
        }
        String path = "/basket.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}