package vn.edu.stu.shop_laptop.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.shop_laptop.model.Order;
import vn.edu.stu.shop_laptop.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class OrderController {
    @Autowired
    private OrderService gOrderService;

    @GetMapping("/order")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> lstOrder = gOrderService.getAllOrder();
        if(lstOrder.size() > 0) return new ResponseEntity<>(lstOrder, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<Order> getOrderByID(@PathVariable("id") int id){
        Order order = gOrderService.getOrderById(id);
        if(order != null) return new ResponseEntity<>(order, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/order/{id}/customer")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    public ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable("id") int id){
        List<Order> lstOrder = gOrderService.getOrderByCustomerId(id);
        return new ResponseEntity<>(lstOrder, HttpStatus.OK);
    }

    @PostMapping("/order")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER','ROLE_SELLER')")
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        try {
            Order saveOrder = gOrderService.createOrder(order);
            return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Order: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/order/{id}/update/requireddate")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    public ResponseEntity<Order> updateRequired(@PathVariable("id") int id){
        Order order = gOrderService.updateRequiredDate(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/order/{id}/update/shippedate")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    public ResponseEntity<Order> updateShipped(@PathVariable("id") int id){
        Order order = gOrderService.updateShippedDate(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/order/{id}/update/statusaccept")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    public ResponseEntity<Order> updateStatusAccept(@PathVariable("id") int id){
        Order order = gOrderService.updateStatus2(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/order/{id}/update/statusshiped")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    public ResponseEntity<Order> updateStatuShip(@PathVariable("id") int id){
        Order order = gOrderService.updateStatus3(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/order/{id}/update/statusdone")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    public ResponseEntity<Order> updateStatusDone(@PathVariable("id") int id){
        Order order = gOrderService.updateStatus4(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PutMapping("/order/{id}/update/statuscancle")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER', 'ROLE_CUSTOMER')")
    public ResponseEntity<Order> updateStatusCancle(@PathVariable("id") int id){
        Order order = gOrderService.updateStatus5(id);
        if(order == null){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }else{
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }
}
