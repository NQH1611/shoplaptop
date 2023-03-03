package vn.edu.stu.shop_laptop.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import vn.edu.stu.shop_laptop.model.Category;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    @Query(value = "select * from category where activated = 1", nativeQuery = true)
    List<Category> findAllCategory();

    @Query(value = "select * from category where activated = 1 and url = :url", nativeQuery = true)
    Category findByUrl(@Param("url")String url);
}
