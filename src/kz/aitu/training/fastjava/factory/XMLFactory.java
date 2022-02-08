package kz.aitu.training.fastjava.factory;

import kz.aitu.training.fastjava.models.Order;
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

    private XMLFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean writeXML(List<Order> orderList) {
        final String dir = System.getProperty("user.dir");
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

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer =  transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(dir + "\\file.xml"));

            transformer.transform(domSource, streamResult);

            System.out.println("XML file successful created!!!");
            return true;

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
