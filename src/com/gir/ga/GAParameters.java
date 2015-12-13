package com.gir.ga;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class GAParameters {

    private int dnaLength;
    private int populationSize;
    private boolean elitism;
    private int matchSampleSize;

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
}
