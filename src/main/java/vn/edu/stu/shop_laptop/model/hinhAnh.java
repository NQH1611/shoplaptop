package vn.edu.stu.shop_laptop.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hinhanh")
public class hinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "hinhanh")
    private String hinhanh;
    @ManyToOne
    @JoinColumn(name = "product_image", referencedColumnName = "id")
    @JsonIgnore
    private Product productId;
    public hinhAnh() {
    }
    public hinhAnh(int id, String hinhanh, Product product) {
        this.id = id;
        this.hinhanh = hinhanh;
        this.productId = product;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHinhanh() {
        return hinhanh;
    }
    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
    public void setProduct(Product product) {
        this.productId = product;
    }
    
}
