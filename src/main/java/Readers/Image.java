package Readers;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private String path;
    private double width;
    private double height;
    private BufferedImage img;
    public String getPath() {
        return path;
    }

    public Image(String path)  {
        this.path = path;
        try {
            img= ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setHeight(img.getHeight());
        setWidth(img.getWidth());

    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setWidth(double width) {
        this.width = width;
    }


    public void setHeight(double height) {
        this.height = height;
    }
    public int getRatioByConcreteDenominator(int denominator){
        System.out.println("image width: "+ width+", image height: "+ height);
        return (int) (height/(width/denominator));
    }
}
