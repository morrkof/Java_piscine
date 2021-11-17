package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    DataSource ds;
    Connection con;

    public ProductsRepositoryJdbcImpl(DataSource ds) throws SQLException {
        this.ds = ds;
        con = ds.getConnection();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Product;");
        pstmt.execute();
        ResultSet rs = pstmt.getResultSet();
        while (rs.next()) {
            Long productId = rs.getLong("id");
            String productName = rs.getString("name");
            int productPrice = rs.getInt("price");
            Product product = new Product(productId, productName, productPrice);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Product WHERE id = ?;");
        pstmt.setLong(1, id);
        pstmt.execute();
        ResultSet rs = pstmt.getResultSet();
        if (rs.next()) {
            Long productId = rs.getLong("id");
            String productName = rs.getString("name");
            int productPrice = rs.getInt("price");
            Product product = new Product(productId, productName, productPrice);
            return Optional.of(product);
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("UPDATE Product SET name = ?, price = ? WHERE id = ?;");
        pstmt.setString(1, product.getName());
        pstmt.setInt(2, product.getPrice());
        pstmt.setLong(3, product.getId());
        pstmt.execute();
    }

    @Override
    public void save(Product product) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO Product (name, price) VALUES (?, ?);");
        pstmt.setString(1, product.getName());
        pstmt.setInt(2, product.getPrice());
        pstmt.execute();

        pstmt = con.prepareStatement("CALL IDENTITY();");
        pstmt.execute();
        ResultSet rs = pstmt.getResultSet();
        if (rs.next()) {
            product.setId(rs.getLong(1));
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("DELETE FROM Product WHERE id = ?;");
        pstmt.setLong(1, id);
        pstmt.execute();
    }
}
