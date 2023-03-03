package vn.edu.stu.shop_laptop.service.impl;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.stu.shop_laptop.model.Employee;
import vn.edu.stu.shop_laptop.repository.EmployeeRepository;
import vn.edu.stu.shop_laptop.security.UserPrincipal;
import vn.edu.stu.shop_laptop.service.EmployeeLogin;

@Service
public class EmployeeLoginImpl implements EmployeeLogin{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserPrincipal findByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != employee) {
            Set<String> authorities = new HashSet<>();
            if (null != employee.getRoles()) employee.getRoles().forEach(r -> {
                authorities.add(r.getRoleKey());
                r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
            });

            userPrincipal.setUserId(employee.getId());
            userPrincipal.setUsername(employee.getUsername());
            userPrincipal.setPassword(employee.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }
}
