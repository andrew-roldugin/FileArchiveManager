<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
    <div>
        <h1>Страница пользователя ${user.login}</h1>

        <c:if test="${users.size() > 0}">
            <h2>Список всех пользователей</h2>
            <table>
                <thead>
                <tr>
                    <th>
                        Логин
                    </th>
                    <th>
                        Роль
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="u">
                    <tr>
                        <td>
                            <a href="/user/${u.id}">${u.login}</a>
                        </td>
                        <td>
                                ${u.role}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div style="margin-top: 15px; margin-right: 10px">
            <a href="/user/delete?id=${user.id}">Удалить учетную запись</a>
            <a href="/user/update?id=${user.id}">Обновить данные</a>
            <hr>
            <a href="/archive">Посмотреть список всех архивов</a>
            <hr>
            <a href="/logout">Выйти из аккаунта</a>
        </div>
    </div>
</body>
</html>
