package com.example.projectz.Controller;


import com.example.projectz.DTO.ProductRequestDTO;
import com.example.projectz.Exception.ProductNotFoundException;
import com.example.projectz.Model.Product;
import com.example.projectz.Service.FakeStoreProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {

    private FakeStoreProductService service;

    // DEPENDENCY INJECTION (CONSTRUCTOR) IS BEING USED.
    public ProductController(FakeStoreProductService inputService) {
        this.service = inputService; //giving value to the current instance of the private class
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        //validations
        if(id <= 0){
            throw new IllegalArgumentException("Invalid product id: "+ id);
        }

        //fetch product
        Product product = service.getProductById(id); // service = new SelfProductService()

        if(product == null){
            throw new ProductNotFoundException("Product with ID "+ id + "not found");
        }
        return ResponseEntity.ok(product);
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = service.getAllProducts();
        //validations
        if(products == null){
            throw new IllegalArgumentException("Products cannot be null");
        }
        //Calling Service layer

        return ResponseEntity.ok(products); //httpStatus code is 200.
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO request) {
        //Validations
        if (request.getDescription() == null){
            throw new IllegalArgumentException("Description cannot be null");
        }
        if(request.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        Product product = service.createProduct(request.getTitle(), request.getImageURL(), request.getCategory().getTitle(), request.getDescription());

        return ResponseEntity.ok(product);
    }


    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable("id") Integer id){

    }




    @DeleteMapping("/products/{id}")
    public void deleteProductById(){

    }

}
