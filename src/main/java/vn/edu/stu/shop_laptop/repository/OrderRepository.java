package vn.edu.stu.shop_laptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.shop_laptop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    List<Order> findByCustomerId(int customerid);
}
