package ru.vsu.cs.group7.application.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.User;
import ru.vsu.cs.group7.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class AuthPages extends HttpServlet {

    private UserService userService;
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthPages.class.getSimpleName());

    @Override
    public void init() throws ServletException {
        Services services = Services.getInstance(ApplicationContext.getInstance());
        userService = services.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath().split("/")[1];
        if (path.equals("login"))
            path = "login";
        else if (path.equals("register"))
            path = "register";
        else if (path.equals("logout")) {
            userService.logout();
            resp.sendRedirect("/");
            return;
        }

        getServletContext()
                .getRequestDispatcher(String.format("/%s.jsp", path))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String action = "";
        try {
            String path = req.getServletPath().split("/")[1];
            if (path.equals("login")) {
                userService.login(login, password);
                action = "авторизации";
            } else if (path.equals("register")) {
                final User user = userService.createUser(login, password);
                ApplicationContext.getInstance().setUser(user);
                action = "регистрации";
            }
            resp.sendRedirect("/user/" + ApplicationContext.getInstance().getUser().getId());
        } catch (ApplicationException | SQLException | NoSuchFieldException | IllegalAccessException e) {
            LOGGER.warn(String.format("Произошла ошибка при %s {}", action), e.getMessage());
            resp.sendRedirect("/");
        }
    }
}
