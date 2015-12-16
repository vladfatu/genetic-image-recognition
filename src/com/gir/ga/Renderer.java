package com.gir.ga;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created by Vlad on 13-Dec-15.
 */
public class Renderer {

    public BufferedImage generateImageFromDna(List<Gene> dna, GAParameters parameters, double multiplier) {
//        long timestamp = System.currentTimeMillis();
        BufferedImage image = new BufferedImage((int)(parameters.getTargetImage().getWidth() * multiplier), (int)(parameters.getTargetImage().getHeight() * multiplier),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(
                RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_SPEED);

        drawBlackBackground(graphics, parameters, multiplier);

        for (Gene gene : dna) {
            int[] x = new int[gene.getPoints().size()];
            int[] y = new int[gene.getPoints().size()];

            for (int i=0; i<gene.getPoints().size(); i++) {
                x[i] = (int)(gene.getPoints().get(i).getX() * multiplier);
                y[i] = (int)(gene.getPoints().get(i).getY() * multiplier);
            }

            Polygon p = new Polygon(x, y, gene.getPoints().size());
            graphics.setColor(gene.getColor());
            graphics.fillPolygon(p);
        }
//        System.out.println("rendering took : " + (System.currentTimeMillis() - timestamp));
        return image;
    }

    private void drawBlackBackground(Graphics2D graphics, GAParameters parameters, double multiplier) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, (int)(parameters.getTargetImage().getWidth() * multiplier), (int)(parameters.getTargetImage().getHeight() * multiplier));
    }

}
