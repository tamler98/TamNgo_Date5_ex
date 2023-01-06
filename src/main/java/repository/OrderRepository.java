package repository;

import entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {
    @Query(value = "select * from orders where month(orderDate) = month(CURDATE())", nativeQuery = true)
    List<Orders> getOrderByCurMonth();

    @Query(value = "select * from orders as o\n" +
            "inner join orderdetails as oo\n" +
            "on o.id = oo.orderId\n" +
            "where oo.productName = ?1", nativeQuery = true)
    List<Orders> getOrderByProductName(String productName);

    @Query(value = "select * from orders as o\n" +
            "inner join orderdetails as od\n" +
            "on o.id = od.orderId\n" +
            "group by o.id\n" +
            "having SUM(unitPrice) > 1000", nativeQuery = true)
    List<Orders> getOrderBySumPrice();

}
