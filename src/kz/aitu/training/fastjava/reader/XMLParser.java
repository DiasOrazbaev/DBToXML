package kz.aitu.training.fastjava.reader;

import kz.aitu.training.fastjava.models.Customer;
import kz.aitu.training.fastjava.models.Order;
import kz.aitu.training.fastjava.repository.CustomerRepository;
import kz.aitu.training.fastjava.repository.OrderRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private XMLParser() {
        throw new IllegalStateException("Utility class");
    }
    static final String dir = System.getProperty("user.dir");
    static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public static void importCustomers(String filename, Connection connection) {
        List<Customer> customerList = new ArrayList<>();
        try {
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse(dir + "//" + filename + ".xml");
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("customer");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");

                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String balance = element.getElementsByTagName("balance").item(0).getTextContent();
                    String phone = element.getElementsByTagName("phone").item(0).getTextContent();

                    Customer customer = new Customer(
                            Integer.parseInt(id),
                            firstName,
                            lastName,
                            Double.parseDouble(balance),
                            phone
                    );
                    customerList.add(customer);
                }
            }
            CustomerRepository customerRepository = new CustomerRepository(connection);
            customerRepository.importCustomers(customerList);
            System.out.println("Done!");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static void importOrders(String filename, Connection connection) {
        List<Order> orderList = new ArrayList<>();
        try {
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse(dir + "//" + filename + ".xml");
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName("order");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");

                    String customerID = element.getElementsByTagName("customerID").item(0).getTextContent();
                    String itemID = element.getElementsByTagName("itemID").item(0).getTextContent();
                    String amount = element.getElementsByTagName("amount").item(0).getTextContent();
                    String totalPrice = element.getElementsByTagName("totalPrice").item(0).getTextContent();

                    Order order = new Order(
                            Integer.parseInt(id),
                            Integer.parseInt(customerID),
                            Integer.parseInt(itemID),
                            Integer.parseInt(amount),
                            Double.parseDouble(totalPrice)
                    );
                    orderList.add(order);
                }
            }
            OrderRepository orderRepository = new OrderRepository(connection);
            orderRepository.importOrders(orderList);
            System.out.println("Done!");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
