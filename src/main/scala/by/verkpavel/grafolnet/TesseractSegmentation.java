package by.verkpavel.grafolnet;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pavel_Verkhovtsov on 3/21/17.
 */


public class TesseractSegmentation {

    private static void verifyTextOnImage(File screenshot) throws IOException {
        BufferedImage image = ImageIO.read(screenshot);
        //image = ImageHelper.convertImageToBinary(image);
        List<Word> lines = new Tesseract().getWords(image,2);
        for(Word rect : lines) {
            File outputfile = new File("./char_"+rect.hashCode()+".png");
            ImageIO.write(image.getSubimage((int) rect.getBoundingBox().getX(), (int) rect.getBoundingBox().getY(), rect.getBoundingBox().width, rect.getBoundingBox().height), "png", outputfile);
        }
    }

    public static void main(String[] arg) throws IOException {
        verifyTextOnImage(new File("./2.png"));
    }
}

