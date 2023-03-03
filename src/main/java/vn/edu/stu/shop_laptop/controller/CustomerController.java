package vn.edu.stu.shop_laptop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.edu.stu.shop_laptop.model.Customer;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.edu.stu.shop_laptop.service.CustomerService;
import vn.edu.stu.shop_laptop.service.CustomerLogin;
import vn.edu.stu.shop_laptop.service.TokenService;
import vn.edu.stu.shop_laptop.security.UserPrincipal;
import vn.edu.stu.shop_laptop.model.Token;
import vn.edu.stu.shop_laptop.security.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private CustomerService gCustomerService;
    @Autowired
    private CustomerLogin gCustomerLogin;
    @Autowired
    private TokenService gTokenService;

    @GetMapping("/customer")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> lstCus = gCustomerService.findAllCustmer();
        if(lstCus != null) return new ResponseEntity<>(lstCus, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        Customer cus = gCustomerService.findCustomerById(id);
        if(cus != null) return new ResponseEntity<>(cus, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PostMapping("/customer/register")
    public ResponseEntity<?> createCustomer( @RequestBody Customer customer){
        try {
            Customer saveCus = gCustomerService.createCusomer(customer);
            if(saveCus == null) return new ResponseEntity<>("Username của bạn đã tồn tại!", HttpStatus.PARTIAL_CONTENT);
            return new ResponseEntity<>(saveCus, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Customer: " + e.getCause().getCause().getMessage());
        }
    }

    @PostMapping("/customer/login")
    public ResponseEntity<?> login(@RequestBody UserPrincipal user) {
        Customer customer = gCustomerService.findCustomerByUsername(user.getUsername());
        if(customer != null && customer.getActivated() == 1){
            UserPrincipal userPrincipal = gCustomerLogin.findByUsername(user.getUsername());
            if (new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword()) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
            }
            Token token = new Token();
            token.setToken(JwtUtil.generateToken(userPrincipal));
            token.setTokenExpDate(JwtUtil.generateExpirationDate());
            token.setCreatedBy(userPrincipal.getUserId());
            gTokenService.createToken(token);
            return ResponseEntity.ok(token.getToken());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản của bạn không tồn tại");
        }
       
    }
    @PutMapping("/customer/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER')")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id){
        try {
            Customer resultCus = gCustomerService.updateCusomer(customer, id);
            if(resultCus != null) return new ResponseEntity<>(resultCus, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Customer: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping("customer/{id}/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id){
        try {
            Customer saveCustomer = gCustomerService.deleteCustomer(id);
            if(saveCustomer != null) return new ResponseEntity<>(saveCustomer, HttpStatus.ACCEPTED);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Delete specified Employee: " + e.getCause().getCause().getMessage());
        }
    }
}
