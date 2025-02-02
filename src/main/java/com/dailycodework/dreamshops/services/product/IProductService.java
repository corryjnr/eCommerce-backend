package com.dailycodework.dreamshops.services.product;

import com.dailycodework.dreamshops.dto.ProductDto;
import com.dailycodework.dreamshops.models.Product;
import com.dailycodework.dreamshops.requests.AddProductRequest;
import com.dailycodework.dreamshops.requests.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryName(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategoryNameAndBrand(String category, String brand);
    List<Product> getProductByBrandAndName(String brand, String name);
    Long countProductByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
