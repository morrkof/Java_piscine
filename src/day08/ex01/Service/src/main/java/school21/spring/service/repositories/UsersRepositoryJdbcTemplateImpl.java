package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        String query = "select * from users where id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        List<User> users = jdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(User.class));
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        String query = "insert into users (email) " +
                "values (:email)";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", entity.getEmail());
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void update(User entity) {
        String query = "update users set email=:email where id=:id" +
                " returning id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", entity.getId())
                .addValue("email", entity.getEmail());
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void delete(Long id) {
        String query = "delete from users where id=:id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "select * from users where email=:email";
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        List<User> users = jdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(User.class));
        return Optional.ofNullable(users.size() > 0 ? users.get(0) : null);
    }
}