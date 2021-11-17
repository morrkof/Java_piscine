package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmbeddedDataSourceTest {
    private DataSource ds;

    @BeforeEach
    public void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        ds = builder.setType(EmbeddedDatabaseType.HSQL).addScripts("schema.sql", "data.sql").build();
    }

    @Test
    public void getConnectionTest() throws SQLException {
        assertNotNull(ds.getConnection());
    }
}
