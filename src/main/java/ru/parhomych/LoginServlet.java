package ru.parhomych;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private final String passwordRight = "test";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Ответ сервера</h1>");

        String name = req.getParameter("login");
        String password = req.getParameter("password");

        writer.println("<p>Привет, " + name +"! Я никому не скажу твой пароль. Хотяяяяяяяяяяя, держи: " +
                password + "</p>");
        writer.close();*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("login");
        String password = req.getParameter("password");

        char[] passwordCharArray = name.toCharArray();
        for (char ch : passwordCharArray) {
            if (((ch >= 'a')&&(ch <= 'z')) || ((ch >= 'A')&&(ch <= 'Z'))|| ((ch >= '0')&&(ch <= '9'))){
                // OK
            } else {
                PrintWriter writer = resp.getWriter();
                writer.println("<!DOCTYPE html>");
                writer.println("<head><link rel=\"stylesheet\" href=\"css/login-style.css\"></head>");
                writer.println("<body>");
                writer.println("<h1>Введите имя латинскими буквами, пожалуйста</h1>");
                writer.println("<p class=\"helptext\">Ваш логин должен содержать буквы a-z, a-Z</p>");
                writer.println("<p class=\"helptext\">Вы можете <a href=\"index.html\">вернуться к списку заданий</a> " +
                        "или <a href=\"login.html\">попытаться залогиниться снова</a></p>");
                writer.println("</body>");
                writer.println("</html>");
                writer.close();
            }
        }

        if (password.equals(passwordRight))
        {
            PrintWriter writer = resp.getWriter();
            writer.println("<!DOCTYPE html>");
            writer.println("<head><link rel=\"stylesheet\" href=\"css/login-style.css\"></head>");
            writer.println("<body>");
            writer.println("<h1>Поздравляю! Вы знаете пароль!</h1>");
            writer.println("<p class=\"helptext\">Привет, " + name +"! Ты становишься тайным участником общества \"Я знаю пароль\"</p>");
            writer.println("<p class=\"helptext\">Вы можете <a href=\"index.html\">вернуться к списку заданий</a></p>");
            writer.println("</body>");
            writer.println("</html>");
            writer.close();
        } else {
            resp.sendError(401);
        }

    }
}
