package com.example.projectz.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @GetMapping("/products/{id}")
    public void getProductById(@PathVariable("id") Integer id) { //path variable way to get params

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
