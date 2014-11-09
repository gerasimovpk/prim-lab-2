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
import java.util.concurrent.ForkJoinPool;
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
        makeMonoImage("c:/temp/4.png");
    }
    
    public static void makeMonoImage(String imageName) {
        BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MonochromeTask mt = new MonochromeTask(inputFile, 0, 0, inputFile.getWidth(), inputFile.getHeight(), MONO_THRESHOLD);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mt);

        try {
            File outputFile = new File("c:/temp/4-inverted.png");
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
