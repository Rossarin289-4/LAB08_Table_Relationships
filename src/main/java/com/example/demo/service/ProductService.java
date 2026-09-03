package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.model.ProductDetail;
import com.example.demo.repository.ProductRepository;
import com.example.demo.strategy.DiscountContext;
import com.example.demo.strategy.MemberDiscountStrategy;
import com.example.demo.strategy.NoDiscountStrategy;
import com.example.demo.strategy.SeasonalSaleStrategy;
import java.util.List;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //แสดงสินค้าทั้งหมด
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //ค้นหาสินค้าตาม id
    public Product getProductByID(Long id){
        return productRepository.findById(id);
    }

    //บันทึกและเพิ่มสินค้า
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    //แก้ไขสินค้า
    public Product updateProduct(Long id, Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscountType(product.getDiscountType());

        //แก้ไข ProductDetail
        if (product.getDetail() != null) {
            ProductDetail detail = existingProduct.getDetail();

            if(detail == null){
                detail = product.getDetail();
                detail.setProduct(existingProduct);
                existingProduct.setDetail(detail);
            } else{
                detail.setDescription(product.getDetail().getDescription());
                detail.setWarranty(product.getDetail().getWarranty());
                detail.setWeight(product.getDetail().getWeight());
                detail.setDismensions(product.getDetail().getDismensions());
                detail.setManufacturedCountry(product.getDetail().getManufacturedCountry());
            }
        }
        return productRepository.save(existingProduct);
    }

    //ลบสินค้า
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    //คำนวณราคาสินค้าหลังหักส่วนลด
    public double calculateFinalPrice(Product product){
        DiscountContext context;

        if("MEMBER".equalsIgnoreCase(product.getDiscountType())){
            context = new DiscountContext( new MemberDiscountStrategy());
        } else if("SEASONAL".equalsIgnoreCase(product.getDiscountType())){
            context = new DiscountContext( new SeasonalSaleStrategy());
        } else{
            context = new DiscountContext( new NoDiscountStrategy());
        }
        return context.calculateFinalPrice(product.getPrice());
    }
}
