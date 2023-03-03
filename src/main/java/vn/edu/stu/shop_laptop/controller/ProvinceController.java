package vn.edu.stu.shop_laptop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.shop_laptop.repository.*;
import vn.edu.stu.shop_laptop.model.*;;
@RestController
@CrossOrigin
public class ProvinceController {
    @Autowired ProvinceRepository provinceRepo;
    @Autowired DistrictRepository districtRepo;
    @Autowired WardRepository wardRepo;
    @GetMapping("/province")
    public List<Province> getAllProvince(){
        List<Province> lstProvince = new ArrayList<Province>();
        provinceRepo.findAll().forEach(lstProvince::add);
        return lstProvince;
    }
    @GetMapping("/district/{provinceid}")
    public List<District> getDistrictByProvince(@PathVariable("provinceid") int province){
        List<District> lstDistrict = new ArrayList<District>();
        districtRepo.findByProvince(province).forEach(lstDistrict::add);
        return lstDistrict;
    }

    @GetMapping("/ward/{district}")
    public List<Ward> getWardByDistrict(@PathVariable("district") int district){
        List<Ward> lstWard = new ArrayList<Ward>();
        wardRepo.findByDistrict(district).forEach(lstWard::add);
        return lstWard;
    }
}
