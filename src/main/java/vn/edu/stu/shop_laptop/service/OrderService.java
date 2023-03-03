package vn.edu.stu.shop_laptop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.stu.shop_laptop.model.Order;
import vn.edu.stu.shop_laptop.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository gOrderRepository;


    public List<Order> getAllOrder(){
        List<Order> lstOrder = new ArrayList<>();
        gOrderRepository.findAll().forEach(lstOrder::add);
        return lstOrder;
    }
    public Order getOrderById (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        if(order.isPresent()) return order.get();
        return null;
    }
    public List<Order> getOrderByCustomerId (int id){
        List<Order> lstOrder = new ArrayList<>();
        gOrderRepository.findByCustomerId(id).forEach(lstOrder::add);
        return lstOrder;
    }
    public Order updateRequiredDate (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        order.get().setRequiredDate(new Date());
        return gOrderRepository.save(order.get());
    }
    public Order updateShippedDate (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        order.get().setShippedDate(new Date());
        return gOrderRepository.save(order.get());
    }
    public Order createOrder(Order order){
        Order newOrder = new Order();
        newOrder.setOrderDate(new Date());
        newOrder.setCustomerId(order.getCustomerId());
        newOrder.setComment(order.getComment());
        newOrder.setStatus(1);
        newOrder.setAddress(order.getAddress());
        newOrder.setProvinceId(order.getProvinceId());
        newOrder.setDistrictId(order.getDistrictId());
        newOrder.setWardId(order.getWardId());
        newOrder.setPhoneNumber(order.getPhoneNumber());
        newOrder.setTotalOrder(order.getTotalOrder());
        Order saveOrder = gOrderRepository.save(newOrder);
        return saveOrder;
    }
    public Order updateStatus2 (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        order.get().setStatus(2);
        return gOrderRepository.save(order.get());
    }
    public Order updateStatus3 (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        order.get().setStatus(3);
        return gOrderRepository.save(order.get());
    }
    public Order updateStatus4 (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        order.get().setStatus(4);
        return gOrderRepository.save(order.get());
    }
    public Order updateStatus5 (int id){
        Optional<Order> order = gOrderRepository.findById(id);
        if(order.get().getStatus() == 1){
            order.get().setStatus(5);
            return gOrderRepository.save(order.get());
        }else{
            return null;
        }
    }
}
