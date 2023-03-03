package vn.edu.stu.shop_laptop.controller;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import vn.edu.stu.shop_laptop.model.Category;
import vn.edu.stu.shop_laptop.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService gCategoryService;

    @GetMapping("/category/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> lstCate = gCategoryService.findAllCategory();
        if(lstCate.size() != 0) return new ResponseEntity<>(lstCate, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        
    }
    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategoryByActive(){
        List<Category> lstCate = gCategoryService.findAllCategoryByActivated();
        if(lstCate.size() != 0) return new ResponseEntity<>(lstCate, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        
    }

    @GetMapping("/category/{url}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Category> findByIdCategory(@PathVariable("url") String url){
        Category cate = gCategoryService.findCategoryByUrl(url);
        if(cate != null) return new ResponseEntity<>(cate, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
    }

    @PostMapping("/category")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        Category save = gCategoryService.createCategory(category);
        if(save != null){
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        }    
        else{
            return new ResponseEntity<>("URL is exist", HttpStatus.PARTIAL_CONTENT);
        }
    }
    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCate(@RequestBody Category category, @PathVariable("id") int id){
        Category save = gCategoryService.updateCateGory(category, id);
        if(save != null) return new ResponseEntity<>(save, HttpStatus.CREATED);
        else{
            return new ResponseEntity<>("URL is exist", HttpStatus.PARTIAL_CONTENT);
        }
    }
    @PutMapping("/category/{id}/delete")
    public ResponseEntity<?> deleteCate(@PathVariable("id") int id){
        Category save = gCategoryService.updateActivated(id);
        if(save != null) return new ResponseEntity<>(save, HttpStatus.CREATED);
        else{
            return new ResponseEntity<>("URL is exist", HttpStatus.PARTIAL_CONTENT);
        }
    }
}
