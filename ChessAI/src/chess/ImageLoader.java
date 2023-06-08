package chess;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private final String basePath = "src/chess-icons/";

    public Image loadImage(String imageName, int tileSize) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(basePath + imageName + ".png"));
        } catch (IOException e) {
            System.out.println("File path: '" + basePath + imageName + ".png" + "' not found.");
        }
        return img.getScaledInstance(tileSize, tileSize, BufferedImage.SCALE_SMOOTH);
    }
}
