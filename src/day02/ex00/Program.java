package day02.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static Map<String, String> makeMapSignatures() throws IOException {
        Map<String, String> result = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream("./src/day02/ex00/signatures.txt"); // rename
        int i;
        StringBuilder line = new StringBuilder();
        while ((i = fileInputStream.read())!= -1) {
            if (i != '\n') {
                line.append((char) i);
            } else {
                int comma = line.indexOf(",");
                result.put(line.substring(0, comma), line.substring(comma+2));
                line.delete(0, line.length());
            }
        }
        fileInputStream.close();
        return result;
    }
    public static void main(String[] args) throws IOException {
        Map<String, String> signatures = makeMapSignatures();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.equals("42")) {
                scanner.close();
                System.exit(0);
            }
            byte [] fileBytes = new byte[8];
            FileInputStream fileInputStream = new FileInputStream(line);
            FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true);
            if (fileInputStream.available() >= 8) {
                fileInputStream.read(fileBytes);
            }
            System.out.println(Long.toHexString(ByteBuffer.wrap(fileBytes).getLong()));

            // find by key
            // write to file

//            fileOutputStream.write(greetings.getBytes());
//            fileOutputStream.close();
        }
    }

}
