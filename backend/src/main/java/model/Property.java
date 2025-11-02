package model;

import java.util.ArrayList;
import java.util.List;

public class Property {
    public static enum Disposition {
        FRONT,
        BACK
    }

    private int propertyId;
    private String typeOfProperty;
    private String description;
    private String address;
    private String district;
    private City city;
    private int numberOfBathrooms;
    private int numberOfBedrooms;
    private int numberOfRooms;
    private int numberOfFloors;
    private Double mts2constructed;
    private Double mts2covered;
    private Double mts2semiCovered;
    private int propertyAge;
    private String propertyCondition;
    private String buildingCategory; // Could be an ENUM
    private double price;
    private boolean parking;
    private ArrayList<Service> services;
    private String orientation;
    private List<Task> taskList;
    private int ownerId;
    private Disposition disposition;
    private String imgSource;

    public Property() {}

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getTypeOfProperty() {
        return typeOfProperty;
    }

    public void setTypeOfProperty(String typeOfProperty) {
        this.typeOfProperty = typeOfProperty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Double getMts2constructed() {
        return mts2constructed;
    }

    public void setMts2constructed(Double mts2constructed) {
        this.mts2constructed = mts2constructed;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public Double getMts2semiCovered() {
        return mts2semiCovered;
    }

    public void setMts2semiCovered(Double mts2semiCovered) {
        this.mts2semiCovered = mts2semiCovered;
    }

    public Double getMts2covered() {
        return mts2covered;
    }

    public void setMts2covered(Double mts2covered) {
        this.mts2covered = mts2covered;
    }

    public int getPropertyAge() {
        return propertyAge;
    }

    public void setPropertyAge(int propertyAge) {
        this.propertyAge = propertyAge;
    }

    public String getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(String propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public String getBuildingCategory() {
        return buildingCategory;
    }

    public void setBuildingCategory(String buildingCategory) {
        this.buildingCategory = buildingCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean hasParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Disposition getDisposition() {
        return disposition;
    }

    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }
}
