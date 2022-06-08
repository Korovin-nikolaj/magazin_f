package ru.myServlets.privateZone;

import ru.retail.BasketRow;
import ru.retail.Product;
import ru.retail.service.MoneyService;
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
import java.util.Map.Entry;

@WebServlet(urlPatterns = "/private/buy")
public class Buy extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String clientId = session.getAttribute("clientId").toString();
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer> basketWithQuantity = (HashMap<Integer, Integer>) session.getAttribute("basketWithQuantity");
        HashMap<Integer, BasketRow> basket = new HashMap<>();
        float totalSum = 0;
        for (Entry entry : basketWithQuantity.entrySet()) {
            Product product = ProductService.getProduct(entry.getKey().toString());
            BasketRow row = new BasketRow(product.getId(), product.getName(), (Integer)entry.getValue(), product.getPrice());
            basket.put((Integer) entry.getKey(), row);
            totalSum += (Integer)entry.getValue() * product.getPrice();
        }
        req.setAttribute("basket", basket);
        req.setAttribute("clientBalance", MoneyService.getClientBalance(Integer.parseInt(clientId)));
        req.setAttribute("totalSum", totalSum);
        String path = "/private/buy.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
