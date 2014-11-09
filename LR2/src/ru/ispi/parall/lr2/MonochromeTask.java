/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ispi.parall.lr2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author gerasimov.pk
 */
public class MonochromeTask extends RecursiveAction{
    private  int startX;
    private  int startY;
    private  int endX;
    private  int endY;
    private  int threshold;
    private BufferedImage image;

    public MonochromeTask(BufferedImage image, int startX, int startY, int endX, int endY, int threshold) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.threshold = threshold;
        this.image = image;
    }

    protected void computeDirectly() {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                if (col.getRed() + col.getGreen() + col.getBlue() > threshold)
                    col = new Color(255, 255, 255);
                else
                    col = new Color(0, 0, 0);
                image.setRGB(x, y, col.getRGB());
            }
        }
    }
    
    @Override
    protected void compute() {
        if ((endX - startX < 50) || (endY - startY < 50)) {
            computeDirectl();
            return;
        }
    }
}
