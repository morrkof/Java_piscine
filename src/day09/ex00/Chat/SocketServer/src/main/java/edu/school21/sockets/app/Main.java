package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.server.Server;

import java.io.IOException;

@Parameters(separators = "=")
public class Main {
    @Parameter(names = "--port", required = true)
    private static int port;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        JCommander.newBuilder().addObject(main).build().parse(args);
        Server server = new Server();
        try {
            server.start(port);
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}
