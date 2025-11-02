package dao;

import model.City;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CityDao {

    public static boolean addNewCity(City currentCity) {
        String sqlQuery = "INSERT INTO cities (description, province_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement statement = conn.prepareStatement(sqlQuery);) {
            statement.setString(1, currentCity.getCityName());
            statement.setInt(2, currentCity.getProvinceId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static City getCityById(int idCity) {
        String sqlQuery = "SELECT * FROM cities WHERE id_city = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
            statement.setInt(1, idCity);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                City currentCity = new City();
                currentCity.setCityId(resultSet.getInt("id_city"));
                currentCity.setCityName(resultSet.getString("description"));
                currentCity.setProvinceId(resultSet.getInt("id_province"));
                return currentCity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<City> getAllCities() {
        ArrayList<City> listCities = new ArrayList<>();
        String sqlQuery = "SELECT * FROM cities c";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery);
             ResultSet resultSet = statement.executeQuery();) {
           while (resultSet.next()) {
               City currentCity = new City();
               currentCity.setCityId(resultSet.getInt("id_city"));
               currentCity.setCityName(resultSet.getString("description"));
               currentCity.setProvinceId(resultSet.getInt("id_province"));
               listCities.add(currentCity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCities;
    }

    public static boolean updateCity(City updCity) {
        String sqlQuery = "UPDATE cities SET cityi_id, city_name = ?, province_id = ? WHERE city_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery)) {

            statement.setInt(1, updCity.getCityId());
            statement.setString(2, updCity.getCityName());
            statement.setInt(3, updCity.getProvinceId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
