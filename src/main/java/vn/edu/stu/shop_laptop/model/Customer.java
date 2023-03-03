package vn.edu.stu.shop_laptop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_khach_hang")
    private String tenKhachHang;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Column(name = "email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @Column(name = "diachi")
    private String diaChi;
    @Column(name = "privince_id")
    private int provinceId;
    @Column(name = "district_id")
    private int districtId;
    @Column(name = "ward_id")
    private int wardId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "activated")
    private int activated;
    @Column(name = "createdate")
    @CreatedDate
    private Date createData;
    @Column(name = "updatedate")
    @LastModifiedDate
    private Date updateDate;
    @Column(name = "tong_tien_da_mua")
    private BigDecimal tongTienDaMua;
    @Column(name = "xeploai")
    private String xepLoai;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "fk_customer_role", joinColumns = {@JoinColumn(name = "customer_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
    public Customer() {
    }
    public Customer(int id, String tenKhachHang, String soDienThoai, String email, String diaChi, int provinceId,
            int districtId, int wardId, String username, String password, int activated, Date createData,
            Date updateDate, BigDecimal tongTienDaMua, String xepLoai, Set<Role> roles) {
        this.id = id;
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.username = username;
        this.password = password;
        this.activated = activated;
        this.createData = createData;
        this.updateDate = updateDate;
        this.tongTienDaMua = tongTienDaMua;
        this.xepLoai = xepLoai;
        this.roles = roles;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTenKhachHang() {
        return tenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }
    public String getSoDienThoai() {
        return soDienThoai;
    }
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getActivated() {
        return activated;
    }
    public void setActivated(int activated) {
        this.activated = activated;
    }
    public Date getCreateData() {
        return createData;
    }
    public void setCreateData(Date createData) {
        this.createData = createData;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public BigDecimal getTongTienDaMua() {
        return tongTienDaMua;
    }
    public void setTongTienDaMua(BigDecimal tongTienDaMua) {
        this.tongTienDaMua = tongTienDaMua;
    }
    public String getXepLoai() {
        return xepLoai;
    }
    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
