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

@WebServlet(urlPatterns = "/updateProduct")
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        Product product = ProductService.getProduct(productId);
        req.setAttribute("productId", product.getId());
        req.setAttribute("productName", product.getName());
        req.setAttribute("price", product.getPrice());
        req.setAttribute("productCategory", product.getProductCategory());
        req.setAttribute("productCountry", product.getProductCountry());
        req.setAttribute("discounted", product.isDiscounted());
        String path = "/updateProduct.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}