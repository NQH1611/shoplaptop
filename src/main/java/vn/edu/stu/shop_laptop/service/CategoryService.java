package vn.edu.stu.shop_laptop.service;
import java.util.ArrayList;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.stu.shop_laptop.repository.CategoryRepository;
import vn.edu.stu.shop_laptop.model.Category;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository gCategoryRepository;

    public List<Category> findAllCategory(){
        List<Category> lstCate = new ArrayList<>();
        gCategoryRepository.findAll().forEach(lstCate::add);
        return lstCate;
    }

    public List<Category> findAllCategoryByActivated(){
        List<Category> lstCate = new ArrayList<>();
        gCategoryRepository.findAllCategory().forEach(lstCate::add);
        return lstCate;
    }

    public Category findCategoryByUrl(String url){
        Category category = gCategoryRepository.findByUrl(url);
        return category;
    }
    public Category createCategory(Category category){
        Category result = findCategoryByUrl(category.getUrl());
        if(result == null){
            Category newCate = new Category();
            newCate.setName(category.getName());
            newCate.setUrl(category.getUrl());
            newCate.setCreateDate(new Date());
            newCate.setActivated(1);
            Category save = gCategoryRepository.save(newCate);
            return save;
        }else{
            return null;
        }
    }
    public Category updateCateGory(Category category, int id){
        Optional<Category> cate = gCategoryRepository.findById(id);
        if(cate.isPresent()){
            Category newCate = cate.get();
            newCate.setName(category.getName());
            newCate.setUrl(category.getUrl());
            newCate.setUpdateDate(new Date());
            newCate.setActivated(category.getActivated());
            Category save = gCategoryRepository.save(newCate);
            return save;
        }else{
            return null;
        }
    }

    public Category updateActivated(int id){
        Optional<Category> cate = gCategoryRepository.findById(id);
        if(cate.isPresent()){
            cate.get().setActivated(0);
            Category save = gCategoryRepository.save(cate.get());
            return save;
        }else{
            return null;
        }
    }
}
