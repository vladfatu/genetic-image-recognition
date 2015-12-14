package com.gir;

import com.gir.ga.FitnessCalculator;
import com.gir.ga.GAParameters;
import com.gir.ga.Generation;
import com.gir.ga.Individual;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class GirTest {

    public static void main(String[] args) {
        GAParameters parameters = new GAParameters();
        parameters.setDnaLength(128);
        parameters.setElitism(true);
        parameters.setPopulationSize(50);
        parameters.setMatchSampleSize(5);
        parameters.setMutationRatio(0.015);

        FitnessCalculator fitnessCalculator = new FitnessCalculator();
//        fitnessCalculator.setTargetDna("1111000000000000000000000000000000000000000000000000000000001111");
//        fitnessCalculator.setTargetDna("11110000000000000000000000000000000000000000000000000000000011111111000000000000000000000000000000000000000000000000000000001111");
//        parameters.setMaxSumSquaredError(fitnessCalculator.getMaxSumSquaredError());

        Generation generation = new Generation(parameters);
        int count = 0;
        printGeneration(generation, count, fitnessCalculator, parameters);
        while (fitnessCalculator.getFitness(generation.getFittestIndividual(parameters), parameters) < 1 && count < 50000) {
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fitnessCalculator, parameters);
        }
    }

    private static void printGeneration(Generation generation, int count, FitnessCalculator fitnessCalculator, GAParameters parameters) {
        System.out.println("Generation " + count + " : ");
        Individual fittestIndividual = generation.getFittestIndividual(parameters);
        System.out.println("Fittest Individual : " + fittestIndividual);
        System.out.println("Fitness : " + fitnessCalculator.getFitness(fittestIndividual, parameters));
        System.out.println();
    }

}
