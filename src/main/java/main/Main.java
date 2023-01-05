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

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrderRepository orderRepository = context.getBean("orderRepository", OrderRepository.class);
    static OrderDetailRepository orderDetailRepository = context.getBean("orderDetailRepository", OrderDetailRepository.class);
    public static void main(String[] args) {
        System.out.println("Welcome to Tanz Industry");
        System.out.println("Develop By Thomas Tanz");

        // Add Data to database
//        createNewOrders();
//        System.out.println("Add successfully");

        // 4.1 List all order
        System.out.println("* 4.1 List all order");
        List<Orders> listorder = (List<Orders>) orderRepository.findAll();
        for (Orders orders: listorder) {
            System.out.println(listorder.toString());
        }

        // 4.2 List all orderdetail
        System.out.println("* 4.2 List all orderdetail");
        List<OrderDetails> listorderdetail = (List<OrderDetails>) orderDetailRepository.findAll();
            System.out.println(listorderdetail.toString());

        // 5. Get an order and orderdetails by order id
        System.out.println("* 5. Get an order and orderdetails by order id");
        findById(1);

        // 6. List all the orders in the current month
        System.out.println("* 6. ist all the orders in the current month");
        List<Orders> listorder1 = orderRepository.getOrderByCurMonth();
            System.out.println(listorder1.toString());

        // 7. List all orders which have total amount more than 1,000USD
        System.out.println("* 7. List all orders which have total amount more than 1,000USD");
        List<Orders> listorder2 = orderRepository.getOrderBySumPrice();
            System.out.println(listorder2.toString());

        // 8. List all orders buy Java book
        System.out.println("* 8. List all orders buy Java book");
        List<Orders> listOrder2 = orderRepository.getOrderByProductName();
        for (Orders orders: listOrder2) {
            System.out.println(listOrder2.toString());
        }


    }
    public static void findById(int id) {
        Optional<Orders> orderById = orderRepository.findById(id);
        System.out.println("Get Order By orderId: "+orderById.toString());
        List<OrderDetails> orderDetail1 = orderDetailRepository.getOrderDetailByOrderId(id);
        for (OrderDetails orderDetails: orderDetail1) {
            System.out.println(orderDetails.toString());
        }

    }

    private static void createNewOrders() {
    // Add Oder 1
        Orders order1 = new Orders();
        order1.setId(1);
        order1.setOrderDate(LocalDate.now());
        order1.setCustomerName("Thomas Tanz");
        order1.setCustomerAddress("Danang City");
        orderRepository.save(order1);

        OrderDetails orderDetail1 = new OrderDetails();
        orderDetail1.setProductName("Tess");
        orderDetail1.setQuantity(3);
        orderDetail1.setUnitPrice(110);
        orderDetail1.setOrders(order1);
        orderDetailRepository.save(orderDetail1);

        OrderDetails orderDetail2 = new OrderDetails();
        orderDetail2.setProductName("Javis");
        orderDetail2.setQuantity(2);
        orderDetail2.setUnitPrice(112);
        orderDetail2.setOrders(order1);
        orderDetailRepository.save(orderDetail2);

        OrderDetails orderDetail3 = new OrderDetails();
        orderDetail3.setProductName("Noi com dien");
        orderDetail3.setQuantity(12);
        orderDetail3.setUnitPrice(232);
        orderDetail3.setOrders(order1);
        orderDetailRepository.save(orderDetail3);

        OrderDetails orderDetail4 = new OrderDetails();
        orderDetail4.setProductName("may giat");
        orderDetail4.setQuantity(21);
        orderDetail4.setUnitPrice(123);
        orderDetail4.setOrders(order1);
        orderDetailRepository.save(orderDetail4);

        OrderDetails orderDetail5 = new OrderDetails();
        orderDetail5.setProductName("Lo vi song");
        orderDetail5.setQuantity(15);
        orderDetail5.setUnitPrice(121);
        orderDetail5.setOrders(order1);
        orderDetailRepository.save(orderDetail5);

    // Add Oder 2
        Orders order2 = new Orders();
        order2.setId(2);
        order2.setOrderDate(LocalDate.now());
        order2.setCustomerName("Elon Mush");
        order2.setCustomerAddress("New York City");
        orderRepository.save(order2);

        OrderDetails orderDetail6 = new OrderDetails();
        orderDetail6.setProductName("Starship");
        orderDetail6.setQuantity(7);
        orderDetail6.setUnitPrice(234);
        orderDetail6.setOrders(order2);
        orderDetailRepository.save(orderDetail6);

        OrderDetails orderDetail7 = new OrderDetails();
        orderDetail7.setProductName("Mars");
        orderDetail7.setQuantity(11);
        orderDetail7.setUnitPrice(125);
        orderDetail7.setOrders(order2);
        orderDetailRepository.save(orderDetail7);

        OrderDetails orderDetail8 = new OrderDetails();
        orderDetail8.setProductName("Moon");
        orderDetail8.setQuantity(34);
        orderDetail8.setUnitPrice(129);
        orderDetail8.setOrders(order2);
        orderDetailRepository.save(orderDetail8);

        OrderDetails orderDetail9 = new OrderDetails();
        orderDetail9.setProductName("Darius");
        orderDetail9.setQuantity(2);
        orderDetail9.setUnitPrice(163);
        orderDetail9.setOrders(order2);
        orderDetailRepository.save(orderDetail9);

        OrderDetails orderDetail10 = new OrderDetails();
        orderDetail10.setProductName("MilkyWay");
        orderDetail10.setQuantity(5);
        orderDetail10.setUnitPrice(400);
        orderDetail10.setOrders(order2);
        orderDetailRepository.save(orderDetail10);

    }
}