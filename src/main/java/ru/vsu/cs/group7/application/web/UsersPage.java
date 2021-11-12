package ru.vsu.cs.group7.application.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.exception.NotAllowedExceptions;
import ru.vsu.cs.group7.exception.UserNotAuthorizedException;
import ru.vsu.cs.group7.exception.UserNotFoundException;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "UserPage", urlPatterns = "/user/*")
public class UsersPage extends HttpServlet {

    private UserService userService;
    public static final Logger LOGGER = LoggerFactory.getLogger(UsersPage.class.getSimpleName());

    @Override
    public void init() throws ServletException {
        Services services = Services.getInstance(ApplicationContext.getInstance());
        userService = services.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final String action = req.getPathInfo().substring(1); // "/delete"
//        final String servletPat12h = req.getServletPath(); // "/user"
//        final String servletPat123h = req.getRequestURI(); // "/user/delete"
//        StringBuffer s = req.getRequestURL(); // "http://localhost:8080/user/delete"

        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            final String action = pathInfo.substring(1);
            if (action.equals("delete")) {
                doDelete(req, resp);
            } else if (action.equals("update")) {
                final Long id = Long.parseLong(req.getQueryString().split("=")[1]);
                String content = String.format("""
                                    <h1>Обновление пользовательских данных</h1>
                                    <div id="wrapper">
                                    <form method="post" action="/user/update?id=%s" autocomplete="off">
                                        <input type="text" name="login" placeholder="логин" />
                                        <input type="password" name="password" placeholder="пароль" />
                                        <button type="submit">Обновить</button>
                                    </form>
                                </div>
                        """, id);
                resp.setContentType("text/html");
                req.setAttribute("content", content);
                getServletContext()
                        .getRequestDispatcher("/update-page.jsp")
                        .forward(req, resp);
            } else {
                try {
                    req.setAttribute("user", userService.getOneUserById(Long.parseLong(action)));
                    final List<User> users = userService.findAll(User.RoleEnum.Admin);
                    req.setAttribute("users", users);
                } catch (ApplicationException | NumberFormatException e) {
                    LOGGER.warn(e.getMessage());
                    req.setAttribute("users", Collections.emptyList());
                }
                getServletContext()
                        .getRequestDispatcher("/user.jsp")
                        .forward(req, resp);
            }
        }
//        } else {
//            try {
//                final List<User> users = userService.findAll(User.RoleEnum.Admin);
//                req.setAttribute("users", users);
//            } catch (UserNotAuthorizedException | NotAllowedExceptions e) {
//                LOGGER.warn("Ошибка при загрузке пользователей");
//                req.setAttribute("users", Collections.emptyList());
//            }
//        }
//        getServletContext()
//                .getRequestDispatcher("/user.jsp")
//                .forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.parseLong(req.getQueryString().split("=")[1]);
        try {
            userService.removeUserById(id);
        } catch (ApplicationException | SQLException e) {
            LOGGER.warn("Ошибка при удалении пользователя");
        }
        resp.sendRedirect("/");
//        getServletContext()
//                .getRequestDispatcher("/index.jsp")
//                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.parseLong(req.getQueryString().split("=")[1]);
        try {
            userService.updateById(id, req.getParameter("login"), req.getParameter("password"));
        } catch (ApplicationException | SQLException | NoSuchFieldException | IllegalAccessException e) {
            LOGGER.warn("Ошибка при обновлении пользователя");
        }
        resp.sendRedirect("/user/" + id);
//        getServletContext()
//                .getRequestDispatcher("/update-page.jsp")
//                .forward(req, resp);
    }
}
