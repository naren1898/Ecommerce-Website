package com.EcomProductService.Repository;

import com.EcomProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitle(String title);
    //Product findById(int id);
    //Product findByTitleOrDescription(String title, String description);
    Product findByTitleAndDescription(String title, String description);
    Product findByPrice_AmountLessThan(double amount);
    Product findByDescription(String description);
    Product deleteByTitle(String title);
    //Product findByPriceGreaterThan(double price);
    //Product findByPriceGreaterThanEqual(double price);
    //Product findByPriceLessThanEqual(double price);
    //Product findByPriceBetweenStartPriceAndEndPrice(double StartPrice, double EndPrice);
    @Query(value=CustomQueries.FIND_PROD_BY_TITLE,nativeQuery=true)
    Product findProductByTitleLikeAndId(String Title, UUID id);
    //@Query(value="select * from products",nativeQuery=true)
    //Product findALLProducts(String Title, UUID id);

}