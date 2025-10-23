package util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionUtils {

    public static boolean checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session != null && session.getAttribute("current_user") != null);
    }
}
