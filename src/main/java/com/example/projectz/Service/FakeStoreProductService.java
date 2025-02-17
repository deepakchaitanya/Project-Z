package com.example.projectz.Service;

import com.example.projectz.DTO.FakeStoreResponseDTO;
import com.example.projectz.Model.Category;
import com.example.projectz.Model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


//we're telling spring to also create an object for FakeStoreProductService class by giving below annotation.
@Service
public class FakeStoreProductService {

    //create object of library restTemplate to make HTTP call...
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
        ResponseEntity<FakeStoreResponseDTO> FakeStoreResponse =
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class); //

        //2. Get the Response
        // It retrieves the actual response object (FakeStoreResponseDTO) from
        // ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse.
        FakeStoreResponseDTO response = FakeStoreResponse.getBody();
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

    public List<Product> getAllProducts() {
        List<Product> response = new ArrayList<>();

        //1. Retrive all products from FakeStore API. IF YOU WANT TO PASS ANY OBJECT THEN WE SHOULD USE ARRAY NOT LIST.
        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreProducts = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreResponseDTO[].class);

        System.out.println("Status code: "+ fakeStoreProducts.getStatusCode());
        //2. Iterate through each FakeStoreResponseDTO object in the fakeStoreProducts collection.
        for(FakeStoreResponseDTO  fakeStoreDTO : fakeStoreProducts.getBody()) {
            response.add(convertFakeStoreResponseToProduct(fakeStoreDTO));

        }
        return response;
    }


    //Creating a Product in fakestore
    public Product createProduct(String category, String description, String image, String title) {
        Product response = new Product();

        FakeStoreResponseDTO requestBody = new FakeStoreResponseDTO();
        requestBody.setCategory(category);
        requestBody.setDescription(description);
        requestBody.setImage(image);
        requestBody.setTitle(title);

        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse = restTemplate.postForEntity("https://fakestoreapi.com/products", requestBody, FakeStoreResponseDTO.class);

        response = convertFakeStoreResponseToProduct(fakeStoreResponse.getBody());
        return response;
    }
}
