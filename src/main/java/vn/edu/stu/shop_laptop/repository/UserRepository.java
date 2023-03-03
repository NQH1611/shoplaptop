package vn.edu.stu.shop_laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.shop_laptop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
