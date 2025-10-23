package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import util.PasswordUtils;
import dao.UserDao;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String passwordAttempt = request.getParameter("password");
        try {
            User currentUser = UserDao.getUser(username);
            if (currentUser != null && PasswordUtils.verifyPassword(passwordAttempt, currentUser.getPassword())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("current_user", currentUser);
                request.getRequestDispatcher("views/secure/dashboard.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
