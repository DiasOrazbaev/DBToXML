package kz.aitu.training.fastjava.models;

public class Order {
    private int id;
    private int customerID;
    private int itemID;
    private int amount;
    private double totalPrice;

    public Order(int id, int customerID, int itemID, int amount, double totalPrice) {
        this.id = id;
        this.customerID = customerID;
        this.itemID = itemID;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getItemID() {
        return itemID;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
