package vn.edu.stu.shop_laptop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.shop_laptop.model.User;
import vn.edu.stu.shop_laptop.repository.UserRepository;


/**
 * @author hieuha
 *
 */
@RestController
@RequestMapping("/")
public class WithoutAuthorizeController {
	@Autowired
	private UserRepository userRepository;
	
    /**
     * Test trường hợp khôngcheck quyền Authorize lấy là danh sách user
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<List<Object>> getUsers() {
        return new ResponseEntity(userRepository.findAll(),HttpStatus.OK);
    }
    /**
     * Test trường hợp khôngcheck quyền Authorize
     * Tạo mới user
     * @param user
     * @return
     */
    @PostMapping("/users/createAdmin")
    public ResponseEntity<Object> create(@RequestBody User user) {
    	user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    	return new ResponseEntity(userRepository.save(user),HttpStatus.CREATED);
    }
    
    /**
     * Test có kiểm tra quyền.
     * @param user
     * @return
     */
    // @PostMapping("/users/create")
    // @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    // public User register(@RequestBody User user) {
    //     user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

    //     return userService.createUser(user);
    // }
}
