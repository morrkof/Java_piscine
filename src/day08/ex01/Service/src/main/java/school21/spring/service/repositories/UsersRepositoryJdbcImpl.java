package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        String query = "select * from users where id = ?";
        User user = null;
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            user = new User();
            if (rs.next()) {
                user.setId(id);
                user.setEmail(rs.getString("email"));
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String query = "select * from users";
        User user;
        List<User> users = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("email"));
                users.add(user);
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User entity) {
        String query = "insert into users (email) " +
                "values (?) returning id";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, entity.getEmail());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getLong("id"));
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String query = "update users set email=? where id=?" +
                " returning id";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, entity.getEmail());
            pst.setLong(2, entity.getId());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                entity.setId(rs.getLong("id"));
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from users where id=?";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setLong(1, id);
            pst.execute();
            pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = "select * from users where email=?";
        User user = null;
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(email);
            }
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
}