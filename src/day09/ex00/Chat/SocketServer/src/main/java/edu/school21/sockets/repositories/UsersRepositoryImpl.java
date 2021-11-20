package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    public void setRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
    }

    private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        return new User(resultSet.getLong("id"),
                resultSet.getString("username"), resultSet.getString("password"));
    };

    public Optional<User> findByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", userRowMapper, username);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", userRowMapper, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (username, password) VALUES (?, ?)", user.getUsername(), user.getPassword());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET username = ?, password = ? WHERE id = ?",
                user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
