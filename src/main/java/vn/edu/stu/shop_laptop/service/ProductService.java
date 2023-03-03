package vn.edu.stu.shop_laptop.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.edu.stu.shop_laptop.model.Product;
import vn.edu.stu.shop_laptop.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository gProductRepository;

    public List<Product> findAllProduct() {
        List<Product> lstProduct = new ArrayList<Product>();
        gProductRepository.findAllProduct().forEach(lstProduct::add);
        if (lstProduct.size() != 0)
            return lstProduct;
        return null;
    }

    public Product findProductById(int id) {
        Product resultProduct = gProductRepository.findProductById(id);
        if (resultProduct != null) return resultProduct;
        return null;
    }

    public Product findProductByUrl(String url) {
        Product resultProduct = gProductRepository.findProductByUrl(url);
        if (resultProduct != null)
            return resultProduct;
        return null;
    }

    public Product taoSanPham(Product product){
        Product newProduct = new Product();
        newProduct.setTenSanPham(product.getTenSanPham());
        newProduct.setThongSoSanPham(product.getThongSoSanPham());
        newProduct.setThongSoKyThuat(product.getThongSoKyThuat());
        newProduct.setSoluong(product.getSoluong());
        newProduct.setGiaTien(product.getGiaTien());
        newProduct.setBaoHanh(product.getBaoHanh());
        newProduct.setKhuyenMai(product.getKhuyenMai());
        newProduct.setMoTa(product.getMoTa());
        newProduct.setUrl(product.getUrl());
        newProduct.setPhanTramKhuyenMai(product.getPhanTramKhuyenMai());
        newProduct.setActivated(product.getActivated());
        newProduct.setCategory(product.getCategory());
        newProduct.setCreateDate(new Date());
        newProduct.setHangSX(product.getHangSX());
        Product saveProduct = gProductRepository.save(newProduct);
        return saveProduct;
    }

    public Product updateProduct(Product product, String url){
        Product resultProduct = gProductRepository.findProductByUrl(url);
        if(resultProduct != null){
            Product newProduct = resultProduct;
            newProduct.setTenSanPham(product.getTenSanPham());
            newProduct.setThongSoSanPham(product.getThongSoSanPham());
            newProduct.setThongSoKyThuat(product.getThongSoKyThuat());
            newProduct.setSoluong(product.getSoluong());
            newProduct.setGiaTien(product.getGiaTien());
            newProduct.setBaoHanh(product.getBaoHanh());
            newProduct.setKhuyenMai(product.getKhuyenMai());
            newProduct.setMoTa(product.getMoTa());
            newProduct.setUrl(product.getUrl());
            newProduct.setPhanTramKhuyenMai(product.getPhanTramKhuyenMai());
            newProduct.setActivated(1);
            newProduct.setCategory(product.getCategory());
            newProduct.setUpdateDate(new Date());
            newProduct.setHangSX(product.getHangSX());
            Product saveProduct = gProductRepository.save(newProduct);
            return saveProduct;
        } else{
            return null;
        }
    }
    public Product deleteProduct(String url){
        Product product = gProductRepository.findProductByUrl(url);
        if(product!=null){
            product.setActivated(0);
            return gProductRepository.save(product);
        }
        return null;
    }
}
