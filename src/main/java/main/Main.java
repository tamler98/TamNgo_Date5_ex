package main;

import configuration.JPAConfig;
import entity.OrderDetails;
import entity.Orders;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.OrderDetailRepository;
import repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderRepository orderRepository = context.getBean("orderRepository", OrderRepository.class);
    static OrderDetailRepository orderDetailRepository = context.getBean("orderDetailRepository", OrderDetailRepository.class);
    public static void main(String[] args) {
        System.out.println("Welcome to Tanz Industry");
        System.out.println("Develop By Thomas Tanz");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        int choose;
        do{
            System.out.println("1. Create new order.");
            System.out.println("2. Create new orderDetail.");
            System.out.println("3. List all order and order details in the database.");
            System.out.println("4. Get an order and orderDetails by order id.");
            System.out.println("5. List all the orders in the current month.");
            System.out.println("6. List all orders which have total amount more than 1,000USD.");
            System.out.println("7. List all orders buy Java book.");
            System.out.println("8. Out Of Program.");
            System.out.print("Enter your choose: ");
            switch (choose = sc.nextInt()){
                case 1:
                    createNewOrder();
                    break;
                case 2:
                    createNewOrderDetail();
                    break;
                case 3:
                    listAllOrderAndOrderDetail();
                    break;
                case 4:
                    getOrderAndOrderDetailByOrderId();
                    break;
                case 5:
                    listAllOrderInCurrentMonth();
                    break;
                case 6:
                    listAllOrderHaveTotalAmountMoreThan1000();
                    break;
                case 7:
                    listAllOrderBuyProductByName();
                    break;
                case 8:
                    exit = true;
                    break;
            }
        } while (!exit);
    }
    public static void listAllOrderAndOrderDetail() {
        System.out.println("* List all order");
        List<Orders> listOrder = (List<Orders>) orderRepository.findAll();
        for (Orders order: listOrder) {
            System.out.println(order.toString());
            List<OrderDetails> listOrderDetail = (List<OrderDetails>) orderDetailRepository.getOrderDetailByOrderId(order.getId());
            for (OrderDetails orderDetailAll: listOrderDetail) {
                System.out.println(orderDetailAll.toString());
            }
        }
    }
    public static void getOrderAndOrderDetailByOrderId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("* Insert OrderId you want to get: ");
        int orderId = sc.nextInt();
        Optional<Orders> orderById = orderRepository.findById(orderId);
        System.out.println("Get Order By orderId: "+orderById.toString());
        List<OrderDetails> orderDetailByOrderID = orderDetailRepository.getOrderDetailByOrderId(orderId);
        for (OrderDetails orderDetails: orderDetailByOrderID) {
            System.out.println(orderDetails.toString());
        }
    }
    public static void listAllOrderInCurrentMonth() {
        System.out.println("* List all the orders in the current month");
        List<Orders> listOrderInCurrentMonth = orderRepository.getOrderByCurMonth();
        for (Orders order: listOrderInCurrentMonth) {
            System.out.println(order.toString());
        }
    }

    public static void listAllOrderHaveTotalAmountMoreThan1000() {
        System.out.print("* List all orders which have total amount more than 1,000USD");
        List<Orders> listOrderMoreThan1000 = orderRepository.getOrderBySumPrice();
        for (Orders order: listOrderMoreThan1000) {
            System.out.println(order.toString());
        }
    }
    public static void listAllOrderBuyProductByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("* Insert Name Of Product You Want to find: ");
        String nameOfProduct = sc.nextLine();
        List<Orders> listOrderByName = orderRepository.getOrderByProductName(nameOfProduct);
        for (Orders order: listOrderByName) {
            System.out.println(order.toString());
        }
    }

    public static Orders insertNewOrder() {
        Scanner sc = new Scanner(System.in);
        Orders order = new Orders();
        System.out.println("Customer Name: ");
        order.setCustomerName(sc.nextLine());
        System.out.println("Customer Address: ");
        order.setCustomerAddress(sc.nextLine());
        order.setOrderDate(LocalDate.now());
        return order;
    }

    public static OrderDetails insertNewOrderDetail() {
        Scanner sc = new Scanner(System.in);
        OrderDetails orderDetails = new OrderDetails();
        System.out.print("Product Name: ");
        orderDetails.setProductName(sc.nextLine());
        System.out.print("Quantity: ");
        orderDetails.setQuantity(sc.nextInt());
        System.out.print("UnitPrice: ");
        orderDetails.setUnitPrice(sc.nextDouble());
//        orderDetailRepository.save(orderDetails);
        return orderDetails;
    }
    public static void createNewOrder() {
        Orders orders = insertNewOrder();
        orderRepository.save(orders);
        System.out.println("Create New Order Successfully");
    }
    public static void createNewOrderDetail() {
        Scanner sc = new Scanner(System.in);
        OrderDetails orderDetails = insertNewOrderDetail();
        System.out.println("Insert to orderID = ");
        Optional<Orders> order = orderRepository.findById(sc.nextInt());
        orderDetails.setOrders(order.get());
        orderDetailRepository.save(orderDetails);
        System.out.println("Create New OrderDetail Successfully");
    }










































//    private static void createNewOrders() {
//    // Add Oder 1
//        Orders order1 = new Orders();
//        order1.setId(1);
//        order1.setOrderDate(LocalDate.now());
//        order1.setCustomerName("Thomas Tanz");
//        order1.setCustomerAddress("Danang City");
//        orderRepository.save(order1);
//
//        OrderDetails orderDetail1 = new OrderDetails();
//        orderDetail1.setProductName("Tess");
//        orderDetail1.setQuantity(3);
//        orderDetail1.setUnitPrice(110);
//        orderDetail1.setOrders(order1);
//        orderDetailRepository.save(orderDetail1);
//
//        OrderDetails orderDetail2 = new OrderDetails();
//        orderDetail2.setProductName("Javis");
//        orderDetail2.setQuantity(2);
//        orderDetail2.setUnitPrice(112);
//        orderDetail2.setOrders(order1);
//        orderDetailRepository.save(orderDetail2);
//
//        OrderDetails orderDetail3 = new OrderDetails();
//        orderDetail3.setProductName("Noi com dien");
//        orderDetail3.setQuantity(12);
//        orderDetail3.setUnitPrice(232);
//        orderDetail3.setOrders(order1);
//        orderDetailRepository.save(orderDetail3);
//
//        OrderDetails orderDetail4 = new OrderDetails();
//        orderDetail4.setProductName("may giat");
//        orderDetail4.setQuantity(21);
//        orderDetail4.setUnitPrice(123);
//        orderDetail4.setOrders(order1);
//        orderDetailRepository.save(orderDetail4);
//
//        OrderDetails orderDetail5 = new OrderDetails();
//        orderDetail5.setProductName("Lo vi song");
//        orderDetail5.setQuantity(15);
//        orderDetail5.setUnitPrice(121);
//        orderDetail5.setOrders(order1);
//        orderDetailRepository.save(orderDetail5);
//
//    // Add Oder 2
//        Orders order2 = new Orders();
//        order2.setId(2);
//        order2.setOrderDate(LocalDate.now());
//        order2.setCustomerName("Elon Mush");
//        order2.setCustomerAddress("New York City");
//        orderRepository.save(order2);
//
//        OrderDetails orderDetail6 = new OrderDetails();
//        orderDetail6.setProductName("Starship");
//        orderDetail6.setQuantity(7);
//        orderDetail6.setUnitPrice(234);
//        orderDetail6.setOrders(order2);
//        orderDetailRepository.save(orderDetail6);
//
//        OrderDetails orderDetail7 = new OrderDetails();
//        orderDetail7.setProductName("Mars");
//        orderDetail7.setQuantity(11);
//        orderDetail7.setUnitPrice(125);
//        orderDetail7.setOrders(order2);
//        orderDetailRepository.save(orderDetail7);
//
//        OrderDetails orderDetail8 = new OrderDetails();
//        orderDetail8.setProductName("Moon");
//        orderDetail8.setQuantity(34);
//        orderDetail8.setUnitPrice(129);
//        orderDetail8.setOrders(order2);
//        orderDetailRepository.save(orderDetail8);
//
//        OrderDetails orderDetail9 = new OrderDetails();
//        orderDetail9.setProductName("Darius");
//        orderDetail9.setQuantity(2);
//        orderDetail9.setUnitPrice(163);
//        orderDetail9.setOrders(order2);
//        orderDetailRepository.save(orderDetail9);
//
//        OrderDetails orderDetail10 = new OrderDetails();
//        orderDetail10.setProductName("MilkyWay");
//        orderDetail10.setQuantity(5);
//        orderDetail10.setUnitPrice(400);
//        orderDetail10.setOrders(order2);
//        orderDetailRepository.save(orderDetail10);
//
//    }
}
