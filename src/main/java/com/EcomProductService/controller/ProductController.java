package com.EcomProductService.controller;

import com.EcomProductService.Utils.ProductNotFoundException;
import com.EcomProductService.dto.ProductListResponseDTO;
import com.EcomProductService.dto.ProductResponseDTO;
import com.EcomProductService.service.ProductService;
import com.EcomProductService.dto.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;
    @Autowired // Autowired for constructor injection is optional from Spring 4.x+ onwards
    public ProductController(@Qualifier("ProductService_DB") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        ProductListResponseDTO response =  productService.getAllProducts();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity getProductFromId(@PathVariable int id) throws ProductNotFoundException {
        /*
        ProductResponseDTO p3 = new ProductResponseDTO();
        p3.setId(1);
        p3.setTitle("Iphone 15 pro");
        p3.setCategory("Electronics");
        p3.setImage("www.google.com/images/iphone");
        p3.setDescription("Phone");
        p3.setPrice(150000);

        ProductResponseDTO p2 = new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook");
        p2.setCategory("Electronics");
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Laptop");
        p2.setPrice(250000);

        List<ProductResponseDTO> p = Arrays.asList(p3,p2);
        return ResponseEntity.ok(p);

         */
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/products/title/{title}")
    public ResponseEntity getProductFromTitle(@PathVariable String title) throws ProductNotFoundException {
        /*
        ProductResponseDTO p3 = new ProductResponseDTO();
        p3.setId(1);
        p3.setTitle("Iphone 15 pro");
        p3.setCategory("Electronics");
        p3.setImage("www.google.com/images/iphone");
        p3.setDescription("Phone");
        p3.setPrice(150000);

        ProductResponseDTO p2 = new ProductResponseDTO();
        p2.setId(2);
        p2.setTitle("Macbook");
        p2.setCategory("Electronics");
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("Laptop");
        p2.setPrice(250000);

        List<ProductResponseDTO> p = Arrays.asList(p3,p2);
        return ResponseEntity.ok(p);

         */
        ProductResponseDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO responseDTO =  productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
       boolean responseDTO =  productService.deleteProduct(id);
        return ResponseEntity.ok(responseDTO);
    }

}
