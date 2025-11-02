<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add / Edit Property</title>
</head>
<body>
<h2>Property Form</h2>

<form action="${pageContext.request.contextPath}/property" method="post">
    <!-- hidden field for CRUD action -->
    <input type="hidden" name="action" value="save">

    Type of Property: <input type="text" name="typeOfProperty" placeholder="Apartment, House, etc." required><br>

    Description: <textarea name="description" placeholder="Description" required></textarea><br>

    Address: <input type="text" name="address" placeholder="Street 123" required><br>
    District: <input type="text" name="district" placeholder="District" required><br>

    City ID: <input type="number" name="cityId" placeholder="City ID" required><br>

    Number of Bathrooms: <input type="number" name="numberOfBathrooms" required><br>
    Number of Bedrooms: <input type="number" name="numberOfBedrooms" required><br>
    Number of Rooms: <input type="number" name="numberOfRooms" required><br>
    Number of Floors: <input type="number" name="numberOfFloors" required><br>

    M² Constructed: <input type="number" step="0.01" name="mts2constructed"><br>
    M² Covered: <input type="number" step="0.01" name="mts2covered"><br>
    M² Semi-Covered: <input type="number" step="0.01" name="mts2semiCovered"><br>

    Property Age: <input type="number" name="propertyAge"><br>
    Property Condition: <input type="text" name="propertyCondition"><br>
    Building Category: <input type="text" name="buildingCategory"><br>

    Price: <input type="number" step="0.01" name="price"><br>
    Parking:
    <select name="parking">
        <option value="true">Yes</option>
        <option value="false">No</option>
    </select><br>

    Orientation: <input type="text" name="orientation"><br>
    Owner ID: <input type="number" name="ownerId"><br>

    Disposition:
    <select name="disposition">
        <option value="FRONT">Front</option>
        <option value="BACK">Back</option>
    </select><br>

    Image Source URL: <input type="text" name="imgSource"><br>

    <input type="submit" value="save">
</form>
</body>
</html>