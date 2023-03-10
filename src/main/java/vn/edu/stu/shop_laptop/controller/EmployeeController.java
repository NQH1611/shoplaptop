package vn.edu.stu.shop_laptop.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.shop_laptop.model.Employee;
import vn.edu.stu.shop_laptop.model.Token;
import vn.edu.stu.shop_laptop.repository.EmployeeRepository;
import vn.edu.stu.shop_laptop.security.JwtUtil;
import vn.edu.stu.shop_laptop.security.UserPrincipal;
import vn.edu.stu.shop_laptop.service.EmployeeLogin;
import vn.edu.stu.shop_laptop.service.EmployeeService;
import vn.edu.stu.shop_laptop.service.TokenService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    EmployeeRepository gEmployeeRepository;
    @Autowired
    private EmployeeService gEmployeeService;

    @Autowired
    private EmployeeLogin gEmployeeLogin;

    @Autowired
    private TokenService gTokenService;
// get all list employee
    @GetMapping("/employees")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Employee>> getEmployee(){
        List<Employee> lstEmployee = gEmployeeService.getAllEmployee();
        if(lstEmployee.size() != 0) return new ResponseEntity<>(lstEmployee, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
// get employee by id
    @GetMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
        Employee employee = gEmployeeService.getEmployeeById(id);
        if(employee != null) return new ResponseEntity<>(employee, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    // find employee by username
    @GetMapping("/username/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable("username") String username){
        Employee employee = gEmployeeService.getEmployeeByUsername(username);
        if(employee != null) return new ResponseEntity<>(employee, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    //login (????ng nh???p)
    @PostMapping("/loginadmin")
    public ResponseEntity<?> login(@RequestBody UserPrincipal user) {
        Employee employee = gEmployeeService.getEmployeeByUsername(user.getUsername());
        if(employee != null && employee.getActivated() == 1){
            UserPrincipal userPrincipal = gEmployeeLogin.findByUsername(user.getUsername());
            if (new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword()) == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("t??i kho???n ho???c m???t kh???u kh??ng ch??nh x??c");
            }
            Token token = new Token();
            token.setToken(JwtUtil.generateToken(userPrincipal));
            token.setTokenExpDate(JwtUtil.generateExpirationDate());
            token.setCreatedBy(userPrincipal.getUserId());
            gTokenService.createToken(token);
            return ResponseEntity.ok(token.getToken());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("t??i kho???n c???a b???n kh??ng t???n t???i");
        }
       
    }
    // register(????ng k??)
    @PostMapping("/register")
    public ResponseEntity<?> createEmployee( @RequestBody @Valid Employee employee){
        try {
            Employee saveEmployee = gEmployeeService.createEmployee(employee);
            if(saveEmployee == null) return new ResponseEntity<>("Username c???a b???n ???? t???n t???i!", HttpStatus.PARTIAL_CONTENT);
            return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Employee: " + e.getCause().getCause().getMessage());
        }
    }
    // update employee by id (ch???nh s???a th??ng tin c???a employee b???ng id)
    @PutMapping("/employee/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateEmployee( @RequestBody @Valid Employee employee, @PathVariable("id") int id){
        try {
            Employee saveEmployee = gEmployeeService.updateEmployee(employee, id);
            if(saveEmployee != null) return new ResponseEntity<>(saveEmployee, HttpStatus.ACCEPTED);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Update specified Employee: " + e.getCause().getCause().getMessage());
        }
    }
    // tr??n giao di???n s??? l?? ch???c n??ng x??a employee
    // th???c ch???t ch??? v?? hi???u h??a t??i kho???n b???ng thu???c t??nh activated
    @PutMapping("employee/{id}/delete/{activated}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id, @PathVariable("activated") int activated){
        try {
            Employee saveEmployee = gEmployeeService.deleteEmployee(id, activated);
            if(saveEmployee != null) return new ResponseEntity<>(saveEmployee, HttpStatus.ACCEPTED);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Delete specified Employee: " + e.getCause().getCause().getMessage());
        }
    }
}
