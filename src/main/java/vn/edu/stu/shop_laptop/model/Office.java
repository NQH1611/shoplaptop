package vn.edu.stu.shop_laptop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "province_id")
    @NotNull(message = "Hãy chọn tỉnh/thành phố của cửa hàng")
    private Integer provinceId;
    @Column(name = "district_id")
    @NotNull(message = "Hãy chọn quận/huyện của cửa hàng")
    private Integer districtId;
    @Column(name = "ward_id")
    @NotNull(message = "Hãy chọn phường/xã của cửa hàng")
    private Integer wardId;
    @Column(name = "address")
    @NotNull(message = "Hãy nhập địa chỉ của cửa hàng")
    @Size(min = 10, message = "địa chỉ có ít nhất 10 ký tự")
    private String address;
    public Office() {
    }
    public Office(Integer id, Integer provinceId, Integer districtId, Integer wardId, String address) {
        this.id = id;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.address = address;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public Integer getDistrictId() {
        return districtId;
    }
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
    public Integer getWardId() {
        return wardId;
    }
    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
}
