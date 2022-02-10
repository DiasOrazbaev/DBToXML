package kz.aitu.training.fastjava.factory;

import kz.aitu.training.fastjava.models.Customer;
import kz.aitu.training.fastjava.models.Order;
import kz.aitu.training.fastjava.util.OSCheck;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLFactory {

    public enum OSType {
        Windows, MacOS, Linux, Other
    }

    private XMLFactory() {
        throw new IllegalStateException("Utility class");
    }
    static final String dir = System.getProperty("user.dir");
    static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public static void exportCustomers(List<Customer> customerList, String fileName) {

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("customers");
            document.appendChild(root);

            for (Customer customer : customerList) {
                Element orderElement = document.createElement("customer");
                root.appendChild(orderElement);

                Attr attr = document.createAttribute("id");
                attr.setValue(Integer.toString(customer.getId()));
                orderElement.setAttributeNode(attr);

                // firstName
                Element firstName = document.createElement("firstName");
                firstName.appendChild(document.createTextNode(customer.getFirstName()));
                orderElement.appendChild(firstName);

                // lastName
                Element lastName = document.createElement("firstName");
                lastName.appendChild(document.createTextNode(customer.getLastName()));
                orderElement.appendChild(lastName);

                // balance
                Element balance = document.createElement("firstName");
                balance.appendChild(document.createTextNode(Double.toString(customer.getBalance())));
                orderElement.appendChild(balance);

                // phone
                Element phone = document.createElement("firstName");
                phone.appendChild(document.createTextNode(customer.getPhone()));
                orderElement.appendChild(phone);
            }

            transformerFactory(document,fileName);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static void transformerFactory(Document document, String fileName) throws TransformerException {
        StreamResult streamResult;
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer =  transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        OSCheck.OSType osType = OSCheck.getOperatingSystemType();

        switch (osType) {
            case Linux, MacOS -> streamResult = new StreamResult(new File(dir + '/' + fileName + ".xml"));
            case Windows -> streamResult = new StreamResult(new File(dir + "//" + fileName + ".xml"));
            default -> {
                System.out.println("Sorry i don't now how filesystem work with this OS");
                return;
            }
        }
        transformer.transform(domSource, streamResult);
        System.out.println("XML file successful created!!!");
    }

    public static void exportOrders(List<Order> orderList, String fileName) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("orders");
            document.appendChild(root);

            for (Order order : orderList) {
                Element orderElement = document.createElement("order");
                root.appendChild(orderElement);

                Attr attr = document.createAttribute("id");
                attr.setValue(Integer.toString(order.getId()));
                orderElement.setAttributeNode(attr);

                // customerID element
                Element customerID = document.createElement("customerID");
                customerID.appendChild(document.createTextNode(Integer.toString(order.getCustomerID())));
                orderElement.appendChild(customerID);

                // itemID
                Element itemID = document.createElement("itemID");
                itemID.appendChild(document.createTextNode(Integer.toString(order.getItemID())));
                orderElement.appendChild(itemID);

                // amount
                Element amount = document.createElement("amount");
                amount.appendChild(document.createTextNode(Integer.toString(order.getAmount())));
                orderElement.appendChild(amount);

                // totalPrice
                Element totalPrice = document.createElement("totalPrice");
                totalPrice.appendChild(document.createTextNode(Double.toString(order.getTotalPrice())));
                orderElement.appendChild(totalPrice);
            }

            transformerFactory(document, fileName);

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
