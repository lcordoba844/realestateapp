package util;

import jakarta.servlet.http.HttpServletRequest;
import model.Property;
import dao.CityDao;

public class PropertyFormHelper {

    public static Property buildPropertyFromRequest(HttpServletRequest request) {
        Property property = new Property();

        property.setTypeOfProperty(request.getParameter("typeOfProperty"));
        property.setDescription(request.getParameter("description"));
        property.setAddress(request.getParameter("address"));
        property.setDistrict(request.getParameter("district"));

        int cityId = Integer.parseInt(request.getParameter("cityId"));
        property.setCity(CityDao.getCityById(cityId));

        property.setNumberOfBathrooms(Integer.parseInt(request.getParameter("numberOfBathrooms")));
        property.setNumberOfBedrooms(Integer.parseInt(request.getParameter("numberOfBedrooms")));
        property.setNumberOfRooms(Integer.parseInt(request.getParameter("numberOfRooms")));
        property.setNumberOfFloors(Integer.parseInt(request.getParameter("numberOfFloors")));

        property.setMts2constructed(Double.parseDouble(request.getParameter("mts2constructed")));
        property.setMts2covered(Double.parseDouble(request.getParameter("mts2covered")));
        property.setMts2semiCovered(Double.parseDouble(request.getParameter("mts2semiCovered")));

        property.setPropertyAge(Integer.parseInt(request.getParameter("propertyAge")));
        property.setPropertyCondition(request.getParameter("propertyCondition"));
        property.setBuildingCategory(request.getParameter("buildingCategory"));
        property.setPrice(Double.parseDouble(request.getParameter("price")));
        property.setParking(Boolean.parseBoolean(request.getParameter("parking")));
        property.setOrientation(request.getParameter("orientation"));
        property.setOwnerId(Integer.parseInt(request.getParameter("ownerId")));
        property.setDisposition(Property.Disposition.valueOf(request.getParameter("disposition")));
        property.setImgSource(request.getParameter("imgSource"));

        return property;
    }
}