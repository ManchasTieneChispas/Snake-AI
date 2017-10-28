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
    private double[] input;
    private double[][] ih; //weights from input to hidden
    private double[] hidden;
    private double[][] ho; //weights from hidden to output
    private double[] output;
    private Robot grabber;
    private KeyFrame runner;
    private Timeline timer;
    private BufferedImage screen;
    private String tleft = "topleft";
    private String tright = "topright";
    private String bleft = "bottomLeft";
    private String bright= "bottomright";
    private HashMap<String, int[]> corners;
    private Rectangle screenRec;


    public Mapper(int i, int h, int o) throws AWTException, IOException {
        input = new double[i];
        hidden = new double[h];
        output = new double[o];
        ih = new double[h][i];
        ho = new double[o][h];

        grabber = new Robot();
        BufferedImage screen = null;
        corners = new HashMap<String, int[]>();
        generateRandomWeights();
    }

    public void start() throws InterruptedException{
        while(true) {
            run();
            Thread.sleep(38);
        }
    }

    public void generateRandomWeights() {
        for(int h = 0; h < ih.length; h++) {
            for(int i = 0; i < ih[0].length; i++) {
                ih[h][i] = (Math.random() * 2) -1;
            }
        }
        for(int o = 0; o < ho.length; o++) {
            for(int h = 0; h < ho[0].length; h++) {
                ho[o][h] = (Math.random() * 2) -1;
            }
        }
    }

    public void run() {
        screen = grabber.createScreenCapture(screenRec);
        input = averageRGB();
        for(int h = 0; h < hidden.length; h++) {
            for(int i = 0; i < input.length; i++) {
                hidden[h] += input[i] * ih[h][i];
            }
            hidden[h] = sigmoid(hidden[h]);
        }
        for(int o = 0; o < output.length; o++) {
            for(int h = 0; h < hidden.length; h++) {
                output[o] += hidden[h] * ho[o][h];
            }
            output[o] = sigmoid(output[o]);
            System.out.println(output[o]);
        }
    }

    public double[] averageRGB() {
        //screen = grabber.createScreenCapture(screenRec); //temporaty
        int pix[] = screen.getRGB(0, 0, (int)screenRec.getWidth(), (int)screenRec.getHeight(), null, 0, (int)screenRec.getWidth());
        Color[] c= new Color[pix.length];
        int sum = 0;
        double[]  gray = new double[pix.length];
        for(int i = 0; i < pix.length; i++) {
            sum = 0;
            c[i] = new Color(pix[i], true);
            sum+= c[i].getBlue() + c[i].getGreen() + c[i].getRed();
            gray[i] = (double)(sum/3);
        }
        return gray;
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
        screenRec = new Rectangle(corners.get(tleft)[0], corners.get(tleft)[1], width, height);
        File outputfile = new File("image.jpg");
        ImageIO.write(first, "jpg", outputfile);
    }




    //sigmoid function
    private double sigmoid(double x) {
        return (1/( 1 + Math.pow(Math.E,(-1*x))));
    }

}
