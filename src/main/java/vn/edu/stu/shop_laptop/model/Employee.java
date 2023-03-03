package vn.edu.stu.shop_laptop.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "lastname")
    @NotNull(message = "Last Name không được rỗng")
    private String lastName;
    @Column(name = "firstname")
    @NotNull(message = "First Name không được rỗng")
    private String firstName;
    @Column(name = "birthday")
    @NotNull(message = "Ngày sinh không được rỗng")
    private String birthday;
    @Column(name = "email")
    @Email(message = "Email không hợp lệ")
    private String email;
    @NotEmpty(message = "Hãy nhập số điện thoại")
    @Size(min = 10, max = 10, message = "Số điện thoại phải có 10 số")
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "address")
    @NotNull(message = "Hãy nhập địa chỉ của nhân viên")
    @Size(min = 10, message = "địa chỉ có ít nhất 10 ký tự")
    private String address;
    @Column(name = "province_id")
    @NotNull(message = "Hãy chọn tỉnh/thành phố của nhân viên đang sinh sống")
    private Integer provinceId;
    @Column(name = "district_id")
    @NotNull(message = "Hãy chọn quận/huyện của nhân viên đang sinh sống")
    private Integer districtId;
    @Column(name = "ward_id")
    @NotNull(message = "Hãy chọn phường/xã phố của nhân viên đang sinh sống")
    private Integer wardId;
    @Column(name = "office_id")
    @NotNull(message = "Hãy chọn cửa hàng nhân viên làm")
    private Integer officeId;
    @Column(name = "username")
    @NotBlank(message = "Hãy nhập username")
    @Size(min = 5, message = "Có ít nhất 5 ký tự")
    private String username;
    @Column(name = "password")
    @NotBlank(message = "Hãy nhập password")
    private String password;
    @Column(name = "createdate")
    @CreatedDate
    private Date createDate;
    @Column(name = "updatedate")
    @LastModifiedDate
    private Date updateDate;
    @Column(name = "userlevel")
    private int userLevel;
    @Column(name = "activated")
    private int activated;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "fk_employees_role", joinColumns = {@JoinColumn(name = "employees_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
    public Employee() {
    }
    
    public Employee(int id, @NotNull(message = "Last Name không được rỗng") String lastName,
            @NotNull(message = "First Name không được rỗng") String firstName,
            @NotNull(message = "Ngày sinh không được rỗng") String birthday,
            @Email(message = "Email không hợp lệ") String email,
            @NotEmpty(message = "Hãy nhập số điện thoại") @Size(min = 10, max = 10, message = "Số điện thoại phải có 10 số") String phoneNumber,
            @NotNull(message = "Hãy nhập địa chỉ của nhân viên") @Size(min = 10, message = "địa chỉ có ít nhất 10 ký tự") String address,
            @NotBlank(message = "Hãy chọn tỉnh/thành phố của nhân viên đang sinh sống") Integer provinceId,
            @NotBlank(message = "Hãy chọn quận/huyện của nhân viên đang sinh sống") Integer districtId,
            @NotBlank(message = "Hãy chọn phường/xã phố của nhân viên đang sinh sống") Integer wardId,
            @NotBlank(message = "Hãy chọn cửa hàng nhân viên làm") Integer officeId,
            @NotBlank(message = "Hãy nhập username") @Size(min = 5, message = "Có ít nhất 5 ký tự") String username,
            @NotBlank(message = "Hãy nhập password") String password, Date createDate, Date updateDate, int userLevel,
            Set<Role> roles, int activated) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.officeId = officeId;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userLevel = userLevel;
        this.roles = roles;
        this.activated = activated;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public Integer getOfficeId() {
        return officeId;
    }
    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
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
    public int getUserLevel() {
        return userLevel;
    }
    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }
    
    
}
