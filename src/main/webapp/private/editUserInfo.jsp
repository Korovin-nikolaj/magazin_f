<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Редактирование личной информации</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a>  |  <a href="http://localhost:8082/account">Профиль</a></p>
        <p> Редактирование личной информации: </p>
        <form action="/private/editUserInfoEnd" method="post">
            <input type="hidden"  name="clientId" value="${clientId}"/>
            <input type="hidden"  name="hashPassword" value="${hashPassword}"/>
            <p>Имя: <input maxlength="50" size="50" name="clientName" value="${clientName}"/></p>
            <p>Телефон: <input maxlength="25" size="25" name="phone" value="${phone}"/></p>
            <input type="submit" value = "Обновить информацию"/>
        </form>
    </body>
</html>