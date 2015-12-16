package com.gir.ga;

import java.awt.image.BufferedImage;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class GAParameters {

    private int dnaLength;
    private int maxGeneSize;
    private int populationSize;
    private boolean elitism;
    private int matchSampleSize;
    private BufferedImage targetImage;
    private BufferedImage scaledTargetImage;
    private double mutationRatio;
    private double maxMutation;
    private long maxSumSquaredError;
    private double currentImageScale;

    public int getDnaLength() {
        return dnaLength;
    }

    public void setDnaLength(int dnaLength) {
        this.dnaLength = dnaLength;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public boolean isElitism() {
        return elitism;
    }

    public void setElitism(boolean elitism) {
        this.elitism = elitism;
    }

    public int getMatchSampleSize() {
        return matchSampleSize;
    }

    public void setMatchSampleSize(int matchSampleSize) {
        this.matchSampleSize = matchSampleSize;
    }

    public BufferedImage getTargetImage() {
        return targetImage;
    }

    public void setTargetImage(BufferedImage targetImage) {
        this.targetImage = targetImage;
    }

    public double getMutationRatio() {
        return mutationRatio;
    }

    public void setMutationRatio(double mutationRatio) {
        this.mutationRatio = mutationRatio;
    }

    public long getMaxSumSquaredError() {
        return maxSumSquaredError;
    }

    public void setMaxSumSquaredError(long maxSumSquaredError) {
        this.maxSumSquaredError = maxSumSquaredError;
    }

    public int getMaxMutation(int maxValue) {
        return (int) (maxValue * maxMutation);
    }

    public void setMaxMutation(double maxMutation) {
        this.maxMutation = maxMutation;
    }

    public int getMaxGeneSize() {
        return maxGeneSize;
    }

    public void setMaxGeneSize(int maxGeneSize) {
        this.maxGeneSize = maxGeneSize;
    }

    public BufferedImage getScaledTargetImage() {
        return scaledTargetImage;
    }

    public void setScaledTargetImage(BufferedImage scaledTargetImage) {
        this.scaledTargetImage = scaledTargetImage;
    }

    public double getCurrentImageScale() {
        return currentImageScale;
    }

    public void setCurrentImageScale(double currentImageScale) {
        this.currentImageScale = currentImageScale;
    }
}
