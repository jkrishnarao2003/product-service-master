package com.prokarma.technologyoffice.dao;


import java.util.List;

import com.prokarma.technologyoffice.dto.ProductDto;

/**
 * Created by kranthi on 6/23/2016.
 */
public interface IProductDao {

    ProductDto getProductsById(Long productIds);

    List<ProductDto> findAll();

    void delete(long id);

    void save(ProductDto ProductDto);
    
    void updateProductById(ProductDto productDto);

	ProductDto getProductsByName(String productName);

}
