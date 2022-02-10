package kz.aitu.training.fastjava.models;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private double balance;
    private String phone;

    public Customer(int id, String firstName, String lastName, double balance, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                ", phone='" + phone + '\'' +
                '}';
    }
}
