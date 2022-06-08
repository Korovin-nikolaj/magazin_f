<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Корзина</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/account">Профиль</a>  |  <a href="http://localhost:8082/basket.jsp">Корзина ${basketSize}</a></p>
        <h3>Результаты поиска</h3>
        <c:forEach var="product" items="${foundProducts}">
            <p><c:out value="${product.value}"/>  <a href="http://localhost:8082/putInBasket?productId=${product.key}&productView=${product.value}">В корзину</a></p>
        </c:forEach>
    </body>
</html>