package vn.edu.stu.shop_laptop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.stu.shop_laptop.model.JsonRequest;
import vn.edu.stu.shop_laptop.model.hinhAnh;
import vn.edu.stu.shop_laptop.repository.HinhAnhRepository;
import vn.edu.stu.shop_laptop.repository.ProductRepository;
import vn.edu.stu.shop_laptop.security.FileUploadUtil;

@RestController
@CrossOrigin
public class JsonRequestController {
    @Autowired
    private HinhAnhRepository gAnhRepository;
    @Autowired
    private ProductRepository gProductRepository;
    @PostMapping("/uploadFile")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("productId") int productid){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        hinhAnh anh = new hinhAnh();
        anh.setHinhanh(fileName);
        anh.setProduct(gProductRepository.findProductById(productid));
        hinhAnh save = gAnhRepository.save(anh);
        String uploadDir = "/image";
        try {
            FileUploadUtil.saveFile(uploadDir, fileName, file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("file received successfully");
    }
    @PostMapping("/uploadJson")
    public ResponseEntity<String> handleJsonInput(@RequestBody JsonRequest json){
        return ResponseEntity.ok().body(json.getId()+json.getName());
    }
}
