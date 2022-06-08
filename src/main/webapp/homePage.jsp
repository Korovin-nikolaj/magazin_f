<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Интернет-магазин</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/private/account"> Профиль </a> |  | <a href="http://localhost:8082/managerLogin.jsp"> Войти как мененджер </a> | <a href="http://localhost:8082/basket.jsp"> Корзина(${basketSize}) </a></p>
        <h3>Поиск товаров:</h3>
        <form action="/search" method="post">
            Наименование: <input name = "productName"/>  |  Категория: <input name = "productCategory"/> <br><br>
            Цена от: <input name = "priceFrom" type = "number"/>  |  Цена до: <input name = "priceUp" type = "number"/> <br><br>
            Страна производства: <input name = "productCountry"/> |  Только со скидкой: <input name = "discounted" type="checkbox"/> <br><br>
            <input type="submit" value = "Найти"/>
        </form>
        <h3>Каталог товаров: </h3>
        <c:forEach var="product" items="${allProducts}">
            <p><c:out value="${product.value}"/>  <a href="http://localhost:8082/putInBasket?productId=${product.key}&productView=${product.value}"> В корзину</a></p>
        </c:forEach>
    </body>
</html>