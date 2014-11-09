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

    private static int threshold;
    private static String path;
    private static String name;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        path = args.length>0 && args[0]==null ? args[0] : "C:/temp/";
        name = args.length>0 && args[1]==null ? args[1] : "4.png";
        threshold = args.length>0 && args[2]==null ? Integer.parseInt(args[2]) : 286;
        makeMonoImage(path + name);
    }
    
    public static void makeMonoImage(String imageName) {
        BufferedImage inputFile = null;
        try {
            inputFile = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MonochromeTask mt = new MonochromeTask(inputFile, 0, 0, inputFile.getWidth(), inputFile.getHeight(), threshold);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mt);

        try {
            File outputFile = new File(path + "mono-"+name);
            ImageIO.write(inputFile, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
