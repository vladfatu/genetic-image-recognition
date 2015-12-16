package com.gir.ga;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class FitnessCalculator {

//    private static byte[] targetDna;
//
//    public void setTargetDna(byte[] targetDna) {
//        this.targetDna = targetDna;
//    }

//    public void setTargetDna(String targetDna) {
//        this.targetDna = new byte[targetDna.length()];
//        for (int i=0; i<targetDna.length(); i++) {
//            if (targetDna.charAt(i) == '1') {
//                this.targetDna[i] = 1;
//            } else {
//                this.targetDna[i] = 0;
//            }
//        }
//    }

//    public double getFitness(Individual individual) {
//        int accurateGeneCount = 0;
//        for (int i=0; i<targetDna.length; i++) {
//            if (individual.getGene(i) == targetDna[i]) {
//                accurateGeneCount++;
//            }
//        }
//        return (double)accurateGeneCount / targetDna.length;
//    }

    public long getFitness(Individual individual, GAParameters parameters) {
//        long timestamp = System.currentTimeMillis();
        long sumSquaredError = 0;
        BufferedImage targetImage = parameters.getScaledTargetImage();
        BufferedImage individualImage = individual.getScaledImage(parameters);
        for (int i=0; i< targetImage.getWidth(); i++) {
            for (int j = 0; j < targetImage.getHeight(); j++) {
                Color targetColor = new Color(targetImage.getRGB(i, j));
                Color individualColor = new Color(individualImage.getRGB(i, j));
                int redDifference = targetColor.getRed() - individualColor.getRed();
                int greenDifference = targetColor.getGreen() - individualColor.getGreen();
                int blueDifference = targetColor.getBlue() - individualColor.getBlue();
                sumSquaredError += (redDifference * redDifference) + (greenDifference * greenDifference) + (blueDifference * blueDifference);
            }
        }
//        return 1 - ((double)sumSquaredError / parameters.getMaxSumSquaredError());
//        System.out.println("fitness took : " + (System.currentTimeMillis() - timestamp));
        return sumSquaredError;
    }

    public long getMaxSumSquaredError(BufferedImage image) {
        long maxDifferencePerPixel = 255 * 255 * 3;
        return maxDifferencePerPixel * image.getHeight() * image.getWidth();
    }

    public BufferedImage getScaledImage(BufferedImage initialImage, double ratio) {
//        int width = initialImage.getWidth() / ratio;
//        int height = initialImage.getHeight() / ratio;
//        Image tmp = initialImage.getScaledInstance(width, height, BufferedImage.SCALE_FAST);
//        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        scaledImage.getGraphics().drawImage(tmp, 0, 0, null);
//        return scaledImage;


        int newWidth = new Double(initialImage.getWidth() * ratio).intValue();
        int newHeight = new Double(initialImage.getHeight() * ratio).intValue();
        BufferedImage resized = new BufferedImage(newWidth, newHeight, initialImage.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(initialImage, 0, 0, newWidth, newHeight, 0, 0, initialImage.getWidth(),
                initialImage.getHeight(), null);
        g.dispose();
        return resized;
    }

}
