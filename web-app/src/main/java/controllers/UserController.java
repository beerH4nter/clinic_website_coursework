package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController extends HttpServlet {

    // Обработка GET-запроса
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Установка типа контента
        response.setContentType("application/json");

        // Пример ответа в формате JSON
        String jsonResponse = "{\"message\": \"Hello from the backend!\"}";

        // Запись ответа
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    // Обработка POST-запроса
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Чтение параметров из POST-запроса
        String username = request.getParameter("username");

        // Ответ в формате JSON
        String jsonResponse = "{\"message\": \"Hello, " + username + "\"}";

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
