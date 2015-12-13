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

        FitnessCalculator fitnessCalculator = new FitnessCalculator();
//        fitnessCalculator.setTargetDna("1111000000000000000000000000000000000000000000000000000000001111");
        fitnessCalculator.setTargetDna("11110000000000000000000000000000000000000000000000000000000011111111000000000000000000000000000000000000000000000000000000001111");

        Generation generation = new Generation(parameters);
        int count = 0;
        printGeneration(generation, count, fitnessCalculator);
        while (fitnessCalculator.getFitness(generation.getFittestIndividual()) < 1 && count < 50) {
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fitnessCalculator);
        }
    }

    private static void printGeneration(Generation generation, int count, FitnessCalculator fitnessCalculator) {
        System.out.println("Generation " + count + " : ");
        Individual fittestIndividual = generation.getFittestIndividual();
        System.out.println("Fittest Individual : " + fittestIndividual);
        System.out.println("Fitness : " + fitnessCalculator.getFitness(fittestIndividual));
        System.out.println();
    }

}
