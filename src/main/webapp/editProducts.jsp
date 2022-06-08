<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Редактирование товаров</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/private/account">Профиль</a></p>
        <c:if test="${countAddRows == 1}">
            Товар ${productName} был успешно добавлен!
        </c:if>
        <c:if test="${countDeleteRows == 1}">
            Товар с id ${productId} был успешно удален!
        </c:if>
        <c:if test="${countUpdateRows == 1}">
            Товар ${productName} был успешно обновлен!
        </c:if>
        <p> Ввод нового товара: </p>
        <form action="/inputProduct" method="post">
            <p>Наименование товара: <input maxlength="50" size="50" name="productName"/></p>
            <p>Цена: <input type="number"  size="15" name="price"/></p>
            <p>Категория товара: <input maxlength="25" size="25" name="productCategory"/></p>
            <p>Страна-производитель: <input maxlength="25" size="25" name="productCountry"/></p>
            <p>Товар со скидкой: <input name="discounted" type="checkbox"/></p>
                <input type="submit" value = "Добавить товар"/>
        </form>
        <h3>Каталог товаров</h3>
            <c:forEach var="product" items="${allProducts}">
                <p>  <a href="http://localhost:8082/deleteProduct?productId=${product.key}">X</a> <a href="http://localhost:8082/updateProduct?productId=${product.key}">...</a> <c:out value="${product.value}"/></p>
            </c:forEach>
    </body>
</html>