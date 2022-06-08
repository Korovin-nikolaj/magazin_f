package ru.myServlets;

import ru.retail.Storage;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/")
public class HomePage extends HttpServlet {
    public static Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Storage.getInstance();
    }

    @Override
    public void destroy() {
        super.destroy();
        Connection conn = Storage.getInstance().getConn();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        HashMap<Integer, Integer> basketWithQuantity = (HashMap<Integer, Integer>) session.getAttribute("basketWithQuantity");
        if (basketWithQuantity == null) {
            basketWithQuantity = new HashMap<>();
            session.setAttribute("basketWithQuantity", basketWithQuantity);
        }
        @SuppressWarnings("unchecked")
        HashMap<Integer, String> basketForView = (HashMap<Integer, String>) session.getAttribute("basketForView");
        if (basketForView == null) {
            basketForView = new HashMap<>();
            session.setAttribute("basketForView", basketForView);
        }
        LinkedHashMap<Integer, String> allProducts = ProductService.getAllProducts();
        request.setAttribute("basketSize", basketWithQuantity.size());
        request.setAttribute("allProducts", allProducts);
        String path = "/homePage.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
