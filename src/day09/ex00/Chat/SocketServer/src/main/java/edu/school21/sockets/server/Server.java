package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    UsersService usersService;

    public void start(int port) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        usersService = (UsersService) context.getBean("service");
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println("Hello from Server!!! \uD83D\uDC4B");
        String answer = in.readLine();
        if ("signUp".equals(answer)) {
            out.println("Enter username: ");
            String username = in.readLine();
            out.println("Enter password: ");
            String password = in.readLine();
            if (usersService.signUp(username, password)) {
                out.println("Successful!");
            } else {
                out.println("Already exist");
            }
        } else {
            out.println("i don't understand");
        }
        stop();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}
