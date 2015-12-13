package com.gir.ga;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class FitnessCalculator {

    private static byte[] targetDna;

    public void setTargetDna(byte[] targetDna) {
        this.targetDna = targetDna;
    }

    public void setTargetDna(String targetDna) {
        this.targetDna = new byte[targetDna.length()];
        for (int i=0; i<targetDna.length(); i++) {
            if (targetDna.charAt(i) == '1') {
                this.targetDna[i] = 1;
            } else {
                this.targetDna[i] = 0;
            }
        }
    }

    public double getFitness(Individual individual) {
        int accurateGeneCount = 0;
        for (int i=0; i<targetDna.length; i++) {
            if (individual.getGene(i) == targetDna[i]) {
                accurateGeneCount++;
            }
        }
        return (double)accurateGeneCount / targetDna.length;
    }

}
