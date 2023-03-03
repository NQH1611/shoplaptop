package vn.edu.stu.shop_laptop.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.stu.shop_laptop.repository.OfficeRepository;
import vn.edu.stu.shop_laptop.model.Office;

@Service
public class OfficeService {
    @Autowired
    private OfficeRepository gOfficeRepository;

    public List<Office> findAllOffice(){
        List<Office> lstOffice = new ArrayList<>();
        gOfficeRepository.findAll().forEach(lstOffice::add);
        return lstOffice;
    }
    public Office getOfficeById(int id){
        Optional<Office> office = gOfficeRepository.findById(id);
        if(office.isPresent()) return office.get();
        return null;
    }

    public Office createOffice(Office office){
        Office newOffice = new Office();
        newOffice.setProvinceId(office.getProvinceId());
        newOffice.setDistrictId(office.getDistrictId());
        newOffice.setWardId(office.getWardId());
        newOffice.setAddress(office.getAddress());
        Office saveOffice = gOfficeRepository.save(newOffice);
        return saveOffice;
    }
    public Office updateOffice(Office office, int id){
        Optional<Office> resultOffice = gOfficeRepository.findById(id);
        if(resultOffice.isPresent()){
            Office newOffice = resultOffice.get();
            newOffice.setProvinceId(office.getProvinceId());
            newOffice.setDistrictId(office.getDistrictId());
            newOffice.setWardId(office.getWardId());
            newOffice.setAddress(office.getAddress());
            Office saveOffice = gOfficeRepository.save(newOffice);
            return saveOffice;
        }else{
            return null;
        }
    }
     
}
