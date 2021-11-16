package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.MessagesRepository;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException {

        HikariDataSource ds = getDataSource();
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID");
        if (scanner.hasNextInt()) {
            Long id = scanner.nextLong();
            Optional<Message> msg = messagesRepository.findById(id);
            if (msg.isPresent())
                System.out.println(msg.get());
            else
                System.out.println("Message not found");
        }
        scanner.close();
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
