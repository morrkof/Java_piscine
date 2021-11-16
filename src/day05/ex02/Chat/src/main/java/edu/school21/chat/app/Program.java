package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class Program {
    public static void main(String[] args) throws SQLException {

        HikariDataSource ds = getDataSource();
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        User author = new User(2L, "user", "user");
        Chatroom room = new Chatroom(2L, "Chatik");
        Message message = new Message(null, "HELLO WORLD!!!1", Timestamp.from(Instant.now()));
        message.setAuthor(author);
        message.setRoom(room);
        try {
            messagesRepository.save(message);
            System.out.println(message);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        User authorWRONG = new User(222L, "user", "user");
        message.setAuthor(authorWRONG);
        try {
            messagesRepository.save(message);
            System.out.println(message);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        ds.close();
    }

    private static HikariDataSource getDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/chat");
        ds.setUsername("idea");
        ds.setPassword("idea");
        return ds;
    }
}
