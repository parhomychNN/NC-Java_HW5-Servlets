package ru.parhomych;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoServlet extends HttpServlet {

    Logger logger = Logger.getLogger("info");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<head><link rel=\"stylesheet\" href=\"css/login-style.css\"></head>");
        writer.println("<body>");

        writer.println("<h1>Информационная страничка о браузере</h1>");
        writer.println("<div  class=\"helptext\">");
        String userAgent = req.getHeader("user-agent");
        writer.println("<p><b>Ваш браузер: </b>" + userAgent + "</p>");
        //Date date = new Date(req.getDateHeader("date"));
        Date date = new Date();

        writer.println("<p><b>Дата : </b>" + date.toString() + "</p>");
        writer.println("<p>Вы можете <a href=\"index.html\">вернуться к списку заданий</a></p>");
        writer.println("</div>");

        writer.println("</body>");
        writer.println("</html>");

        writer.close();
    }
}
