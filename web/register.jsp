<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
    <body>
        <h1>Зарегистироваться</h1>
        <div id="wrapper">
            <form method="post" action="/register" autocomplete="off">
                <input type="text" name="login" placeholder="логин"/>
                <input type="password" name="password" placeholder="пароль"/>
                <button type="submit">Зарегистрироваться</button>
            </form>
        </div>
    </body>
</html>
