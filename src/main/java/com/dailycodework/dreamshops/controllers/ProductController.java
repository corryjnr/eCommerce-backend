package com.dailycodework.dreamshops.controllers;

import com.dailycodework.dreamshops.dto.ProductDto;
import com.dailycodework.dreamshops.exception.AlreadyExistsException;
import com.dailycodework.dreamshops.exception.ResourceNotFoundException;
import com.dailycodework.dreamshops.models.Product;
import com.dailycodework.dreamshops.requests.AddProductRequest;
import com.dailycodework.dreamshops.requests.UpdateProductRequest;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.services.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()){
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse("No product found!", null));
        }
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Success!", convertedProducts));
    }

    @GetMapping("/product/{productId}/id")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Success!", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product){
        try {
            Product newProduct = productService.addProduct(product);
            ProductDto productDto = productService.convertToDto(newProduct);
            return ResponseEntity.ok(new ApiResponse("Add product success!", productDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest product){
        try {
            Product updatedProduct = productService.updateProduct(product, productId);
            ProductDto productDto = productService.convertToDto(updatedProduct);
            return ResponseEntity.ok(new ApiResponse("Product update success!", productDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product delete success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/brand/{brandName}/product/{productName}")
    public ResponseEntity<ApiResponse> getProductsByBrandAndName(@PathVariable String brandName, @PathVariable String productName){
        try {
            List<Product> products = productService.getProductByBrandAndName(brandName, productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-category/{categoryName}")
    public ResponseEntity<ApiResponse> getProductsByCategoryName(@PathVariable String categoryName){
        try {
            List<Product> products = productService.getProductsByCategoryName(categoryName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-brand/{brandName}")
    public ResponseEntity<ApiResponse> getProductsByBrand(@PathVariable String brandName){
        try {
            List<Product> products = productService.getProductsByBrand(brandName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/by-name/{productName}")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String productName){
        try {
            List<Product> products = productService.getProductsByName(productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No product found!", null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success!", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/count/by-brand/{brandName}/product/{productName}")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@PathVariable String brandName, @PathVariable String productName){
        Long count = productService.countProductByBrandAndName(brandName, productName);
        return ResponseEntity.ok(new ApiResponse("Count success", count));
    }

}
