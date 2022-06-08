<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Редактирование товара</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/account">Профиль</a></p>
        <c:if test="${countAddRows == 1}">
            Товар ${productName} был успешно добавлен!
        </c:if>
        <c:if test="${countDeleteRows == 1}">
            Товар с id ${productId} был успешно удален!
        </c:if>
        <p> Редактирование существующего товара: </p>
        <form action="/updateProductEnd" method="post">
            <input type="hidden"  name="productId" value="${productId}"/>
            <p>Наименование товара: <input maxlength="50" size="50" name="productName" value="${productName}"/></p>
            <p>Цена: <input type="number"  size="15" name="price" value="${price}"/></p>
            <p>Категория товара: <input maxlength="25" size="25" name="productCategory" value="${productCategory}"/></p>
            <p>Страна-производитель: <input maxlength="25" size="25" name="productCountry" value="${productCountry}"/></p>
            <p>Товар со скидкой: <input name="discounted" type="checkbox" value="true" <c:if test="${discounted}"> checked </c:if>/></p>
                <input type="submit" value = "Обновить товар"/>
        </form>
    </body>
</html>