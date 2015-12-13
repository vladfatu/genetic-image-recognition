package com.gir.ga;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class IndividualGenerator {

    public Individual generateRandomIndividual(GAParameters parameters) {
        byte[] dna = new byte[parameters.getDnaLength()];
        for (int i=0; i<parameters.getDnaLength(); i++) {
            if (Math.random() > 0.5) {
                dna[i] = 1;
            } else {
                dna[i] = 0;
            }
        }
        return new Individual(dna);
    }

    public Individual generateIndividualThroughCrossover(Match match, GAParameters parameters) {
        byte[] newDna = new byte[parameters.getDnaLength()];
        for (int i = 0; i < parameters.getDnaLength(); i++) {
            if (Math.random() <= 0.5) {
                newDna[i] = match.getFirstIndividual().getGene(i);
            } else {
                newDna[i] = match.getSecondIndividual().getGene(i);
            }
        }
        return new Individual(newDna);
    }

}
