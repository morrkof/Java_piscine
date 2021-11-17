package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcImplTest {

    private List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
    private final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "cake", 170);
    private final Product EXPECTED_FIND_BY_ID_PRODUCT1 = new Product(1L, "pie", 130);
    private final Product EXPECTED_FIND_BY_ID_PRODUCT2 = new Product(2L, "chocolate", 42);
    private final Product EXPECTED_FIND_BY_ID_PRODUCT3 = new Product(3L, "cookie", 20);
    private final Product EXPECTED_FIND_BY_ID_PRODUCT4 = new Product(4L, "candie", 5);

    private Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "cookie", 20);
    private ProductsRepositoryJdbcImpl productsRepositoryJdbc;

    @BeforeEach
    public void init() throws SQLException {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        DataSource ds = builder.setType(EmbeddedDatabaseType.HSQL).addScripts("schema.sql", "data.sql").build();
        productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(ds);
    }

    @Test
    void findAllTest() throws SQLException {
        EXPECTED_FIND_ALL_PRODUCTS.clear();
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT1);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT2);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT3);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT4);
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll(), "Unexpected product");
    }

    @Test
    void findByIdTest() throws SQLException {
        productsRepositoryJdbc.findById(1L);
        Optional<Product> optionalProduct = productsRepositoryJdbc.findById(EXPECTED_FIND_BY_ID_PRODUCT.getId());
        Product result = null;
        if (optionalProduct.isPresent()) {
            result = optionalProduct.get();
        }
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, result, "Unexpected product");
    }

    @Test
    void updateTest() throws SQLException {
        productsRepositoryJdbc.update(EXPECTED_UPDATED_PRODUCT);
        Optional<Product> optionalProduct = productsRepositoryJdbc.findById(EXPECTED_UPDATED_PRODUCT.getId());
        Product result = null;
        if (optionalProduct.isPresent()) {
            result = optionalProduct.get();
        }
        assertEquals(EXPECTED_UPDATED_PRODUCT, result, "Unexpected product");
    }

    @Test
    void saveTest() throws SQLException {
        Product product = new Product(null, "pizza", 200);
        productsRepositoryJdbc.save(product);
        Optional<Product> optionalProduct = productsRepositoryJdbc.findById(product.getId());
        Product result = null;
        if (optionalProduct.isPresent()) {
            result = optionalProduct.get();
        }
        assertEquals(product, result, "Unexpected product");
    }

    @Test
    void deleteTest() throws SQLException {
        Product product = new Product(null, "pizza", 200);
        productsRepositoryJdbc.save(product);
        Optional<Product> optionalProduct = productsRepositoryJdbc.findById(product.getId());
        Product result = null;
        if (optionalProduct.isPresent()) {
            result = optionalProduct.get();
        }
        EXPECTED_FIND_ALL_PRODUCTS.clear();
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT1);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT2);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT3);
        EXPECTED_FIND_ALL_PRODUCTS.add(EXPECTED_FIND_BY_ID_PRODUCT4);
        productsRepositoryJdbc.delete(result.getId());
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, productsRepositoryJdbc.findAll(), "Unexpected product");
    }
}