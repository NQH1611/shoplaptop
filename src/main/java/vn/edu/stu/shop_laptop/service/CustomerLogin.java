package vn.edu.stu.shop_laptop.service;
import vn.edu.stu.shop_laptop.security.UserPrincipal;
public interface CustomerLogin {
    UserPrincipal findByUsername(String username);
    
}
