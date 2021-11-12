package ru.vsu.cs.group7.application.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;
import ru.vsu.cs.group7.application.consoleApp.config.Services;
import ru.vsu.cs.group7.exception.ApplicationException;
import ru.vsu.cs.group7.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/file/*")
public class FilesPage extends HttpServlet {

    private FileService fileService;
    public static final Logger LOGGER = LoggerFactory.getLogger(FilesPage.class.getSimpleName());

    @Override
    public void init() throws ServletException {
        this.fileService = Services.getInstance(ApplicationContext.getInstance()).getFileService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null) {
            final String action = pathInfo.substring(1);
            if (action.equals("delete")) {
                doDelete(req, resp);
            } else if (action.equals("update")) {
                final String[] temp = req.getQueryString().split("&");
                final Long fileId = Long.parseLong(temp[0].split("=")[1]);
                final Long archiveId = Long.parseLong(temp[1].split("=")[1]);
                String content = String.format("""
                                    <h1>Обновление файла</h1>
                                    <div>
                                    <form method="post" action="/file/update?id=%s&archiveId=%s" autocomplete="off">
                                        <input type="text" name="name" placeholder="новое имя файла" />
                                        <button type="submit">Обновить</button>
                                    </form>
                                </div>
                        """, fileId, archiveId);
                resp.setContentType("text/html");
                req.setAttribute("content", content);
                getServletContext()
                        .getRequestDispatcher("/update-page.jsp")
                        .forward(req, resp);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String[] temp = req.getQueryString().split("&");
        final Long fileId = Long.parseLong(temp[0].split("=")[1]);
        final Long archiveId = Long.parseLong(temp[1].split("=")[1]);
        try {
            fileService.removeFileById(fileId);
        } catch (SQLException e) {
            LOGGER.warn("Ошибка при удалении файла");
        }
        resp.sendRedirect("/archive/" + archiveId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getPathInfo().substring(1);
        if (action.equals("add")) {
            final Long archiveId = Long.parseLong(req.getQueryString().split("=")[1]);
            try {
                fileService.addNewFiles(archiveId, Arrays.stream(req.getParameter("files").split(" ")).toList());
            } catch (ApplicationException | SQLException | NoSuchFieldException | IllegalAccessException e) {
                LOGGER.warn("Ошибка при добавлении файлов");
            }
            resp.sendRedirect("/archive/" + archiveId);
        } else if (action.equals("update")) {
            final String[] temp = req.getQueryString().split("&");
            final Long fileId = Long.parseLong(temp[0].split("=")[1]);
            final Long archiveId = Long.parseLong(temp[1].split("=")[1]);
            try {
                fileService.updateById(fileId, req.getParameter("name"));
            } catch (ApplicationException | SQLException | NoSuchFieldException | IllegalAccessException e) {
                LOGGER.warn("Ошибка при обновлении файла");
            }
            resp.sendRedirect("/archive/" + archiveId);
        }
    }
}
