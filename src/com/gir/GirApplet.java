package com.gir;

import com.gir.ga.FitnessCalculator;
import com.gir.ga.GAParameters;
import com.gir.ga.Generation;
import com.gir.ga.Individual;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Vlad on 13-Dec-15.
 */
public class GirApplet extends JApplet {

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        long initialTimestamp = System.currentTimeMillis();

        GAParameters parameters = new GAParameters();
        parameters.setDnaLength(2);
        parameters.setMaxGeneSize(20);
        parameters.setElitism(true);
        parameters.setPopulationSize(2);
        parameters.setMatchSampleSize(1);
        parameters.setMutationRatio(0.015);
        parameters.setMaxMutation(0.15);
        parameters.setTargetImage(loadImage());

        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        parameters.setMaxSumSquaredError(fitnessCalculator.getMaxSumSquaredError(parameters.getTargetImage()));

        g.drawImage(parameters.getTargetImage(), 0, 0, null);

        Generation generation;
        Individual fittestIndividual;
        int count;
        long fitness;

        parameters.setCurrentImageScale(0.1);
        parameters.setScaledTargetImage(fitnessCalculator.getScaledImage(parameters.getTargetImage(), parameters.getCurrentImageScale()));

        generation = new Generation(parameters);
        count = 0;
        fittestIndividual = generation.getFittestIndividual(parameters);
        fitness = fittestIndividual.getFitness(parameters);
        printGeneration(generation, count, fittestIndividual, fitness, 0);
        while (generation.getFittestIndividual(parameters).getFitness(parameters) > 0 && count < 10000) {
            long timestamp = System.currentTimeMillis();
            fittestIndividual = generation.getFittestIndividual(parameters);
            fitness = fittestIndividual.getFitness(parameters);
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fittestIndividual, fitness, System.currentTimeMillis() - timestamp);
            if (count % 500 == 0) {
                g.drawImage(fittestIndividual.getImage(parameters), 300, 0, null);
            }
        }

        parameters.setCurrentImageScale(0.25);
        parameters.setScaledTargetImage(fitnessCalculator.getScaledImage(parameters.getTargetImage(), parameters.getCurrentImageScale()));

        generation.scale(2.5);
        generation = new Generation(generation, parameters);
//        generation = new Generation(parameters);

        count = 0;
        fittestIndividual = generation.getFittestIndividual(parameters);
        fitness = fittestIndividual.getFitness(parameters);
        printGeneration(generation, count, fittestIndividual, fitness, 0);
        while (generation.getFittestIndividual(parameters).getFitness(parameters) > 0 && count < 2000) {
            long timestamp = System.currentTimeMillis();
            fittestIndividual = generation.getFittestIndividual(parameters);
            fitness = fittestIndividual.getFitness(parameters);
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fittestIndividual, fitness, System.currentTimeMillis() - timestamp);
            if (count % 500 == 0) {
                g.drawImage(fittestIndividual.getImage(parameters), 300, 0, null);
            }
        }

        parameters.setCurrentImageScale(0.5);
        parameters.setScaledTargetImage(fitnessCalculator.getScaledImage(parameters.getTargetImage(), parameters.getCurrentImageScale()));

        generation.scale(2);
        generation = new Generation(generation, parameters);
//        generation = new Generation(parameters);

        count = 0;
        fittestIndividual = generation.getFittestIndividual(parameters);
        fitness = fittestIndividual.getFitness(parameters);
        printGeneration(generation, count, fittestIndividual, fitness, 0);
        while (generation.getFittestIndividual(parameters).getFitness(parameters) > 0 && count < 2000) {
            long timestamp = System.currentTimeMillis();
            fittestIndividual = generation.getFittestIndividual(parameters);
            fitness = fittestIndividual.getFitness(parameters);
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fittestIndividual, fitness, System.currentTimeMillis() - timestamp);
            if (count % 500 == 0) {
                g.drawImage(fittestIndividual.getImage(parameters), 300, 0, null);
            }
        }

        parameters.setCurrentImageScale(1);
        parameters.setScaledTargetImage(parameters.getTargetImage());

        generation.scale(2);
        generation = new Generation(generation, parameters);
//        generation = new Generation(parameters);

        count = 0;
        fittestIndividual = generation.getFittestIndividual(parameters);
        fitness = fittestIndividual.getFitness(parameters);
        printGeneration(generation, count, fittestIndividual, fitness, 0);
        while (generation.getFittestIndividual(parameters).getFitness(parameters) > 0 && count < 50000) {
            long timestamp = System.currentTimeMillis();
            fittestIndividual = generation.getFittestIndividual(parameters);
            fitness = fittestIndividual.getFitness(parameters);
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fittestIndividual, fitness, System.currentTimeMillis() - timestamp);
            if (count % 500 == 0) {
                g.drawImage(fittestIndividual.getImage(parameters), 300, 0, null);
            }
        }

//        g.drawImage(fittestIndividual.getImage(parameters, 10), 300, 0, null);
//        g.drawImage(fitnessCalculator.getScaledImage(fittestIndividual.getImage(parameters, 1), 0.1), 300, 300, null);
        System.out.println();
        System.out.println();
        System.out.println("Final time : " + (System.currentTimeMillis() - initialTimestamp));
    }

    private void printGeneration(Generation generation, int count, Individual fittestIndividual, long fitness, long timeSpent) {
        System.out.println("Generation " + count + " : ");
        System.out.println("Fittest Individual : " + fittestIndividual);
        System.out.println("Fitness : " + fitness);
        System.out.println("Took: " + timeSpent + " millis");
        System.out.println();
    }

    private BufferedImage loadImage() {
        URL imageURL = this.getClass().getClassLoader().getResource("ml.png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
