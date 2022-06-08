package ru.myServlets;

import ru.retail.Product;
import ru.retail.service.ProductService;

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

@WebServlet(urlPatterns = "/putInBasket")
public class PutInBasket extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        if (productId != null) {
            HttpSession session = request.getSession();
            @SuppressWarnings("unchecked")
            HashMap<Integer, Integer> basketWithQuantity = (HashMap<Integer, Integer>) session.getAttribute("basketWithQuantity");
            if (basketWithQuantity == null) {
                basketWithQuantity = new HashMap<>();
            }
            @SuppressWarnings("unchecked")
            HashMap<Integer, String> basketForView = (HashMap<Integer, String>) session.getAttribute("basketForView");
            if (basketForView == null) {
                basketForView = new HashMap<>();
            }
            Integer element = basketWithQuantity.get(Integer.valueOf(productId));
            if (element != null) {
                element = element + 1;
            } else {
                element = 1;
            }
            basketWithQuantity.put(Integer.valueOf(productId), element);
            Product product = ProductService.getProduct(productId);
            basketForView.put(Integer.valueOf(productId), product.getName() + " по цене " + product.getPrice() + " рублей. " + element + " шт.");
            session.setAttribute("basketWithQuantity", basketWithQuantity);
            session.setAttribute("basketForView", basketForView);
        }
        String path = "/";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}