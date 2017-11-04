package com.prokarma.technologyoffice.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prokarma.technologyoffice.dto.ProductDto;
import com.prokarma.technologyoffice.service.IProductService;


/**
 * @author Ninod Pillai
 *
 */
@RestController
@RequestMapping(value = "products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto addProduct(@RequestBody final ProductDto productVo) {
        productService.addProduct(productVo);
        return productVo;
    }

    @RequestMapping(value = "/searchbyId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto getProductsById(@RequestParam(value = "productId", required = true) final Long productId) {
        return productService.getProductsById(productId);
    }
    
    @RequestMapping(value = "/searchbyName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto getProductsByName(@RequestParam(value = "productName", required = true) final String productName) {
        return productService.getProductsByName(productName);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteProduct(@PathVariable final Long id) {
    	
        productService.deleteProduct(id);
        return "{\"DELETED\":\"TRUE\"}";
    }
    
    @RequestMapping(value = "/updateById", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProductById(@RequestBody final ProductDto productDto) {
    	productService.updateProductById(productDto);
    	return productDto;
    }
}
