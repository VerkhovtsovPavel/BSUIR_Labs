package by.bsuir.verkpavel.courseproject.ui.companetns;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {
    private static final long serialVersionUID = -6335717413387987509L;
    private BufferedImage image;

    public ImageLabel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage scaledBI = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D grap = scaledBI.createGraphics();

        grap.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
        grap.dispose();
        this.setIcon(new ImageIcon(scaledBI));
    }

}
