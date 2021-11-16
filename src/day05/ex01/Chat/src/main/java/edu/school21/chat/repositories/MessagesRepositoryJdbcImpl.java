package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    DataSource dataSource;
    Connection connection;
    Statement statement;

    @Override
    public Optional<Message> findById(Long id) {
        try {
            ResultSet rs = statement.executeQuery(String.format("SELECT * from \"Message\" where id = %d;", id));
            while (rs.next()) {
                Long messageId = rs.getLong("id");
                Long authorId = rs.getLong("author");
                Long roomId = rs.getLong("room");
                String text = rs.getString("text");
                Timestamp date = rs.getObject("date", Timestamp.class);
                Message message = new Message(messageId, text, date);
                ResultSet rs2 = statement.executeQuery(String.format("SELECT * from \"User\" where id = %d;", authorId));
                while (rs2.next()) {
                    String login = rs2.getString("login");
                    String password = rs2.getString("password");
                    message.setAuthor(new User(authorId, login, password));
                }
                ResultSet rs3 = statement.executeQuery(String.format("SELECT * from \"Chatroom\" where id = %d;", roomId));
                while (rs3.next()) {
                    String name = rs3.getString("name");
                    message.setRoom(new Chatroom(roomId, name));
                }

                return Optional.of(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        connection = dataSource.getConnection();
        statement = connection.createStatement();
    }
}
