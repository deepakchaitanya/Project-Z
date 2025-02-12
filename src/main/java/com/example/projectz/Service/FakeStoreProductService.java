package com.example.projectz.Service;

import com.example.projectz.DTO.FakeStoreResponseDTO;
import com.example.projectz.Model.Category;
import com.example.projectz.Model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//we're telling spring to create an object for FakeStoreProductService class also by giving below annotation.
@Service
public class FakeStoreProductService {

    //create object restTemplate to make HTTP call...
    private RestTemplate restTemplate;

    // DEPENDENCY INJECTION (CONSTRUCTOR) in the FakeStoreProductService
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Product getProductById(Integer id) {
        Product product = new Product();
        //Steps
        // 1. Call Fakestore API  -->  Use RestTemplate
        // Retrive product details from Fake Store API by product ID
        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class); //

        //2. Get the Response
        // It retrieves the actual response object (FakeStoreResponseDTO) from
        // ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse.
        FakeStoreResponseDTO response = fakeStoreResponse.getBody();
        //Validations
        if(response == null) {
            throw new IllegalArgumentException("FakeStore Product not found");
        }

        //3. Convert the response to product model
        product = convertFakeStoreResponseToProduct(response);

        //4.  Create function to return the product.
        return product;
    }


    //This function will return the product
    private Product convertFakeStoreResponseToProduct(FakeStoreResponseDTO response) {
        Product product = new Product();
        Category category = new Category();
        category.setTitle(response.getCategory());


        product.setId(response.getId());
        product.setCategory(category);
        product.setDescription(response.getDescription());
        product.setImageURL(response.getImage());
        product.setTitle(response.getTitle());

        return product;
    }
}
