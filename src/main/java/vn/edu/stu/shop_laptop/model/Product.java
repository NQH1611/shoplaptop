package vn.edu.stu.shop_laptop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_san_pham")
    @NotNull(message = "Hãy nhập tên sản phẩm")
    private String tenSanPham;
    @Column(name = "thong_so_san_pham", columnDefinition = "TEXT")
    private String thongSoSanPham;
    @Column(name = "thong_so_ky_thuat", columnDefinition = "TEXT")
    private String thongSoKyThuat;
    @Column(name = "soluong")
    private Integer soluong;
    @Column(name = "baohanh")
    private String baoHanh;
    @Column(name = "giaTien")
    private BigDecimal giaTien;
    @Column(name = "phan_tram_khuyen_mai")
    private String phanTramKhuyenMai;
    @Column(name = "khuyenmai", columnDefinition = "TEXT")
    private String khuyenMai;
    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;
    @Column(name = "url")
    @NotNull(message = "Hãy nhập vào địa chỉ bạn muốn trỏ tới sản phẩm")
    private String url;
    @Column(name = "activated")
    private int activated;
    @Column(name = "createdate")
    @CreatedDate
    private Date createDate;
    @Column(name = "updatedate")
    @LastModifiedDate
    private Date updateDate;
    @Column(name = "danhmuc")
    @NotNull(message = "Không được để trống danh mục sản phẩm")
    private int category;
    @Column(name = "hangsx")
    private String hangSX;
    @OneToMany(targetEntity = hinhAnh.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productId")
    @JsonIgnore
    private Set<hinhAnh> hinhAnhs;
    
    public Product() {
    }
    public Product(int id, @NotNull(message = "Hãy nhập tên sản phẩm") String tenSanPham, String thongSoSanPham,
            String thongSoKyThuat, Integer soluong, String baoHanh, BigDecimal giaTien, String phanTramKhuyenMai,
            String khuyenMai, String moTa,
            @NotNull(message = "Hãy nhập vào địa chỉ bạn muốn trỏ tới sản phẩm") String url, int activated,
            Date createDate, Date updateDate, @NotNull(message = "Không được để trống danh mục sản phẩm") int category,
            String hangSX, Set<hinhAnh> hinhAnhs) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.thongSoSanPham = thongSoSanPham;
        this.thongSoKyThuat = thongSoKyThuat;
        this.soluong = soluong;
        this.baoHanh = baoHanh;
        this.giaTien = giaTien;
        this.phanTramKhuyenMai = phanTramKhuyenMai;
        this.khuyenMai = khuyenMai;
        this.moTa = moTa;
        this.url = url;
        this.activated = activated;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.category = category;
        this.hangSX = hangSX;
        //this.hinhAnhs = hinhAnhs;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTenSanPham() {
        return tenSanPham;
    }
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }
    public String getThongSoSanPham() {
        return thongSoSanPham;
    }
    public void setThongSoSanPham(String thongSoSanPham) {
        this.thongSoSanPham = thongSoSanPham;
    }
    public String getThongSoKyThuat() {
        return thongSoKyThuat;
    }
    public void setThongSoKyThuat(String thongSoKyThuat) {
        this.thongSoKyThuat = thongSoKyThuat;
    }
    public Integer getSoluong() {
        return soluong;
    }
    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }
    public String getBaoHanh() {
        return baoHanh;
    }
    public void setBaoHanh(String baoHanh) {
        this.baoHanh = baoHanh;
    }
    public BigDecimal getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }
    public String getPhanTramKhuyenMai() {
        return phanTramKhuyenMai;
    }
    public void setPhanTramKhuyenMai(String phanTramKhuyenMai) {
        this.phanTramKhuyenMai = phanTramKhuyenMai;
    }
    public String getKhuyenMai() {
        return khuyenMai;
    }
    public void setKhuyenMai(String khuyenMai) {
        this.khuyenMai = khuyenMai;
    }
    public String getMoTa() {
        return moTa;
    }
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getActivated() {
        return activated;
    }
    public void setActivated(int activated) {
        this.activated = activated;
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
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public String getHangSX() {
        return hangSX;
    }
    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public Set<hinhAnh> getHinhanh() {
        return hinhAnhs;
    }
    public void setHinhanh(Set<hinhAnh> hinhAnhs) {
        this.hinhAnhs = hinhAnhs;
    }
    
}
