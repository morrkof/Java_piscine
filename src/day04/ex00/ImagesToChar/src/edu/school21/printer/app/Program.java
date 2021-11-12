package day04.ex00.ImagesToChar.src.edu.school21.printer.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        Settings set = null;
        if (args.length != 3 || !(set = new Settings(args)).isValid()) {
            System.out.println("need arguments: --w=[WHITE_SYMBOL] --b=[BLACK_SYMBOL] --path=[PATH_TO_FILE]");
            System.exit(-1);
        }

        FileInputStream fileInputStream = new FileInputStream(set.getPath());
        BufferedImage image = ImageIO.read(fileInputStream);
        char[][] arr = new char[image.getWidth()][image.getHeight()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                if (color == Color.BLACK.getRGB()) {
                    System.out.print(set.getB());
//                    arr[x][y] = set.getB();
                } else {
                    System.out.print(set.getW());
//                    arr[x][y] = set.getW();
                }

            }
            System.out.println();
        }


    }
}

