<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Редактирование клиентов</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/private/account">Профиль</a></p>
        <c:if test="${countAddRows == 1}">
            Клиент ${name} был успешно добавлен!
        </c:if>
        <c:if test="${countDeleteRows == 1}">
            Клиент с id ${clientId} был успешно удален!
        </c:if>
        <c:if test="${countUpdateRows == 1}">
            Клиент ${name} был успешно обновлен!
        </c:if>
        <p> Ввод нового клиента: </p>
        <form action="/inputClient" method="post">
            <p>Имя клиента: <input maxlength="50" size="50" name="name"/></p>
            <p>Номер телефона: <input maxlength="25" size="25" name="phone"/></p>
            <p>Пароль: <input type="password" maxlength="25" size="25" name="password"/></p>
                <input type="submit" value = "Добавить клиента"/>
        </form>
        <h3>Список клиентов</h3>
            <c:forEach var="client" items="${allClients}">
                <p>  <a href="http://localhost:8082/deleteClient?clientId=${client.key}">X</a> <a href="http://localhost:8082/updateClient?clientId=${client.key}">...</a> <c:out value="${client.value}"/></p>
            </c:forEach>
    </body>
</html>