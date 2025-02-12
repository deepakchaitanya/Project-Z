package com.example.projectz.Controller;


import com.example.projectz.Model.Product;
import com.example.projectz.Service.FakeStoreProductService;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    private FakeStoreProductService service;

    // DEPENDENCY INJECTION (CONSTRUCTOR) IS BEING USED.
    public ProductController(FakeStoreProductService inputService) {
        this.service = inputService; //giving value to the current instance of the private class
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Integer id) { //path variable way to get params
        //validations
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        return service.getProductById(id);
    }


    @PostMapping("/products")
    public void createProduct(){

    }


    @GetMapping("/products")
    public void getallProducts(){

    }


    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable("id") Integer id){

    }


    @DeleteMapping("/products/{id}")
    public void deleteProductById(){

    }

}
