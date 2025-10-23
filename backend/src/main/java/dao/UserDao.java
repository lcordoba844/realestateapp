package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.UserAlreadyExistsException;
import util.DatabaseConnection;
import model.User;
import model.User.Role;

public class UserDao {

    public static void addNewUser(String username, String password) throws IllegalArgumentException, UserAlreadyExistsException {
        if (username == null || password == null) {
            throw new IllegalArgumentException("El usuario o la contraseña son null");
        }

        if (password == null) {
            throw new IllegalArgumentException("Error al hashear la contraseña");
        }

        if (!validateExistence(username)) {
            try {
                Connection conn = DatabaseConnection.connect();
                String sqlQuery = "INSERT INTO `RealEstate`.`Users` (`username`, `password`, `id_user_type`) VALUES (?, ?, '2');";
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }
    }

    private static boolean validateExistence(String username) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.connect();
            String sqlQuery = "SELECT * FROM users u WHERE u.username = ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            return resultSet.next(); // Devuelve true si el nombre de usuario existe en la base de datos, de lo contrario, devuelve false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static User getUser(String username) {
        User currentUser = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role = null;
        try {
            conn = DatabaseConnection.connect();
            String sqlQuery = "SELECT * FROM RealEstate.users u WHERE u.username = ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User.Role currentUserRole = null;
                int userType = resultSet.getInt("id_user_type");
                if (userType == 1) {
                    currentUserRole = User.Role.ADMIN;
                } else if (userType == 2 ) {
                    currentUserRole = User.Role.CLIENT;
                }
                currentUser = new User(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        currentUserRole
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return currentUser;
    }
}
