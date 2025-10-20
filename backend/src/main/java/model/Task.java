package model;

import java.util.Date;

public class Task {
    private int taskId;
    private String description;
    private TaskState taskState;
    private double price;
    private Date dateOfAssignment;
    private Date dateOfRealization;
    private boolean isPaid;
    private Date dateOfPayment;
    private Property property;

    public Task(String description, TaskState status, double price, boolean isPaid, Date dateOfAssignment, Date dateOfPayment, Property property) {
        this.setDescription(description);
        this.setTaskState(status);
        this.setPrice(price);
        this.setDateOfAssignment(dateOfAssignment);
        this.setPaid(isPaid);
        this.setDateOfPayment(dateOfPayment);
        this.setProperty(property);
    }

    public enum TaskState {
        PENDING,
        FINISHED
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateOfAssignment() {
        return dateOfAssignment;
    }

    public void setDateOfAssignment(Date dateOfAssignment) {
        this.dateOfAssignment = dateOfAssignment;
    }

    public Date getDateOfRealization() {
        return dateOfRealization;
    }

    public void setDateOfRealization(Date dateOfRealization) {
        this.dateOfRealization = dateOfRealization;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property prop) {
        this.property = prop;
    }
}
