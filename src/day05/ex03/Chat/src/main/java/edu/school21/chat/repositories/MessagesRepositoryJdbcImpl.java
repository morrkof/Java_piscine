package edu.school21.chat.repositories;

import edu.school21.chat.errors.NotSavedSubEntityException;
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

    @Override
    public void save(Message message) {
        String query = String.format("INSERT INTO \"Message\" (author, room, text, date)" +
                        "values ('%d', '%d', '%s', '%tc') RETURNING ID", message.getAuthor().getId(),
                message.getRoom().getId(), message.getText(), message.getDate());
        try {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                message.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }

    @Override
    public void update(Message message) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE \"Message\" SET author = ")
                .append(checkNull(message.getAuthor().getId()))
                .append(", room = ")
                .append(checkNull(message.getRoom().getId()))
                .append(", text = ")
                .append(checkNull(message.getText()))
                .append(", date = ")
                .append(checkNull(message.getDate()))
                .append(" where id = ")
                .append(message.getId())
                .append(";");
        try {
            statement.executeUpdate(query.toString());
        } catch (SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }

    private String checkNull(Object o) {
        if (o == null)
            return "null";
        else
            return ("'" + o + "'");
    }

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        connection = dataSource.getConnection();
        statement = connection.createStatement();
    }
}
