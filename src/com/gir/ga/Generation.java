package com.gir.ga;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class Generation {

    private List<Individual> population;

    public Generation(GAParameters parameters) {
        IndividualGenerator generator = new IndividualGenerator();
        population = new ArrayList<Individual>();
        for (int i=0; i<parameters.getPopulationSize(); i++) {
            population.add(generator.generateRandomIndividual(parameters));
        }
    }

    public Generation(List<Individual> population) {
        this.population = population;
    }

    public Generation(Generation previousGeneration, GAParameters parameters) {
        Evolution evolution = new Evolution();
        this.population = evolution.generateNextGeneration(previousGeneration, parameters);
    }

    public Individual getFittestIndividual(GAParameters parameters) {
        long minError = parameters.getMaxSumSquaredError();
        Individual fittestIndividual = null;
        for (Individual individual : population) {
            long error = individual.getFitness(parameters);
            if (error < minError) {
                minError = error;
                fittestIndividual = individual;
            }
        }
        return fittestIndividual;
    }

    public List<Individual> getPopulation() {
        return population;
    }
}