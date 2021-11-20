package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.io.IOException;
import java.util.Scanner;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--port", required = true)
    private static int port;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        Client client = new Client();
        client.startConnection("localhost", port);
        Scanner sc = new Scanner(System.in);
        try {
            while (true) {
                String response = client.sendMessage(sc.nextLine());
                System.out.println(response);
                if (response.equals("Successful!") || response.equals("i don't understand") || response.equals("Already exist")) {
                    client.stopConnection();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }
}
