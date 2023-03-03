package vn.edu.stu.shop_laptop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.shop_laptop.model.Office;
import vn.edu.stu.shop_laptop.service.OfficeService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class OfficeController {
    @Autowired
    private OfficeService gOfficeService;
    @GetMapping("/office")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Office>> getAllOffice(){
        List<Office> lstOffice = new ArrayList<Office>();
        lstOffice = gOfficeService.findAllOffice();
        if(lstOffice.size() != 0) return new ResponseEntity<>(lstOffice, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/office/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Office> getOfficeById(@PathVariable("id")  int id){
        Office resuleOffice = gOfficeService.getOfficeById(id);
        if(resuleOffice != null) return new ResponseEntity<>(resuleOffice, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        
    }

    @PostMapping("/office")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createOffice(@Valid @RequestBody Office office){
        try {
            Office newOffice = gOfficeService.createOffice(office);
            if(newOffice != null) return new ResponseEntity<>(newOffice, HttpStatus.CREATED);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Office: " + e.getCause().getCause().getMessage());
        }
    }
    @PutMapping("/office/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateOffice(@RequestBody Office office, @PathVariable("id") int id){
        try {
            Office newOffice = gOfficeService.updateOffice(office, id);
            if(newOffice != null) return new ResponseEntity<>(newOffice, HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity()
                    .body("Failed to Create specified Office: " + e.getCause().getCause().getMessage());
        }
    }
}
