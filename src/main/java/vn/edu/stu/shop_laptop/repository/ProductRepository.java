package vn.edu.stu.shop_laptop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.edu.stu.shop_laptop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query(value = "select * from product where url = :url and activated = 1", nativeQuery = true)
    Product findProductByUrl(@Param("url") String url);
    @Query(value = "SELECT * FROM product WHERE activated = 1", nativeQuery = true)
    List<Product> findAllProduct();

    @Query(value = "select * from product where id = :id and activated = 1", nativeQuery = true)
    Product findProductById(@Param("id") int id);
}
