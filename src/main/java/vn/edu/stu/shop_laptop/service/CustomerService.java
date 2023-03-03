package vn.edu.stu.shop_laptop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.edu.stu.shop_laptop.model.Customer;
import vn.edu.stu.shop_laptop.model.Role;
import vn.edu.stu.shop_laptop.repository.CustomerRepository;
import vn.edu.stu.shop_laptop.repository.RoleRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository gCustomerRepository;
    @Autowired
    private RoleRepository gRoleRepository;

    public List<Customer> findAllCustmer() {
        List<Customer> lstCustomer = new ArrayList<Customer>();
        gCustomerRepository.findCustomerActivated().forEach(lstCustomer::add);
        if (lstCustomer.size() != 0)
            return lstCustomer;
        return null;
    }

    public Customer findCustomerByUsername(String username) {
        Customer cus = gCustomerRepository.findByUsername(username);
        if (cus != null)
            return cus;
        return null;
    }

    public Customer findCustomerById(int id) {
        Optional<Customer> customer = gCustomerRepository.findCustomerByIdAndActivated(id);
        if (customer.isPresent())
            return customer.get();
        return null;
    }

    public Customer createCusomer(Customer customer) {
        Set<Role> roleCustomer = new HashSet<>();
        roleCustomer.add(gRoleRepository.findByRoleKey("ROLE_CUSTOMER"));
        Customer newCus = new Customer();
        newCus.setTenKhachHang(customer.getTenKhachHang());
        newCus.setSoDienThoai(customer.getSoDienThoai());
        newCus.setEmail(customer.getEmail());
        newCus.setDiaChi(customer.getDiaChi());
        newCus.setProvinceId(customer.getProvinceId());
        newCus.setDistrictId(customer.getDistrictId());
        newCus.setWardId(customer.getWardId());
        newCus.setUsername(customer.getUsername());
        newCus.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        newCus.setActivated(1);
        newCus.setCreateData(new Date());
        newCus.setTongTienDaMua(customer.getTongTienDaMua());
        newCus.setXepLoai("Đồng");
        newCus.setRoles(roleCustomer);
        Customer resultCus = gCustomerRepository.findByUsername(customer.getUsername());
        if (resultCus != null) {
            return null;
        } else {
            return gCustomerRepository.save(newCus);
        }
    }

    public Customer updateCusomer(Customer customer, int id) {
        Optional<Customer> resultCus = gCustomerRepository.findById(id);
        if (resultCus.isPresent()) {
            Customer newCus = resultCus.get();
            newCus.setTenKhachHang(customer.getTenKhachHang());
            newCus.setSoDienThoai(customer.getSoDienThoai());
            newCus.setEmail(customer.getEmail());
            newCus.setDiaChi(customer.getDiaChi());
            newCus.setProvinceId(customer.getProvinceId());
            newCus.setDistrictId(customer.getDistrictId());
            newCus.setWardId(customer.getWardId());
            newCus.setUsername(customer.getUsername());
            newCus.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
            newCus.setActivated(1);
            newCus.setCreateData(new Date());
            newCus.setTongTienDaMua(customer.getTongTienDaMua());
            newCus.setXepLoai("Đồng");
            return gCustomerRepository.save(newCus);
        }else{
            return null;
        }

    }
    public Customer deleteCustomer(int id){
        Optional<Customer> resultCustomers = gCustomerRepository.findById(id);
        if(resultCustomers.isPresent()){
             resultCustomers.get().setActivated(0);
             return gCustomerRepository.save(resultCustomers.get());
             
        }else{
            return null;
        }
    }
}
