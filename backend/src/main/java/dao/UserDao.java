package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.PasswordHashing;
import util.UserAlreadyExistsException;
import util.DatabaseConnection;
import model.User;
import model.User.Role;

public class UserDao {

    public static void addNewUser(String username, String pass) throws IllegalArgumentException, UserAlreadyExistsException {
        if (username == null || pass == null) {
            throw new IllegalArgumentException("El usuario o la contraseña son null");
        }

        String password = PasswordHashing.hashPassword(pass);

        if (password == null) {
            throw new IllegalArgumentException("Error al hashear la contraseña");
        }

        if (!validateExistence(username)) {
            try {
                Connection conn = DatabaseConnection.connect();
                String sqlQuery = "INSERT INTO `inmobiliaria`.`usuarios` (`nombre_usuario`, `contraseña`, `id_tipo_usuario`) VALUES (?, ?, '2');";
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
            String sqlQuery = "SELECT * FROM usuarios u WHERE u.nombre_usuario = ?";
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

    public static User getUser(String username, String password) {
        User currentUser = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Role role = null;
        try {
            conn = DatabaseConnection.connect();
            String sqlQuery = "SELECT * FROM inmobiliaria.usuarios u WHERE u.nombre_usuario = ? AND u.contraseña = ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                currentUser = new User();
                currentUser.setUsername(resultSet.getString("nombre_usuario"));
                currentUser.setPassword(resultSet.getString("contraseña"));
                if ("Administrador".equalsIgnoreCase(resultSet.getString("tipo_usuario"))) {
                    currentUser.setRole(User.Role.ADMIN);
                } else if ("Cliente".equalsIgnoreCase(resultSet.getString("tipo_usuario"))) {
                    currentUser.setRole(User.Role.CLIENT);
                }

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
