<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:choose>
        <c:when test="${archives.size() > 0}">
            <h1>Страница всех архивов</h1>
            <table>
                <thead>
                <tr>
                    <th>
                        Название
                    </th>
                    <th>
                        Дата обновления
                    </th>
                    <th>
                        Дата создания
                    </th>
                    <th>
                        Владелец
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${archives}" var="a">
                    <tr>
                        <td>
                            <a href="/archive/${a.id}">${a.name}</a>
                        </td>
                        <td>
                                ${a.updateTime}
                        </td>
                        <td>
                                ${a.createTime}
                        </td>
                        <td>
                                ${a.owner.login}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:when test="${archive != null}">
            <h1>Страница архива ${archive.name}</h1>
            <h2>Список файлов</h2>
            <c:if test="${files.size() > 0}">
                <table>
                    <thead>
                    <tr>
                        <th>
                            Название
                        </th>
                        <th>
                            Дата добавления
                        </th>
                        <th>
                            Архив
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${files}" var="f">
                        <tr>
                            <td>
                                    ${f.name}
    <%--                            <a href="/file/${f.id}">${f.name}</a>--%>
                            </td>
                            <td>
                                    ${f.appendTime}
                            </td>
                            <td>
                                    ${f.fileArchive.name}
                            </td>
                            <td><a href="/file/update?id=${f.id}&archiveId=${archive.id}">Переименовать</a></td>
                            <td><a href="/file/delete?id=${f.id}&archiveId=${archive.id}">Удалить</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <div>
                <h4>Добавить файлы в архив</h4>
                <div>
                    <form method="post" action="/file/add?id=${archive.id}" autocomplete="off">
                        <input type="text" name="files" placeholder="имена файлов через пробел"/>
                        <button type="submit">Добавить</button>
                    </form>
                </div>
            </div>
            <a href="/archive/delete?id=${archive.id}">Удалить архив</a>
            <a href="/archive/update?id=${archive.id}">Переименовать архив</a>
            <hr>
            <a href="/archive">Назад к списку всех архивов</a>
        </c:when>
    </c:choose>

    <c:if test="${archive == null}">
        <div>
            <h4>Создать новый архив</h4>
            <div id="wrapper">
                <form method="post" action="/archive/create" autocomplete="off">
                    <input type="text" name="name" placeholder="имя архива"/>
                    <button type="submit">Создать</button>
                </form>
            </div>
        </div>
        <hr>
        <a href="/">Домой</a>
    </c:if>
</body>
</html>
