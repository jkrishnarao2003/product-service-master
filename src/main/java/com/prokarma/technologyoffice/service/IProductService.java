package com.prokarma.technologyoffice.service;

import java.util.List;

import com.prokarma.technologyoffice.dto.ProductDto;


/**
 * @author Ninod Pillai
 *
 */
public interface IProductService {

    void addProduct(ProductDto productVo);

    List<ProductDto> getProducts();

    void deleteProduct(Long id);

    ProductDto getProductsById(long productId);
    
    void updateProductById(ProductDto productDto);

	ProductDto getProductsByName(String productName);
}
