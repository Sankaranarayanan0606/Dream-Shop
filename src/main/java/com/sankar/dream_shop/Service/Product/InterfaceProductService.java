package com.sankar.dream_shop.Service.Product;

import com.sankar.dream_shop.Request.AddProductRequest;
import com.sankar.dream_shop.Request.ProductUpdateRequest;
import com.sankar.dream_shop.model.Product;

import java.util.List;

public interface InterfaceProductService {

    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);

    void deleteProductById(Long id);


    Product updateProduct(ProductUpdateRequest request, Long product_id);

    Product getProductById(long id);

    void deleteProductById(long id);

    void updateProduct(Product product, long product_id);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> grtProductByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String brand , String name);
    Long countProductsByBrandAndName(String brand, String name);


}
