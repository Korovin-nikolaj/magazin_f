package ru.myServlets;

import ru.retail.Storage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(urlPatterns = "/inputProduct")
public class InputProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = Storage.getInstance().getConn();
        String productName = req.getParameter("productName");
        float  price = Float.parseFloat(req.getParameter("price"));
        String productCategory = req.getParameter("productCategory");
        String productCountry = req.getParameter("productCountry");
        boolean discounted = req.getParameter("discounted")!= null;
        int countRows = 0;
        try(Statement statement = conn.createStatement()) {
            String sqlCommand = "insert into products (productName, price, productCategory, productCountry, discounted) values('" + productName + "'," + price + ",'" + productCategory + "','" + productCountry + "'," + discounted + ");";
            System.out.println(sqlCommand);
            countRows = statement.executeUpdate(sqlCommand);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("countAddRows", countRows);
        req.setAttribute("productName", productName);
        String path = "/editProducts";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
