<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Корзина</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/private/account">Профиль</a></p>
        <h3>Товары в корзине</h3>
        <c:forEach var="product" items="${basketForView}">
            <p><c:out value="${product.value}"/>
            <a href="http://localhost:8082/changeQuantityInBasket?productId=${product.key}&add=0"> < </a>
            <a href="http://localhost:8082/changeQuantityInBasket?productId=${product.key}&add=1"> > </a>
            <a href="http://localhost:8082/removeFromBasket?productId=${product.key}">Удалить</a>
            </p>
        </c:forEach>
        <p><a href="http://localhost:8082/private/buy">Купить товары</a></p>
    </body>
</html>