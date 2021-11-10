package day02.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./src/day02/ex00/signatures.txt");
        int i;
        while((i=fileInputStream.read())!= -1){

            System.out.print((char)i);
            // make map
        }

        // read file signature

        // find by key
        FileOutputStream fileOutputStream = new FileOutputStream("result.txt", true);
        String greetings = "stroka";
        fileOutputStream.write(greetings.getBytes());
        fileOutputStream.close();

    }

}
