<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Оплата заказа</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a></p>
        <h3>Оплата заказа</h3>
        <p> Текущий баланс: ${clientBalance}</p>
        <c:if test="${totalSum > clientBalance}">
            <p>Не хватает средств для оплаты. Пополните счет.</p>
        </c:if>
        <c:if test="${totalSum <= clientBalance}">
            <p>После нажатия на кнопку будет произведена оплат товара.</p>
            <p>С вашего счета будет списано ${totalSum} рублей.</p>
            <form action="/private/buyPage" method="post">
                <input type="hidden"  name="totalSum" value="${totalSum}"/>
                <p>Адрес доставки: <input maxlength="100" size="70" name="address"/></p>
                <p>Комментарий к заказу: <input maxlength="50" size="50" name="comments"/></p>
                <input type="submit" value = "Произвести оплату"/>
            </form>
        </c:if>
        <br>
        <h3>Список товаров в корзине</h3>
        <table>
            <tr>
                <td>Товар</td>
                <td>Количество</td>
                <td>Цена</td>
            </tr>
            <c:forEach var="product" items="${basket}">
                <tr>
                    <td><c:out value="${product.value.productName}"/></td>
                    <td><c:out value="${product.value.quantity}"/></td>
                    <td><c:out value="${product.value.price}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>