package vn.edu.stu.shop_laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.shop_laptop.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByUsername(String username);
}
