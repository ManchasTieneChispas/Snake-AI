import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class test {

    public static void main(String args[]) throws AWTException, IOException {
        Mapper map = new Mapper(5, 5, 5);
        map.findSnakeGame();
    }

}
