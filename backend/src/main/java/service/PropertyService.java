package service;

import dao.PropertyDao;
import model.Property;

import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyService {

    public ArrayList<Property> getAll() {
        return PropertyDao.getAllProperties();
    }

    public void create(Property newProperty) {
       try {PropertyDao.addNewProperty(newProperty);}
       catch (SQLException e) {
           e.printStackTrace();
       }
    }

    public void update(Property updProperty) {
        try {PropertyDao.updateProperty(updProperty);}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
