<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Property" %>
<%@ page import="model.City" %>
<html>
<head>
    <title>Property List</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
    </style>
</head>
<body>
<h2>All Properties</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Type</th>
        <th>Description</th>
        <th>Address</th>
        <th>District</th>
        <th>City</th>
        <th>Bathrooms</th>
        <th>Bedrooms</th>
        <th>Rooms</th>
        <th>Floors</th>
        <th>Price</th>
        <th>Parking</th>
        <th>Disposition</th>
        <th>Owner ID</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Property> properties = (List<Property>) request.getAttribute("properties");
        if (properties != null && !properties.isEmpty()) {
            for (Property p : properties) {
                City city = p.getCity();
    %>
    <tr>
        <td><%= p.getPropertyId() %></td>
        <td><%= p.getTypeOfProperty() %></td>
        <td><%= p.getDescription() %></td>
        <td><%= p.getAddress() %></td>
        <td><%= p.getDistrict() %></td>
        <td><%= city != null ? city.getCityName() : "N/A" %></td>
        <td><%= p.getNumberOfBathrooms() %></td>
        <td><%= p.getNumberOfBedrooms() %></td>
        <td><%= p.getNumberOfRooms() %></td>
        <td><%= p.getNumberOfFloors() %></td>
        <td><%= p.getPrice() %></td>
        <td><%= p.hasParking() ? "Yes" : "No" %></td>
        <td><%= p.getDisposition() != null ? p.getDisposition().name() : "N/A" %></td>
        <td><%= p.getOwnerId() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="14">No properties found.</td>
    </tr>
    <% } %>
    </tbody>
</table>

<br>
<a href="property?action=create">Add New Property</a>

</body>
</html>