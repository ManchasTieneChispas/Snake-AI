import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.tools.JavaCompiler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Mapper {

    private Matrix input;
    private Matrix ih; //weights from input to hidden
    private Matrix hidden;
    private Matrix ho; //weights from hidden to output
    private Matrix output;
    private Robot grabber;
    private KeyFrame runner;
    private Timeline timer;
    private BufferedImage scree;
    private String tleft = "topleft";
    private String tright = "topright";
    private String bleft = "bottomLeft";
    private String bright= "bottomright";
    private HashMap<String, int[]> corners;


    public Mapper(int i, int h, int o) throws AWTException {
        input = new Matrix(i, 1);
        hidden = new Matrix(h, 1);
        output = new Matrix(o, 1);
        ih = new Matrix(i, h);
        ho = new Matrix(h, o);
        grabber = new Robot();
        BufferedImage screen = null;
        runner = new KeyFrame(Duration.millis(37.5), event -> {
            //screen = grabber.createScreenCapture(new Rectangle());
        });
        timer = new Timeline(runner);
        corners = new HashMap<String, int[]>();
    }

    public void findSnakeGame() throws AWTException, IOException {
        BufferedImage first = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        Color color;
        int[][] coords = new int[4][2];
        int x = 0;
        for(int r = 0; r < first.getHeight(); r++) {
            for(int c = 0; c < first.getWidth(); c++) {
                color = new Color(first.getRGB(c, r ));
                if(color.getBlue() == 2 && color.getRed() == 3 && color.getGreen() == 5) {
                    coords[x][0] = c;
                    coords[x][1] = r;
                    x++;
                }
            }
        }
        corners.put(tleft, coords[0]);
        corners.put(tright, coords[1]);
        corners.put(bleft, coords[2]);
        corners.put(bright, coords[3]);
        System.out.println(corners.get(tleft)[0]);
        System.out.println(corners.get(tright)[0]);
        int width = corners.get(tright)[0] - corners.get(tleft)[0];
        int height = corners.get(bleft)[1] - corners.get(tleft)[1];
        first = grabber.createScreenCapture(new Rectangle(corners.get(tleft)[0], corners.get(tleft)[1], width, height));
        File outputfile = new File("image.jpg");
        ImageIO.write(first, "jpg", outputfile);
    }




    //sigmoid function
    private double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }

}
