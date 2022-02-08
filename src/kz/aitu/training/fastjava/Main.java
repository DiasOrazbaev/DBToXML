package kz.aitu.training.fastjava;

import kz.aitu.training.fastjava.data.PostgreSQL;
import kz.aitu.training.fastjava.factory.XMLFactory;
import kz.aitu.training.fastjava.repository.OrderRepository;

public class Main {

    public static void main(String[] args) {
        System.out.println("This is program what create new XML file witch converting DB to XML");
        OrderRepository orderRepository = new OrderRepository(PostgreSQL.getConnection());
        XMLFactory.writeXML(orderRepository.getAllOrders());
        System.out.println("WoW this works!!! :) ");
    }
}
