<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Вход в профиль клиента</title>
    </head>
    <body>
        <p><a href="http://localhost:8082/">На главную</a></p>
        <p>Введите логин и пароль пользователя магазина
        <form action="/userAuthentication" method="post">
            <p><strong>Логин:</strong>
            <input maxlength="25" size="40" name="login"></p>
            <p><strong>Пароль:</strong>
            <input type="password" maxlength="25" size="40" name="password"></p>
            <input type="submit" value = "Войти"/>
        </form>
    </body>
</html>