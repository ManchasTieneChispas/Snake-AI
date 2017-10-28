import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class test {

    public static void main(String args[]) throws AWTException, IOException {
        Mapper map = new Mapper(250000, 100, 2500);
        map.findSnakeGame();
        int t[] = map.averageRGB();
        int count = 0;
        for(int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
            count = i;
        }
        System.out.println(count);
    }

}
