package com.prokarma.technologyoffice.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

import com.prokarma.technologyoffice.controller.ProductController;
import com.prokarma.technologyoffice.dao.IProductDao;
import com.prokarma.technologyoffice.dao.impl.ProductDaoImpl;
import com.prokarma.technologyoffice.service.IProductService;
import com.prokarma.technologyoffice.service.impl.ProductServiceImpl;



/**
 * @author Ninod Pillai
 *
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public ProductController productController(final IProductService productService) {
        return new ProductController(productService);
    }

    @Bean
    public IProductService productService(final IProductDao iProductDao) {
        return new ProductServiceImpl(iProductDao);
    }

    @Bean
    public IProductDao productDao(final JdbcTemplate jdbcTemplate,OracleSequenceMaxValueIncrementer productSequenceIncrementer) {
        return new ProductDaoImpl(jdbcTemplate,productSequenceIncrementer);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    public OracleSequenceMaxValueIncrementer productSequenceIncrementer(final DataSource dataSource){
    	OracleSequenceMaxValueIncrementer incrementer = new OracleSequenceMaxValueIncrementer();
    	incrementer.setDataSource(dataSource);
    	incrementer.setIncrementerName("product_q1");
    	return incrementer;
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase embeddedDatabase = embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2)
                .addScript("create-db.sql").addScript("insert-data.sql").build();
        return embeddedDatabase;
    }
}
