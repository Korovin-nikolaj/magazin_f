package ru.myServlets.privateZone;

import ru.retail.User;
import ru.retail.service.MoneyService;
import ru.retail.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/private/editUserInfoEnd")
public class EditUserInfoEnd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientId = req.getParameter("clientId");
        String clientName = req.getParameter("clientName");
        String phone = req.getParameter("phone");
        String hashPassword = req.getParameter("hashPassword");
        if (clientId == null) {
            req.setAttribute("returnPage", "/userLogin.jsp");
            forwardRequest(req, resp, "/errorPage.jsp");
        } else {
            User user = new User(clientName, Integer.parseInt(clientId), phone, Integer.parseInt(hashPassword));
            int countRows = UserService.updateUser(user, req.getSession());
            req.setAttribute("clientId", clientId);
            req.setAttribute("clientName", clientName);
            req.setAttribute("clientPhone", phone);
            req.setAttribute("countUpdateRows", countRows);
            req.setAttribute("clientBalance", MoneyService.getClientBalance(Integer.parseInt(clientId)));
            forwardRequest(req, resp, "/private/account.jsp");
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
