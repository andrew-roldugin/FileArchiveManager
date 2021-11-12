package ru.vsu.cs.group7.application.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.model.File;
import ru.vsu.cs.group7.model.FileArchive;
import ru.vsu.cs.group7.service.FileArchiveService;
import ru.vsu.cs.group7.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = "/archive/*")
public class ArchivesPage extends HttpServlet {

    private FileArchiveService fileArchiveService;
    private FileService fileService;
    public static final Logger LOGGER = LoggerFactory.getLogger(UsersPage.class.getSimpleName());

    @Override
    public void init() throws ServletException {
        final Services services = Services.getInstance(ApplicationContext.getInstance());
        this.fileArchiveService = services.getFileArchiveService();
        this.fileService = services.getFileService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            final String action = pathInfo.substring(1);
            if (action.equals("delete")) {
                doDelete(req, resp);
                return;
            } else if (action.equals("update")) {
                final Long id = Long.parseLong(req.getQueryString().split("=")[1]);
                String content = String.format("""
                                    <h1>Обновление архива</h1>
                                    <div id="wrapper">
                                    <form method="post" action="/archive/update?id=%s" autocomplete="off">
                                        <input type="text" name="name" placeholder="имя архива" />
                                        <button type="submit">Обновить</button>
                                    </form>
                                </div>
                        """, id);
                resp.setContentType("text/html");
                req.setAttribute("content", content);
                getServletContext()
                        .getRequestDispatcher("/update-page.jsp")
                        .forward(req, resp);
                return;
            } else {
                try {
                    final FileArchive fileArchive = fileArchiveService.getOneById(Long.parseLong(action));
                    req.setAttribute("archive", fileArchive);
                    final List<File> files = fileService.getAllFilesInArchiveById(fileArchive.getId());
                    req.setAttribute("files", files);
                } catch (ApplicationException | NumberFormatException e) {
                    LOGGER.warn("Ошибка при загрузке архива {}", e.getMessage());
                }
            }
        } else {
            try {
                final Collection<FileArchive> allArchives = fileArchiveService.getAllArchives();
                req.setAttribute("archives", allArchives);
            } catch (NumberFormatException | SQLException e) {
                LOGGER.warn("Ошибка при загрузке архивов {}", e.getMessage());
                req.setAttribute("archives", Collections.emptyList());
            }
        }
        getServletContext()
                .getRequestDispatcher("/archive.jsp")
                .forward(req, resp);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.parseLong(req.getQueryString().split("=")[1]);
        try {
            fileArchiveService.removeById(id);
        } catch (ApplicationException | SQLException e) {
            LOGGER.warn("Ошибка при удалении архива");
        }
        resp.sendRedirect("/archive");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getPathInfo().substring(1);
        if (action.equals("create")) {
            try {
                fileArchiveService.createArchive(req.getParameter("name"));
            } catch (ApplicationException | SQLException | NoSuchFieldException | IllegalAccessException e) {
                LOGGER.warn("Ошибка при создании архива");
            }
            resp.sendRedirect("/archive");
        } else if (action.equals("update")) {
            final Long id = Long.parseLong(req.getQueryString().split("=")[1]);
            try {
                fileArchiveService.update(id, req.getParameter("name"));
            } catch (ApplicationException | SQLException | NoSuchFieldException | IllegalAccessException e) {
                LOGGER.warn("Ошибка при обновлении архива");
            }
            resp.sendRedirect("/archive/" + id);
        }
    }
}
