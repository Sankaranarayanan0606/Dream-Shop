package com.sankar.dream_shop.Service.Product;

import com.sankar.dream_shop.CustomException.ProductNotFoundException;
import com.sankar.dream_shop.Repository.CategoryRepository;
import com.sankar.dream_shop.Repository.ProductRepository;
import com.sankar.dream_shop.Request.AddProductRequest;
import com.sankar.dream_shop.Request.ProductUpdateRequest;
import com.sankar.dream_shop.model.Category;
import com.sankar.dream_shop.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements InterfaceProductService {

    private final ProductRepository productRepository;
    private final  CategoryRepository categoryRepository;
    @Autowired
    private AddProductRequest request;


//    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//    }


    @Override
    public Product addProduct(AddProductRequest request) {
        //check if the is found in the DB
        //if yes, set it as the new product \
        //if no , save it asa new category
        //the set as the new producrt category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet( () -> {
                    Category newcategory = new Category(request.getCategory().getName());
                    return  categoryRepository.save(newcategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }
//helper method for addproduct
    private Product createProduct(AddProductRequest request , Category category)
    {
        return  new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
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
    public Product updateProduct(ProductUpdateRequest request, Long product_id) {

        return productRepository.findById(product_id)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }
//updateProduct method
    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request)
    {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName((request.getCategory().getName()));
        existingProduct.setCategory(category);
        return existingProduct;
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
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findBybrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
