package dao;

import model.City;
import model.Property;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyDao {

    public static boolean addNewProperty(Property property) throws SQLException {
        String sqlQuery = "INSERT INTO properties (type_of_property, description, address, district, id_city, number_of_bathrooms, number_of_bedrooms, number_of_rooms, " +
                "number_of_floors, mts2constructed, mts2covered, mts2semicovered, property_age, property_condition, building_category, price, parking, orientation, id_owner, disposition) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            City city = property.getCity();
            statement.setString(1, property.getTypeOfProperty());
            statement.setString(2, property.getDescription());
            statement.setString(3, property.getAddress());
            statement.setString(4, property.getDistrict());
            statement.setInt(5, city.getCityId());
            statement.setInt(6, property.getNumberOfBathrooms());
            statement.setInt(7, property.getNumberOfBedrooms());
            statement.setInt(8, property.getNumberOfRooms());
            statement.setInt(9, property.getNumberOfFloors());
            statement.setDouble(10, property.getMts2constructed());
            statement.setDouble(11, property.getMts2covered());
            statement.setDouble(12, property.getMts2semiCovered());
            statement.setInt(13, property.getPropertyAge());
            statement.setString(14, property.getPropertyCondition());
            statement.setString(15, property.getBuildingCategory());
            statement.setDouble(16, property.getPrice());
            statement.setBoolean(17, property.hasParking());
            statement.setString(18, property.getOrientation());
            statement.setInt(19, property.getOwnerId());
            statement.setString(20, property.getDisposition().name());

            return statement.executeUpdate() > 0;
        }
    }

    public static Property getPropertyById(int propertyId) {
        String sqlQuery = "SELECT * FROM properties WHERE property_id = ?";
        Property currentProperty = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            statement.setInt(1, propertyId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                currentProperty = new Property();
                currentProperty.setPropertyId(resultSet.getInt("property_id"));
                currentProperty.setTypeOfProperty(resultSet.getString("type_of_property"));
                currentProperty.setDescription(resultSet.getString("description"));
                currentProperty.setAddress(resultSet.getString("address"));
                currentProperty.setDistrict(resultSet.getString("district"));

                int cityId = resultSet.getInt("city_id");
                City city = CityDao.getCityById(cityId);
                currentProperty.setCity(city);

                currentProperty.setNumberOfBathrooms(resultSet.getInt("number_of_bathrooms"));
                currentProperty.setNumberOfBedrooms(resultSet.getInt("number_of_bedrooms"));
                currentProperty.setNumberOfRooms(resultSet.getInt("number_of_rooms"));
                currentProperty.setNumberOfFloors(resultSet.getInt("number_of_floors"));
                currentProperty.setMts2constructed(resultSet.getDouble("mts2constructed"));
                currentProperty.setMts2covered(resultSet.getDouble("mts2covered"));
                currentProperty.setMts2semiCovered(resultSet.getDouble("mts2semicovered"));
                currentProperty.setPropertyAge(resultSet.getInt("property_age"));
                currentProperty.setPropertyCondition(resultSet.getString("property_condition"));
                currentProperty.setBuildingCategory(resultSet.getString("building_category"));
                currentProperty.setPrice(resultSet.getDouble("price"));
                currentProperty.setParking(resultSet.getBoolean("parking"));
                currentProperty.setOrientation(resultSet.getString("orientation"));
                currentProperty.setOwnerId(resultSet.getInt("owner_id"));
                currentProperty.setDisposition(Property.Disposition.valueOf(resultSet.getString("disposition")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentProperty;
    }

    public static ArrayList<Property> getAllProperties() {
        ArrayList<Property> listProperties = new ArrayList<>();
        String sqlQuery = "SELECT * FROM properties";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Property property = new Property();
                property.setPropertyId(resultSet.getInt("id_property"));
                property.setTypeOfProperty(resultSet.getString("type_of_property"));
                property.setDescription(resultSet.getString("description"));
                property.setAddress(resultSet.getString("address"));
                property.setDistrict(resultSet.getString("district"));

                int cityId = resultSet.getInt("id_city");
                City city = CityDao.getCityById(cityId);
                property.setCity(city);

                property.setNumberOfBathrooms(resultSet.getInt("number_of_bathrooms"));
                property.setNumberOfBedrooms(resultSet.getInt("number_of_bedrooms"));
                property.setNumberOfRooms(resultSet.getInt("number_of_rooms"));
                property.setNumberOfFloors(resultSet.getInt("number_of_floors"));
                property.setMts2constructed(resultSet.getDouble("mts2constructed"));
                property.setMts2covered(resultSet.getDouble("mts2covered"));
                property.setMts2semiCovered(resultSet.getDouble("mts2semicovered"));
                property.setPropertyAge(resultSet.getInt("property_age"));
                property.setPropertyCondition(resultSet.getString("property_condition"));
                property.setBuildingCategory(resultSet.getString("building_category"));
                property.setPrice(resultSet.getDouble("price"));
                property.setParking(resultSet.getBoolean("parking"));
                property.setOrientation(resultSet.getString("orientation"));
                property.setOwnerId(resultSet.getInt("id_owner"));
                property.setDisposition(Property.Disposition.valueOf(resultSet.getString("disposition")));

                listProperties.add(property);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listProperties;
    }

    public static boolean updateProperty(Property property) throws SQLException {
        String sqlQuery = "UPDATE properties SET type_of_property = ?, description = ?, address = ?, district = ?, city_id = ?, number_of_bathrooms = ?, " +
                "number_of_bedrooms = ?, number_of_rooms = ?, number_of_floors = ?, mts2constructed = ?, mts2covered = ?, mts2semicovered = ?, property_age = ?, " +
                "property_condition = ?, building_category = ?, price = ?, parking = ?, orientation = ?, owner_id = ?, disposition = ? WHERE property_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            City city = property.getCity();
            statement.setString(1, property.getTypeOfProperty());
            statement.setString(2, property.getDescription());
            statement.setString(3, property.getAddress());
            statement.setString(4, property.getDistrict());
            statement.setInt(5, city.getCityId());
            statement.setInt(6, property.getNumberOfBathrooms());
            statement.setInt(7, property.getNumberOfBedrooms());
            statement.setInt(8, property.getNumberOfRooms());
            statement.setInt(9, property.getNumberOfFloors());
            statement.setDouble(10, property.getMts2constructed());
            statement.setDouble(11, property.getMts2covered());
            statement.setDouble(12, property.getMts2semiCovered());
            statement.setInt(13, property.getPropertyAge());
            statement.setString(14, property.getPropertyCondition());
            statement.setString(15, property.getBuildingCategory());
            statement.setDouble(16, property.getPrice());
            statement.setBoolean(17, property.hasParking());
            statement.setString(18, property.getOrientation());
            statement.setInt(19, property.getOwnerId());
            statement.setString(20, property.getDisposition().name());
            statement.setInt(21, property.getPropertyId());

            return statement.executeUpdate() > 0;
        }
    }
}