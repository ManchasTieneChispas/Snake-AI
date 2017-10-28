import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class test {

    public static void main(String args[]) throws AWTException, IOException, InterruptedException {
        Mapper map = new Mapper(250000, 100, 2500);
        map.findSnakeGame();
        map.start();
    }

}
