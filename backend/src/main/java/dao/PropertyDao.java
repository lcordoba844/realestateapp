package dao;

import model.City;
import model.Property;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PropertyDao {

    public static boolean addNewProperty(Property property) throws SQLException {
        String sqlQuery = "INSERT INTO properties (type_of_property, description, address, district, id_city, number_of_bathrooms, number_of_bedrooms, number_of_rooms," +
                " number_of_floors, mts2constructed, mts2covered, mts2semicovered, propertyAge, property_condition, building_category, price, parking, orientation, id_owner, disposition)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement statement = conn.prepareStatement(sqlQuery);) {
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
            return  statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean updatePropiedad(Propiedad prop) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionClass.connect();
            String tipo = prop.getTipo_propiedad();
            int cant_baños = prop.getCant_baños();
            int cant_dormitorios = prop.getCant_dormitorios();
            Localidad loc = prop.getLocalidad();
            int id_localidad = loc.getId();
            String descripcion = prop.getDescripcion();
            String direccion = prop.getDireccion();
            String estado = prop.getEstado();
            int id_propiedad = prop.getId_propiedad();
            double precio = prop.getPrecio();
            boolean cochera = prop.tieneCochera();

            String sqlQuery;
            if (prop instanceof Casa) {
                Casa casa = (Casa) prop;
                int cant_pisos = casa.getCant_pisos();
                double mts2_cubiertos = casa.getMts2cubiertos();
                double mts2_tot_terreno = casa.getMts2tot_terreno();

                sqlQuery = "UPDATE propiedades SET tipo_propiedad = ?, estado = ?, cant_dormitorios = ?, " +
                        "cant_baños = ?, cant_pisos = ?, mts2_tot_terreno = ?, mts2_cubiertos = ?, cochera = ?, descripcion = ?, direccion = ?, " +
                        "id_localidad = ?, precio = ? WHERE id_propiedad = ?";
                statement = conn.prepareStatement(sqlQuery);
                statement.setString(1, tipo);
                statement.setString(2, estado);
                statement.setInt(3, cant_dormitorios);
                statement.setInt(4, cant_baños);
                statement.setInt(5, cant_pisos);
                statement.setDouble(6, mts2_tot_terreno);
                statement.setDouble(7, mts2_cubiertos);
                statement.setBoolean(8, cochera);
                statement.setString(9, descripcion);
                statement.setString(10, direccion);
                statement.setInt(11, id_localidad);
                statement.setDouble(12, precio);
                statement.setInt(13, id_propiedad);

            } else if (prop instanceof Departamento){
                Departamento dpto = (Departamento) prop;
                boolean balcon = dpto.tieneBalcon();
                boolean terraza = dpto.tieneTerraza();
                String orientacion = dpto.getOrientacion();
                double mts2Tot = dpto.getMts2Tot();

                sqlQuery = "UPDATE propiedades SET tipo_propiedad = ?, estado = ?, cant_dormitorios = ?, " +
                        "cant_baños = ?, mts2tot = ?, balcon = ?, terraza = ?, cochera = ?, orientacion = ?, descripcion = ?, direccion = ?, " +
                        "id_localidad = ?, precio = ? WHERE id_propiedad = ?";
                statement = conn.prepareStatement(sqlQuery);
                statement.setString(1, tipo);
                statement.setString(2, estado);
                statement.setInt(3, cant_dormitorios);
                statement.setInt(4, cant_baños);
                statement.setDouble(5, mts2Tot);
                statement.setBoolean(6, balcon);
                statement.setBoolean(7, terraza);
                statement.setBoolean(8, cochera);
                statement.setString(9, orientacion);
                statement.setString(10, descripcion);
                statement.setString(11, direccion);
                statement.setInt(12, id_localidad);
                statement.setDouble(13, precio);
                statement.setInt(14, id_propiedad);
            }
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static Propiedad getOne(int idPropiedad) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Propiedad prop = null;
        try {
            conn = ConnectionClass.connect();
            String sqlQuery = "SELECT * FROM propiedades WHERE propiedades.id_propiedad = ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, idPropiedad);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String descripcion = resultSet.getString("descripcion");
                int cant_dormitorios = resultSet.getInt("cant_dormitorios");
                int cant_baños = resultSet.getInt("cant_baños");
                boolean cochera = resultSet.getBoolean("cochera");
                int id_propietario = resultSet.getInt("id_propietario");
                String tipo = resultSet.getString("tipo_propiedad");
                String estado = resultSet.getString("estado");
                String direccion = resultSet.getString("direccion");
                double precio = resultSet.getDouble("precio");
                String orientacion = resultSet.getString("orientacion");
                if ("Casa".equalsIgnoreCase(tipo)){
                    int cant_pisos = resultSet.getInt("cant_pisos");
                    double mts2cubiertos = resultSet.getDouble("mts2_cubiertos");
                    double mts2totales = resultSet.getDouble("mts2_tot_terreno");
                    prop = new Casa(cant_baños, cant_dormitorios, tipo, precio,
                            cochera, descripcion, id_propietario, direccion, estado, cant_pisos, orientacion, mts2cubiertos, mts2totales);
                } else if ("Departamento".equalsIgnoreCase(tipo)) {
                    boolean balcon = resultSet.getBoolean("balcon");
                    boolean terraza = resultSet.getBoolean("terraza");
                    double mts2 = resultSet.getDouble("mts2_tot");
                    prop = new Departamento(cant_baños, cant_dormitorios, tipo, precio,
                            cochera, descripcion, id_propietario, direccion, estado, mts2, balcon, orientacion, terraza );
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
        return prop;
    }

    public static List<Propiedad> getAllPropiedades() {
        List<Propiedad> listPropiedades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = ConnectionClass.connect();
            String sqlQuery = "SELECT * FROM propiedades";
            statement = conn.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idActual = resultSet.getInt("id_propiedad");
                String descripcion = resultSet.getString("descripcion");
                int cant_dormitorios = resultSet.getInt("cant_dormitorios");
                int cant_baños = resultSet.getInt("cant_baños");
                boolean cochera = resultSet.getBoolean("cochera");
                int id_localidad = resultSet.getInt("id_localidad");
                String tipo = resultSet.getString("tipo_propiedad");
                String estado = resultSet.getString("estado");
                String direccion = resultSet.getString("direccion");
                double precio = resultSet.getDouble("precio");
                int id_propietario = resultSet.getInt("id_propietario");
                String orientacion = resultSet.getString("orientacion");
                Localidad lActual = null;// DataLocalidad.getOne(id_localidad);
                if ("Casa".equalsIgnoreCase(tipo)) {
                    int cant_pisos = resultSet.getInt("cant_pisos");
                    double mts2cubiertos = resultSet.getDouble("mts2_cubiertos");
                    double mts2totales = resultSet.getDouble("mts2_tot_terreno");
                    Casa casa = new Casa(idActual, cant_baños, cant_dormitorios, tipo, precio,
                            cochera, descripcion, id_propietario, direccion, estado, cant_pisos, orientacion, mts2cubiertos, mts2totales);
                    listPropiedades.add(casa);
                } else if ("Departamento".equalsIgnoreCase(tipo)) {
                    boolean balcon = resultSet.getBoolean("balcon");
                    boolean terraza = resultSet.getBoolean("terraza");
                    double mts2 = resultSet.getDouble("mts2_tot");
                    Departamento dpto = new Departamento(idActual, cant_baños, cant_dormitorios, tipo, precio,
                            cochera, descripcion, id_propietario, direccion, estado, mts2, balcon, orientacion, terraza );
                    listPropiedades.add(dpto);
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

        return listPropiedades;
    }


    public static List<Propiedad> getPropiedadesForCliente(int idCliente) {
        List<Propiedad> listPropiedades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = ConnectionClass.connect();
            String sqlQuery = "SELECT * FROM propiedades p WHERE p.id_propietario = ?";
            statement = conn.prepareStatement(sqlQuery);
            statement.setInt(1, idCliente);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idActual = resultSet.getInt("id_propiedad");
                String descripcion = resultSet.getString("descripcion");
                int cant_dormitorios = resultSet.getInt("cant_dormitorios");
                int cant_baños = resultSet.getInt("cant_baños");
                boolean cochera = resultSet.getBoolean("cochera");
                String tipo = resultSet.getString("tipo_propiedad");
                String estado = resultSet.getString("estado");
                String direccion = resultSet.getString("direccion");
                double precio = resultSet.getDouble("precio");
                int id_propietario = resultSet.getInt("id_propietario");
                String orientacion = resultSet.getString("orientacion");
                if ("Casa".equalsIgnoreCase(tipo)) {
                    int cant_pisos = resultSet.getInt("cant_pisos");
                    double mts2cubiertos = resultSet.getDouble("mts2_cubiertos");
                    double mts2totales = resultSet.getDouble("mts2_tot_terreno");
                    Casa casa = new Casa(idActual, cant_baños, cant_dormitorios, tipo, precio,
                            cochera, descripcion, id_propietario, direccion, estado, cant_pisos, orientacion, mts2cubiertos, mts2totales );
                    listPropiedades.add(casa);
                } else if ("Departamento".equalsIgnoreCase(tipo)) {
                    boolean balcon = resultSet.getBoolean("balcon");
                    boolean terraza = resultSet.getBoolean("terraza");
                    double mts2 = resultSet.getDouble("mts2_tot");
                    Departamento dpto = new Departamento(idActual, cant_baños, cant_dormitorios, tipo, precio,
                            cochera, descripcion, id_propietario, direccion, estado, mts2, balcon, orientacion, terraza );
                    listPropiedades.add(dpto);
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

        return listPropiedades;
    }


    public static List<Propiedad> getAllPropiedades2() {
        List<Propiedad> listPropiedades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement propStmt = null;
        PreparedStatement locStmt = null;
        ResultSet propiedadResultSet = null;
        ResultSet localidadResultSet = null;

        try {
            conn = ConnectionClass.connect();
            String locQuery = "SELECT * FROM localidades";
            locStmt = conn.prepareStatement(locQuery);
            localidadResultSet = locStmt.executeQuery();

            Map<Integer, Localidad> localidadesMap = new HashMap<>();
            while (localidadResultSet.next()) {
                Localidad localidad = new Localidad();
                localidad.setId(localidadResultSet.getInt("id_localidad"));
                localidad.setDescripcion(localidadResultSet.getString("descripcion"));
                localidadesMap.put(localidad.getId(), localidad);
            }

            String propQuery = "SELECT * FROM propiedades";
            propStmt = conn.prepareStatement(propQuery);
            propiedadResultSet = propStmt.executeQuery();

            while (propiedadResultSet.next()) {
                int idLocalidad = propiedadResultSet.getInt("id_localidad");
                Localidad localidad = localidadesMap.get(idLocalidad);
                if (localidad != null) {
                    int idActual = propiedadResultSet.getInt("id_propiedad");
                    String descripcion = propiedadResultSet.getString("descripcion");
                    int cant_dormitorios = propiedadResultSet.getInt("cant_dormitorios");
                    int cant_baños = propiedadResultSet.getInt("cant_baños");
                    boolean cochera = propiedadResultSet.getBoolean("cochera");
                    int id_localidad = propiedadResultSet.getInt("id_localidad");
                    int id_propietario = propiedadResultSet.getInt("id_propietario");
                    String estado = propiedadResultSet.getString("estado");
                    String direccion = propiedadResultSet.getString("direccion");
                    double precio = propiedadResultSet.getDouble("precio");
                    Localidad lActual = DataLocalidad.getOne(id_localidad);
                    String orientacion = propiedadResultSet.getString("orientacion");
                    String tipoPropiedad = propiedadResultSet.getString("tipoPropiedad");
                    if ("Casa".equals(tipoPropiedad)) {
                        int cant_pisos = propiedadResultSet.getInt("cant_pisos");
                        double mts2cubiertos = propiedadResultSet.getDouble("mts2_cubiertos");
                        double mts2totales = propiedadResultSet.getDouble("mts2_tot_terreno");
                        Casa casa = new Casa(idActual, cant_baños, cant_dormitorios, tipoPropiedad, precio,
                                cochera, descripcion, id_propietario, direccion, estado, cant_pisos,orientacion, mts2cubiertos, mts2totales);
                        listPropiedades.add(casa);
                    } else if ("Departamento".equals(tipoPropiedad)) {
                        boolean balcon = propiedadResultSet.getBoolean("balcon");
                        boolean terraza = propiedadResultSet.getBoolean("terraza");

                        double mts2 = propiedadResultSet.getDouble("mts2_tot");
                        Departamento dpto = new Departamento(idActual, cant_baños, cant_dormitorios, tipoPropiedad, precio,
                                cochera, descripcion, id_propietario, direccion, estado, mts2, balcon, orientacion, terraza );
                        listPropiedades.add(dpto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (localidadResultSet != null) {
                    localidadResultSet.close();
                }
                if (propiedadResultSet != null) {
                    propiedadResultSet.close();
                }
                if (locStmt != null) {
                    locStmt.close();
                }
                if (propStmt != null) {
                    propStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPropiedades;
    }

    public static List<Propiedad> getAllPropiedadesForCliente(int idCliente) {
        List<Propiedad> listPropiedades = new ArrayList<>();
        Connection conn = null;
        PreparedStatement propStmt = null;
        PreparedStatement locStmt = null;
        ResultSet propiedadResultSet = null;
        ResultSet localidadResultSet = null;

        try {
            conn = ConnectionClass.connect();
            String locQuery = "SELECT * FROM localidades";
            locStmt = conn.prepareStatement(locQuery);
            localidadResultSet = locStmt.executeQuery();

            Map<Integer, Localidad> localidadesMap = new HashMap<>();
            while (localidadResultSet.next()) {
                Localidad localidad = new Localidad();
                localidad.setId(localidadResultSet.getInt("id_localidad"));
                localidad.setDescripcion(localidadResultSet.getString("descripcion"));
                localidadesMap.put(localidad.getId(), localidad);
            }
            String propQuery = "SELECT * FROM propiedades WHERE propiedades.id_propietario = ?";
            propStmt = conn.prepareStatement(propQuery);
            propStmt.setInt(1, idCliente);
            propiedadResultSet = propStmt.executeQuery();

            while (propiedadResultSet.next()) {
                int idLocalidad = propiedadResultSet.getInt("id_ciudad");
                Localidad localidad = localidadesMap.get(idLocalidad);
                if (localidad != null) {
                    int idActual = propiedadResultSet.getInt("id_propiedad");
                    String descripcion = propiedadResultSet.getString("descripcion");
                    int cant_dormitorios = propiedadResultSet.getInt("cant_dormitorios");
                    int cant_baños = propiedadResultSet.getInt("cant_baños");
                    boolean cochera = propiedadResultSet.getBoolean("cochera");
                    int id_localidad = propiedadResultSet.getInt("id_localidad");
                    int id_propietario = propiedadResultSet.getInt("id_propietario");
                    String estado = propiedadResultSet.getString("estado");
                    String direccion = propiedadResultSet.getString("direccion");
                    double precio = propiedadResultSet.getDouble("precio");
                    String orientacion = propiedadResultSet.getString("orientacion");
                    Localidad lActual = DataLocalidad.getOne(id_localidad);
                    String tipoPropiedad = propiedadResultSet.getString("tipoPropiedad");
                    if ("Casa".equals(tipoPropiedad)) {
                        int cant_pisos = propiedadResultSet.getInt("cant_pisos");
                        double mts2cubiertos = propiedadResultSet.getDouble("mts2_cubiertos");
                        double mts2totales = propiedadResultSet.getDouble("mts2_tot_terreno");
                        Casa casa = new Casa(idActual, cant_baños, cant_dormitorios, tipoPropiedad, precio,
                                cochera, descripcion, id_propietario, direccion, estado, cant_pisos, orientacion, mts2cubiertos, mts2totales);
                        listPropiedades.add(casa);
                    } else if ("Departamento".equals(tipoPropiedad)) {
                        boolean balcon = propiedadResultSet.getBoolean("balcon");
                        boolean terraza = propiedadResultSet.getBoolean("terraza");
                        double mts2 = propiedadResultSet.getDouble("mts2_tot");
                        Departamento dpto = new Departamento(idActual, cant_baños, cant_dormitorios, tipoPropiedad, precio,
                                cochera, descripcion, id_propietario, direccion, estado, mts2, balcon, orientacion, terraza );
                        listPropiedades.add(dpto);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (localidadResultSet != null) {
                    localidadResultSet.close();
                }
                if (propiedadResultSet != null) {
                    propiedadResultSet.close();
                }
                if (locStmt != null) {
                    locStmt.close();
                }
                if (propStmt != null) {
                    propStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listPropiedades;
    }
}
