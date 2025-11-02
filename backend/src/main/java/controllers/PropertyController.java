package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Property;
import service.PropertyService;
import util.PropertyFormHelper;

import java.io.IOException;

@WebServlet("/property")
public class PropertyController extends HttpServlet {
    private final PropertyService propertyService = new PropertyService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "list":
                request.setAttribute("properties", propertyService.getAll());
                request.getRequestDispatcher("/WEB-INF/views/secure/property-list.jsp").forward(request, response);
                break;
            case "create":
                request.getRequestDispatcher("/WEB-INF/views/secure/property-form.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "save":
                Property newProperty = PropertyFormHelper.buildPropertyFromRequest(request);
                propertyService.create(newProperty);
                response.sendRedirect("property?action=list");
                break;
            case "update":
                Property updProperty = PropertyFormHelper.buildPropertyFromRequest(request);
                propertyService.update(updProperty);
                response.sendRedirect("property?action=list");
                break;
        }
    }
}
