package com.prokarma.technologyoffice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.prokarma.technologyoffice.dao.IProductDao;
import com.prokarma.technologyoffice.dto.ProductDto;
import com.prokarma.technologyoffice.service.IProductService;


/**
 * @author Ninod Pillai
 *
 */
public class ProductServiceImpl implements IProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final IProductDao productDao;

    public ProductServiceImpl(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(ProductDto productVo) {
        productDao.save(productVo);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productDao.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.delete(id);
    }

    @Override
    public ProductDto getProductsById(long productId) {
    	LOGGER.info("ProductServiceImpl.getProductsById()");
        return productDao.getProductsById(productId);
    }

	@Override
	public void updateProductById(ProductDto productDto) {
		LOGGER.info("ProductServiceImpl.updateProductById()");
		productDao.updateProductById(productDto);
	}

	@Override
	public ProductDto getProductsByName(String productName) {
		LOGGER.info("ProductServiceImpl.getProductsByName()");
		return productDao.getProductsByName(productName);
	}
}
