package com.gir.ga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class Matchmaker {

    public Match getMatch(Generation generation, GAParameters parameters) {
        List<Individual> population = new ArrayList<Individual>(generation.getPopulation());
        Individual firstIndividual = pickIndividual(population, parameters);
        population.remove(firstIndividual);
        Individual secondIndividual = pickIndividual(population, parameters);
        return new Match(firstIndividual, secondIndividual);
    }

    private Individual pickIndividual(List<Individual> population, GAParameters parameters) {
        List<Individual> sample = new ArrayList<Individual>();
        for (int i=0; i<parameters.getMatchSampleSize(); i++) {
            int randomIndex = (int) (Math.random() * population.size());
            sample.add(population.get(randomIndex));
        }
        Generation sampleGeneration = new Generation(sample);
        return sampleGeneration.getFittestIndividual();
    }

}
