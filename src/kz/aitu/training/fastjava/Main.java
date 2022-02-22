package kz.aitu.training.fastjava;

import kz.aitu.training.fastjava.data.PostgreSQL;
import kz.aitu.training.fastjava.factory.XMLFactory;
import kz.aitu.training.fastjava.reader.XMLParser;
import kz.aitu.training.fastjava.repository.CustomerRepository;
import kz.aitu.training.fastjava.repository.OrderRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("This is program what create new XML file witch converting DB to XML");
        int option;
        String fileName;
        while (true) {
            System.out.println("Select one of types of work");
            System.out.println("1 - Import data from XML file to DB");
            System.out.println("2 - Export data from DB file to XML");
            try (Scanner scanner = new Scanner(System.in)) {
                int type = scanner.nextInt();
                if (type > 2 || type < 1) {
                    System.out.println("Select valid option!");
                    continue;
                }
                System.out.println("Select one of the options to export data");
                System.out.println("1 - Customer table");
                System.out.println("2 - Order table");
                try {
                    option = scanner.nextInt();
                    if (option > 3 || option < 0) {
                        System.out.println("Select valid option from list!");
                        continue;
                    }

                    switch (type) {
                        case 2 -> {
                            while(true) {
                                System.out.println("Enter file name (without filename extension)");
                                scanner.nextLine(); // sry it's java crutch
                                fileName = scanner.nextLine();
                                if (fileName.equals("") || fileName.contains("/") || fileName.contains("\\")) {
                                    System.out.println("Enter valid filename!");
                                    continue;
                                }
                                Path path = Paths.get(fileName + ".xml");
                                if (Files.exists(path)) {
                                    System.out.println("File already exist!");
                                    continue;
                                }
                                break;
                            }
                            switch (option) {
                                case 1 -> {
                                    CustomerRepository customerRepository = new CustomerRepository(PostgreSQL.getConnection());
                                    XMLFactory.exportCustomers(customerRepository.getAllCustomers(), fileName);
                                }
                                case 2 -> {
                                    OrderRepository orderRepository = new OrderRepository(PostgreSQL.getConnection());
                                    XMLFactory.exportOrders(orderRepository.getAllOrders(), fileName);
                                }
                            }
                        }
                        case 1 -> {
                            while(true) {
                            System.out.println("Enter file name (without filename extension)");
                            scanner.nextLine(); // sry it's java crutch
                            fileName = scanner.nextLine();
                            Path path = Paths.get(fileName + ".xml");
                            if (!Files.exists(path)) {
                                System.out.println("File not exist!");
                                continue;
                            }
                            break;
                        }
                            switch (option) {
                                case 1 -> XMLParser.importCustomers(fileName, PostgreSQL.getConnection());
                                case 2 -> {

                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Work Ended!");
                return;
            }
        }
    }
}
