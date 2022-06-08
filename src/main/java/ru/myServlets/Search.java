package ru.myServlets;

import ru.retail.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@WebServlet(urlPatterns = "/search")
public class Search extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer conditionText = new StringBuffer();
        String productName = request.getParameter("productName");
        String productCategory = request.getParameter("productCategory");
        String productCountry = request.getParameter("productCountry");
        String priceFrom = request.getParameter("priceFrom");
        String priceUp = request.getParameter("priceUp");
        String discounted = request.getParameter("discounted");
        addCondition(conditionText, "productName", productName, "like");
        addCondition(conditionText, "productCategory", productCategory, "like");
        addCondition(conditionText, "productCountry", productCountry, "like");
        addCondition(conditionText, "price", priceFrom, ">");
        addCondition(conditionText, "price", priceUp, "<");
        addCondition(conditionText, "discounted", (discounted != null) ? "1": "", "=");
        LinkedHashMap<Integer, String> foundProducts = ProductService.findProducts(conditionText.toString());
        request.setAttribute("foundProducts", foundProducts);
        int basketSize = 0;
        @SuppressWarnings("unchecked")
        ArrayList<Integer> basket = (ArrayList<Integer>) request.getSession().getAttribute("basket");
        if (basket != null) {
            basketSize = basket.size();
        }
        request.setAttribute("basketSize", basketSize);
        String path = "/search.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private void addCondition(StringBuffer conditionText, String columnName, String value, String conditionType) {
        if (value != null) {
            if (!value.isEmpty()) {
                if (!conditionText.toString().isEmpty()) { //TODO может есть у StringBuffer подобный метод.
                    conditionText.append(" and ");
                }
                if (conditionType.equals("like")) {
                    conditionText.append(columnName).append(" like '%").append(value).append("%'");
                } else if ("<>=".contains(conditionType)) {
                    conditionText.append(columnName).append(conditionType).append(value);
                }
            }
        }
    }
}
