package com.core.UI;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

/**
 * Created by siefker on 10.11.2015.
 */
public class WebcamEvaluation {

    public Boolean compareInCorner(String corner) throws IOException {

        Webcam webcam = Webcam.getDefault();
        webcam.close();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/1.png"));
        File file1 = new File("src/main/java/com/core/UI/Pictures/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        BufferedImage image1 = ImageIO.read(fis1);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/2.png"));
        File file2 = new File("src/main/java/com/core/UI/Pictures/2.png");
        FileInputStream fis2 = new FileInputStream(file2);
        BufferedImage image2 = ImageIO.read(fis2);

        if (corner.equals("TOP_LEFT")) {

            Boolean motionInTopLeftCornerDetected = compareTopLeftCorners(image1, image2);
            return motionInTopLeftCornerDetected;
        }

        return false;
    }

    private boolean compareTopLeftCorners(BufferedImage image1, BufferedImage image2) throws IOException {

        int identicalPixels = 0, differentPixels = 0;

        int width = 640;
        int height = 150;

        for(int a=440;a<width;a++)
        {
            for(int b=0;b<height;b++)
            {

                int clr1=  image1.getRGB(a,b);
                int  red1   = (clr1 & 0x00ff0000) >> 16;
                int  green1 = (clr1 & 0x0000ff00) >> 8;
                int  blue1  =  clr1 & 0x000000ff;

                int clr2=  image2.getRGB(a,b);
                int  red2   = (clr2 & 0x00ff0000) >> 16;
                int  green2 = (clr2 & 0x0000ff00) >> 8;
                int  blue2  =  clr2 & 0x000000ff;

                if(((red1 - red2) + (green1 - green2) + (blue1 - blue2))<50)
                {
                    identicalPixels=identicalPixels+1;
                }
                else
                    differentPixels=differentPixels+1;
            }
        }

        float allPixels = (height*width);
        float percentageIdenticalPixels =(100*identicalPixels)/allPixels;

        if (percentageIdenticalPixels <= 99) {

            return true;
        }

        else {
            return false;
        }
    }
}
