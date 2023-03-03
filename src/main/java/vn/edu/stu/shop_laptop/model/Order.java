package vn.edu.stu.shop_laptop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "orderdate")
    @CreatedDate
    private Date orderDate;
    @Column(name = "customerid")
    private int customerId;
    @LastModifiedDate
    @Column(name = "requireddate")
    private Date requiredDate;
    @LastModifiedDate
    @Column(name = "shippeddate")
    private Date shippedDate;
    @Column(name = "comment")
    private String comment;
    @Column(name = "status")
    private int status;
    @Column(name = "address")
    private String address;
    @Column(name = "province_id")
    private int provinceId;
    @Column(name = "district_id")
    private int districtId;
    @Column(name = "ward_id")
    private int wardId;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "total")
    private BigDecimal totalOrder;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OrderDetail> orderDetails;
    public Order() {
    }
    public Order(int id, Date orderDate, int customerId, Date requiredDate, Date shippedDate, String comment,
            int status, String address, int provinceId, int districtId, int wardId, String phoneNumber,
            BigDecimal totalOrder, Set<OrderDetail> orderDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.comment = comment;
        this.status = status;
        this.address = address;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.phoneNumber = phoneNumber;
        this.totalOrder = totalOrder;
        this.orderDetails = orderDetails;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public Date getRequiredDate() {
        return requiredDate;
    }
    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }
    public Date getShippedDate() {
        return shippedDate;
    }
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
    public int getDistrictId() {
        return districtId;
    }
    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
    public int getWardId() {
        return wardId;
    }
    public void setWardId(int wardId) {
        this.wardId = wardId;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public BigDecimal getTotalOrder() {
        return totalOrder;
    }
    public void setTotalOrder(BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
    }
    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    
}
