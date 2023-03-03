package vn.edu.stu.shop_laptop.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.stu.shop_laptop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @Query(value = "SELECT * FROM customer WHERE username = :username and activated = 1", nativeQuery = true)
    Customer findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM customer WHERE activated = 1", nativeQuery = true)
    List<Customer> findCustomerActivated();

    @Query(value = "SELECT * FROM customer WHERE id = :id and activated = 1", nativeQuery = true)
    Optional<Customer> findCustomerByIdAndActivated(@Param("id") int id);

}
