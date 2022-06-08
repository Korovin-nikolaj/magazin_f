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
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/updateProductEnd")
public class UpdateProductEnd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String productName = req.getParameter("productName");
        String price = req.getParameter("price");
        String productCategory = req.getParameter("productCategory");
        String productCountry = req.getParameter("productCountry");
        String discounted = req.getParameter("discounted");
        if (productId == null) {
            forwardRequest(req, resp, "/errorPage.jsp");
        } else {
            price = (price == null) ? "0" : price;
            discounted = (discounted == null) ? "false" : discounted;
            Product product = new Product(productName, Integer.parseInt(productId), Float.parseFloat(price), productCategory, productCountry, Boolean.parseBoolean(discounted));
            int countRows = ProductService.updateProduct(product);
            req.setAttribute("countUpdateRows", countRows);
            req.setAttribute("productName", productName);
            LinkedHashMap<Integer, String> allProducts = ProductService.getAllProducts();
            req.setAttribute("allProducts", allProducts);
            forwardRequest(req, resp, "/editProducts.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void forwardRequest(HttpServletRequest req, HttpServletResponse resp, String path) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}