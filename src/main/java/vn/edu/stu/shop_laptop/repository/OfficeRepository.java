package vn.edu.stu.shop_laptop.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.edu.stu.shop_laptop.model.Office;
public interface OfficeRepository extends JpaRepository<Office, Integer>{
    
}
