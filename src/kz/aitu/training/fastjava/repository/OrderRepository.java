package kz.aitu.training.fastjava.repository;

import kz.aitu.training.fastjava.models.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderRepository {
    private final Connection connection;

    public OrderRepository(Connection db) {
        connection = db;
    }

    public List<Order> getAllOrders() {
        String query = "select * from orders order by id;";

        // try with resources (means after doing try block, streams below automatically closed)
        try(
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                ) {

            List<Order> orderList = new ArrayList<>();
            while(resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("customerID"),
                        resultSet.getInt("itemID"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("totalPrice")
                );
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
