<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Войти</h1>
    <div id="wrapper">
        <form method="post" action="/login" autocomplete="off">
            <input type="text" name="login" placeholder="логин"/>
            <input type="password" name="password" placeholder="пароль"/>
            <button type="submit">Войти</button>
        </form>
    </div>
</body>
</html>
