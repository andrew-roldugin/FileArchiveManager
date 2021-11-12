package ru.vsu.cs.group7.application.web;

import ru.vsu.cs.group7.application.consoleApp.config.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexPage", urlPatterns = {"/"})
public class IndexPage extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        final String info = "<html><body><div>12</div></body></html>";
//        resp.setContentType("text/html");
//        resp.getWriter().println(info);

        if (ApplicationContext.getInstance().isLoggedIn())
            getServletContext()
                    .getRequestDispatcher("/auth-page.jsp")
                    .forward(req, resp);
//        req.setAttribute("information", new String[]{"a", "b", "c"});
//        getServletContext()
//                .getRequestDispatcher("/archive.jsp")
//                .forward(req, resp);
    }
}
