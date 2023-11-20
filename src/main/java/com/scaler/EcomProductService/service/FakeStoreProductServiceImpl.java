package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.Client.FakeStoreAPIClient;
import com.scaler.EcomProductService.Utils.ProductNotFoundException;
import com.scaler.EcomProductService.dto.*;
import com.scaler.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.scaler.EcomProductService.Mapper.ProductMapper.productRequestDTOtofakeStoreProductRequestDTO;
import static com.scaler.EcomProductService.Mapper.ProductMapper.fakeStoreProductResponseDTOtoproductResponseDTO;
import static com.scaler.EcomProductService.Utils.ProductUtils.isNull;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreAPIClient fakeStoreAPIClient;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder,FakeStoreAPIClient fakeStoreAPIClient){
        this.restTemplateBuilder= restTemplateBuilder;
        this.fakeStoreAPIClient=fakeStoreAPIClient;
    }
    @Override
    public ProductListResponseDTO getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProductResponseDTOS = fakeStoreAPIClient.getAllProducts();
        ProductListResponseDTO reponseDTO = new ProductListResponseDTO();
        for(FakeStoreProductResponseDTO productResponse : fakeStoreProductResponseDTOS){
            reponseDTO.getProducts().add(fakeStoreProductResponseDTOtoproductResponseDTO(productResponse));
        }
        return reponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreAPIClient.getProductById(id);
        if(isNull(fakeStoreProductResponseDTO))
            throw new ProductNotFoundException("Product Not found:" + id);
        return fakeStoreProductResponseDTOtoproductResponseDTO(fakeStoreProductResponseDTO);
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = productRequestDTOtofakeStoreProductRequestDTO(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO =fakeStoreAPIClient.createProduct(fakeStoreProductRequestDTO);
            return fakeStoreProductResponseDTOtoproductResponseDTO(fakeStoreProductResponseDTO);
        }

    @Override
        public boolean deleteProduct(int id) {
        fakeStoreAPIClient.deleteProduct(id);
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }
}
