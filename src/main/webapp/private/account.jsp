<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Профиль клиента</title>
        <style>
           table {
            width: 100%; /* Ширина таблицы */
            border-collapse: collapse; /* Убираем двойные линии */
           }
           thead {
            background: #f5e8d0; /* Цвет фона заголовка */
           }
           td, th {
            padding: 5px; /* Поля в ячейках */
            border: 1px solid #333; /* Параметры рамки */
           }
           /*tbody tr:nth-child(even) {
            background: #f0f0f0;  Зебра
           }*/
        </style>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a></p>
        <h3>Личная информация</h3>
        <c:if test="${countUpdateRows == 1}">
            Личная информация была успешно обновлена!
        </c:if>
        <p> ИД: ${clientId}</p>
        <p> Имя: ${clientName}</p>
        <p> Телефон: ${clientPhone}</p>
        <br>
        <h3>История покупок</h3>
        <table>
            <thead>
                <tr>
                    <th>Номер заказа</td>
                    <th>Сумма</td>
                    <th>Адрес доставки</td>
                    <th>Комментарий</td>
                    <th>Статус</td>
                </tr>
                <tr>
                    <th  colspan="4">Наименование товара</td>
                    <th>Количество</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="rowHistory" items="${buyHistory}">
                    <tr bgcolor="#f0f0f0">
                        <td><c:out value="${rowHistory.value.orderId}"/></td>
                        <td><c:out value="${rowHistory.value.sum}"/></td>
                        <td><c:out value="${rowHistory.value.address}"/></td>
                        <td><c:out value="${rowHistory.value.comment}"/></td>
                        <td><c:out value="${rowHistory.value.status}"/></td>
                    </tr>
                    <c:forEach var="rowProducts" items="${rowHistory.value.products}">
                        <tr>
                            <td colspan="4"><c:out value="${rowProducts.value.productName}"/></td>
                            <td><c:out value="${rowProducts.value.quantity}"/></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <p><a href="http://localhost:8082/private/editUserInfo?clientId=${clientId}">Редактирование личной информации</a></p>
        <h3>Пополнение баланса</h3>
        <c:if test="${countAddRows == 1}">
            Деньги были успешно внесены на счет!
        </c:if>
        <p> Текущий баланс: ${clientBalance}</p>
        <form action="/private/enterMoney" method="post">
            <input type="hidden"  name="clientId" value="${clientId}"/>
            <p> Внести на баланс <input type="number" maxlength="20" size="20" name="sum"> рублей</p>
            <input type="submit" value = "Пополнить"/>
        </form>
    </body>
</html>