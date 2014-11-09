/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ispi.parall.lr2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author gerasimov.pk
 */
public class Main {

    private static int MONO_THRESHOLD = 268;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        makeMonoImage("c:/temp/3.png");
    }
    
    public static void makeMonoImage(String imageName) {
        BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                if (col.getRed() + col.getGreen() + col.getBlue() > MONO_THRESHOLD)
                    col = new Color(255, 255, 255);
                else
                    col = new Color(0, 0, 0);
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        try {
            File outputFile = new File("c:/temp/3-inverted.png");
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
