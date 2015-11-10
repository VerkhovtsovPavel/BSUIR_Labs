package by.bsuir.verkpavel.adb.atm_client.ui.companetns;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	private static final long serialVersionUID = -6335717413387987509L;
	private BufferedImage image;

    public ImagePanel(String imagePath) {
       try {                
          image = ImageIO.read(new File(imagePath));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }

}
