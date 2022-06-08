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

@WebServlet(urlPatterns = "/inputClient")
public class InputClient extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = Storage.getInstance().getConn();
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        int countRows = 0;
        try(Statement statement = conn.createStatement()) {
            String sqlCommand = "insert into clients (name, phone, hashPassword) values('" + name + "','" + phone + "'," + password.hashCode() + ");";
            System.out.println(sqlCommand);
            countRows = statement.executeUpdate(sqlCommand);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("countAddRows", countRows);
        req.setAttribute("name", name);
        String path = "/editClients";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
}
