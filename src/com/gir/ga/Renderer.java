package com.gir.ga;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Vlad on 13-Dec-15.
 */
public class Renderer {

    public BufferedImage generateImageFromDna(List<Gene> dna, GAParameters parameters) {
        BufferedImage image = new BufferedImage(parameters.getTargetImage().getWidth(), parameters.getTargetImage().getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        drawBlackBackground(graphics, parameters);

        for (Gene gene : dna) {
            int[] x = new int[gene.getPoints().size()];
            int[] y = new int[gene.getPoints().size()];

            for (int i=0; i<gene.getPoints().size(); i++) {
                x[i] = gene.getPoints().get(i).getX();
                y[i] = gene.getPoints().get(i).getY();
            }

            Polygon p = new Polygon(x, y, gene.getPoints().size());
            graphics.setColor(gene.getColor());
            graphics.fillPolygon(p);
        }

        return image;
    }

    private void drawBlackBackground(Graphics2D graphics, GAParameters parameters) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, parameters.getTargetImage().getWidth(), parameters.getTargetImage().getHeight());
    }

}
