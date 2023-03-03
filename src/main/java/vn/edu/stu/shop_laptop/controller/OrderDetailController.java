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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.shop_laptop.model.OrderDetail;
import vn.edu.stu.shop_laptop.service.OrderDetailService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class OrderDetailController {
    @Autowired
    private OrderDetailService gOrderDetailService;
    
    @GetMapping("/orderdetail")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetail(){
        List<OrderDetail> lstOrderDetail = gOrderDetailService.findAllOrderDetails();
        return new ResponseEntity<>(lstOrderDetail, HttpStatus.OK);
    }
    @GetMapping("/orderdetail/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable("id") int id){
        OrderDetail lstOrderDetail = gOrderDetailService.findOrderDetailById(id);
        return new ResponseEntity<>(lstOrderDetail, HttpStatus.OK);
    }
    @GetMapping("/orderdetail/{id}/order")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER, ROLE_CUSTOMER')")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByOrderId(@PathVariable("id") int id){
        List<OrderDetail> lstOrderDetail = gOrderDetailService.findOrderDetailByOrderId(id);
        return new ResponseEntity<>(lstOrderDetail, HttpStatus.OK);
    }

    @PostMapping("/orderdetail")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_SELLER')")
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderDetail orderDetail){
        try {
            OrderDetail saveOrderDetail = gOrderDetailService.createOrderDetail(orderDetail);
        return new ResponseEntity<>(saveOrderDetail, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified OrderDetail: " + e.getCause().getCause().getMessage());
        }
    }
}
