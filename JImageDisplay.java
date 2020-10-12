import java.awt.image.*;
import java.awt.*;

public class JImageDisplay extends javax.swing.JComponent {
public java.awt.image.BufferedImage img;
public JImageDisplay(int width, int height) {
    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Dimension imgDimensions = new Dimension(width,height);
    setPreferredSize(imgDimensions);
}
@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img,0, 0, getWidth(), getHeight(),null);
    }
    public void clearImage() {
    Color black = new Color(0,0,0);
    int rgb = black.getRGB();
    int[] arr = new int[rgb];
    img.setRGB(0,0,getWidth(), getHeight(), arr, 0, 1);
    }
    public void drawPixel(int x, int y, int rgbColor) {
    img.setRGB(x,y,rgbColor);
    }
}
