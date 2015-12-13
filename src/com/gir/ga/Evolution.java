package com.gir.ga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class Evolution {

    public List<Individual> generateNextGeneration(Generation previousGeneration, GAParameters parameters) {
        List<Individual> nextGeneration = new ArrayList<Individual>();
        performElitistStep(previousGeneration, nextGeneration, parameters);
        performCrossoverStep(previousGeneration, nextGeneration, parameters);
        return nextGeneration;
    }

    private void performElitistStep(Generation previousGeneration, List<Individual> nextGeneration, GAParameters parameters) {
        nextGeneration.add(previousGeneration.getFittestIndividual());
    }

    private void performCrossoverStep(Generation previousGeneration, List<Individual> nextGeneration, GAParameters parameters) {
        Matchmaker matchmaker = new Matchmaker();
        IndividualGenerator generator = new IndividualGenerator();
        int matchCount = parameters.getPopulationSize();
        if (parameters.isElitism()) {
            matchCount--;
        }
        for (int i=0; i<matchCount; i++) {
            Match match = matchmaker.getMatch(previousGeneration, parameters);
            nextGeneration.add(generator.generateIndividualThroughCrossover(match, parameters));
        }
    }

}
