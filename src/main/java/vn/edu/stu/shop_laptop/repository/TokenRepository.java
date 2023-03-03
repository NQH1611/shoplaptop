package vn.edu.stu.shop_laptop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.shop_laptop.model.Token;


public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);
}
