package vn.edu.stu.shop_laptop.service;

import java.util.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.stu.shop_laptop.model.Role;
import vn.edu.stu.shop_laptop.model.Employee;
import vn.edu.stu.shop_laptop.repository.EmployeeRepository;
import vn.edu.stu.shop_laptop.repository.RoleRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository gRoleRepository;

    
    public List<Employee> getAllEmployee(){
        List<Employee> lstEmployee = new ArrayList<Employee>();
        employeeRepository.findAll().forEach(lstEmployee::add);
        return lstEmployee;
    }
    public Employee getEmployeeByUsername(String username){
        Employee lstEmployee = employeeRepository.findByUsername(username);
        return lstEmployee;
    }
    public Employee getEmployeeById(int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.get();
    }
    public Employee createEmployee(Employee employee){
            Set<Role> roleSeller = new HashSet<>();
            roleSeller.add(gRoleRepository.findByRoleKey("ROLE_SELLER"));
            Employee newEmployee = new Employee();
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setBirthday(employee.getBirthday());
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setPhoneNumber(employee.getPhoneNumber());
            newEmployee.setAddress(employee.getAddress());
            newEmployee.setProvinceId(employee.getProvinceId());
            newEmployee.setDistrictId(employee.getDistrictId());
            newEmployee.setOfficeId(employee.getOfficeId());
            newEmployee.setWardId(employee.getWardId());
            newEmployee.setUsername(employee.getUsername());
            newEmployee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
            newEmployee.setRoles(roleSeller);
            newEmployee.setCreateDate(new Date());
            newEmployee.setUserLevel(employee.getUserLevel());
            newEmployee.setActivated(1);
            Employee testUsername = employeeRepository.findByUsername(newEmployee.getUsername());
            if(testUsername!=null) return null;
            return employeeRepository.save(newEmployee);
    }
    public Employee updateEmployee(Employee employee, int id){
        Optional<Employee> resultEmployees = employeeRepository.findById(id);
        if(resultEmployees.isPresent()){
            Employee newEmployee = resultEmployees.get();
            newEmployee.setLastName(employee.getLastName());
            newEmployee.setFirstName(employee.getFirstName());
            newEmployee.setBirthday(employee.getBirthday());
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setPhoneNumber(employee.getPhoneNumber());
            newEmployee.setAddress(employee.getAddress());
            newEmployee.setProvinceId(employee.getProvinceId());
            newEmployee.setDistrictId(employee.getDistrictId());
            newEmployee.setOfficeId(employee.getOfficeId());
            newEmployee.setWardId(employee.getWardId());
            newEmployee.setUsername(employee.getUsername());
            newEmployee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
            newEmployee.setUpdateDate(new Date());
            newEmployee.setUserLevel(employee.getUserLevel());
            String roleKey = "";
                if(employee.getUserLevel() == 1){
                    roleKey = "ROLE_ADMIN";
                } else if(employee.getUserLevel() == 2){
                    roleKey = "ROLE_SELLER";
                }else if(employee.getUserLevel() == 3){
                    roleKey = "ROLE_ACCOUNTANT";
                }else{
                    roleKey = "ROLE_CUSTOMER";
                }
            Set<Role> role = new HashSet<>();
            role.add(gRoleRepository.findByRoleKey(roleKey));
            newEmployee.setRoles(role);
            return employeeRepository.save(newEmployee);
        }else{
            return null;
        }
    }
    public Employee deleteEmployee(int id, int activated){
        Optional<Employee> resultEmployees = employeeRepository.findById(id);
        if(resultEmployees.isPresent()){
             resultEmployees.get().setActivated(activated);
             return employeeRepository.save(resultEmployees.get());
             
        }else{
            return null;
        }
    }
}