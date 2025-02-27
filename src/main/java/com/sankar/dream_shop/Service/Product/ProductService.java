package com.sankar.dream_shop.Service.Product;

import com.sankar.dream_shop.CustomException.ProductNotFoundException;
import com.sankar.dream_shop.Repository.ProductRepository;
import com.sankar.dream_shop.model.Category;
import com.sankar.dream_shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class ProductService implements InterfaceProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id) {

        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                () -> {throw new ProductNotFoundException("Product Not Found!"); });
    }

    @Override
    public void updateProduct(Product product, Long product_id) {

    }

    @Override
    public Product getProductById(long id) {
        return null;
    }

    @Override
    public void deleteProductById(long id) {

    }

    @Override
    public void updateProduct(Product product, long product_id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> grtProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return List.of();
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0L;
    }
}
