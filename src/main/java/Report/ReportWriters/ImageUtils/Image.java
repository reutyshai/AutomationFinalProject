package Report.ReportWriters.ImageUtils;

import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents an image entity that provides functionality to load an image
 * from a file path, retrieve its dimensions, and calculate aspect ratio.
 * <p>
 * This class utilizes {@link BufferedImage} to read image data and calculate properties.
 * </p>
 */
@Setter
public class Image {

    private String path;
    private double width;
    private double height;
    private BufferedImage img;

    /**
     * Constructs an {@code Image} object from the specified file path.
     *
     * @param path the path to the image file
     * @throws IllegalArgumentException if the file does not exist, is invalid, or unsupported
     */
    public Image(String path) {
        this.path = path;
        try {
            img = ImageIO.read(new File(path));
            if (img == null) {
                throw new IOException("Invalid image file format or unsupported file: " + path);
            }
            setHeight(img.getHeight());
            setWidth(img.getWidth());
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to load image from path: " + path, e);
        }
    }

    /**
     * Gets the file path of the image.
     *
     * @return the file path of the image
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the file path of the image.
     *
     * @param path the new file path of the image
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Sets the width of the image.
     *
     * @param width the width of the image in pixels
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Sets the height of the image.
     *
     * @param height the height of the image in pixels
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Calculates the aspect ratio of the image based on a given width denominator.
     * <p>
     * The method scales the image height proportionally to the given width denominator.
     * </p>
     *
     * @param denominator the desired width denominator for calculating the aspect ratio
     * @return the corresponding height calculated based on the aspect ratio
     * @throws IllegalArgumentException if the denominator is zero or negative
     */
    public int getRatioByConcreteDenominator(int denominator) {
        if (denominator <= 0) {
            throw new IllegalArgumentException("Denominator must be greater than 0");
        }
        System.out.println("image width: " + width + ", image height: " + height);
        return (int) Math.round(height / (width / denominator));
    }
}
