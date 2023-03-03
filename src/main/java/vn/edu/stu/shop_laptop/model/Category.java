package vn.edu.stu.shop_laptop.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_san_pham")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "createdate")
    @CreatedDate
    private Date createDate;
    @Column(name = "updatedate")
    @LastModifiedDate
    private Date updateDate;
    @Column(name = "activated")
    private int activated;
    public Category() {
    }
    public Category(int id, String name, String url, Date createDate, Date updateDate, int activated) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.activated = activated;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public int getActivated() {
        return activated;
    }
    public void setActivated(int activated) {
        this.activated = activated;
    }
    
}
