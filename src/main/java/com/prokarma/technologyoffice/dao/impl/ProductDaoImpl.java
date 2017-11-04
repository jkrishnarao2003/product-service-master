package com.prokarma.technologyoffice.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

import com.prokarma.technologyoffice.dao.IProductDao;
import com.prokarma.technologyoffice.dto.ProductDto;


/**
 * @author Ninod Pillai
 *
 */
public class ProductDaoImpl implements IProductDao {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final OracleSequenceMaxValueIncrementer productSequenceIncrementer;

    public ProductDaoImpl(JdbcTemplate jdbcTemplate,OracleSequenceMaxValueIncrementer productSequenceIncrementer) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.productSequenceIncrementer = productSequenceIncrementer;
    }

    @Override
    public List<ProductDto> findAll() {
        return jdbcTemplate.query("select * from product", productDtoRowMapper);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from product where id= ?", id);

    }

    @Override
    public void save(ProductDto productDto) {
    	productDto.setId(productSequenceIncrementer.nextLongValue());
        jdbcTemplate.update("insert into product values (?,?,?,?)",
                productDto.getId(), productDto.getName(), productDto.getCategory(), productDto.getCost());
    }

    @Override
    public ProductDto getProductsById(Long productId) {
    	LOGGER.info("ProductDaoImpl.getProductsById()");
    	try{
       List<ProductDto> products =  namedParameterJdbcTemplate.query("select * from product where id = :productId", new MapSqlParameterSource().addValue("productId", productId), productDtoRowMapper);
    	if(products != null && products.size() > 0){
    		return products.get(0);
    	}
    	}catch (Exception e){
    		LOGGER.error("Inside Exception :" + e);
    	}
    	return null;
    }

    @Override
    public void updateProductById(ProductDto productDto) {
    	LOGGER.info("ProductDaoImpl.updateProductById()");
    	jdbcTemplate.update("update product set name = ?, category = ?, cost = ? where id = ?", new Object[] {productDto.getName(), productDto.getCategory(), productDto.getCost(), productDto.getId()});
    }

    private RowMapper<ProductDto> productDtoRowMapper = new RowMapper<ProductDto>() {
        @Override
        public ProductDto mapRow(ResultSet resultSet, int i) throws SQLException {
            ProductDto productDto = new ProductDto();
            productDto.setId(resultSet.getLong("id"));
            productDto.setName(resultSet.getString("name"));
            productDto.setCategory(resultSet.getString("category"));
            productDto.setCost(resultSet.getBigDecimal("cost"));
            return productDto;
        }
    };

	@Override
	public ProductDto getProductsByName(String productName) {
    	try{
            List<ProductDto> result = namedParameterJdbcTemplate.query("select * from product where lower(name) = lower(:productName)", new MapSqlParameterSource().addValue("productName", productName), productDtoRowMapper);
            LOGGER.info("Getting Products by Name :" + result);
            if(result != null && result.size() > 0){
        	return result.get(0);
        }
    	}catch (Exception e){
    			LOGGER.error("In Exception : " + e);
        		e.printStackTrace();
        	}
    	return null;
        }

}
