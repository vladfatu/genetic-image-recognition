package com.gir.ga;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Vlad on 13-Dec-15.
 */
public class Renderer {

    public BufferedImage generateImageFromDna(List<Gene> dna, GAParameters parameters, int multiplier) {
        BufferedImage image = new BufferedImage(parameters.getTargetImage().getWidth() * multiplier, parameters.getTargetImage().getHeight() * multiplier,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        drawBlackBackground(graphics, parameters, multiplier);

        for (Gene gene : dna) {
            int[] x = new int[gene.getPoints().size()];
            int[] y = new int[gene.getPoints().size()];

            for (int i=0; i<gene.getPoints().size(); i++) {
                x[i] = gene.getPoints().get(i).getX() * multiplier;
                y[i] = gene.getPoints().get(i).getY() * multiplier;
            }

            Polygon p = new Polygon(x, y, gene.getPoints().size());
            graphics.setColor(gene.getColor());
            graphics.fillPolygon(p);
        }

        return image;
    }

    private void drawBlackBackground(Graphics2D graphics, GAParameters parameters, int multiplier) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, parameters.getTargetImage().getWidth() * multiplier, parameters.getTargetImage().getHeight() * multiplier);
    }

}
