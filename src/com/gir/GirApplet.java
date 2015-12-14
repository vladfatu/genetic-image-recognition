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

        GAParameters parameters = new GAParameters();
        parameters.setDnaLength(30);
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

        Generation generation = new Generation(parameters);
        int count = 0;
        Individual fittestIndividual = generation.getFittestIndividual(parameters);
        long fitness = fitnessCalculator.getFitness(fittestIndividual, parameters);
        printGeneration(generation, count, fittestIndividual, fitness);
        while (fitnessCalculator.getFitness(generation.getFittestIndividual(parameters), parameters) > 0 && count < 50000) {
            fittestIndividual = generation.getFittestIndividual(parameters);
            fitness = fitnessCalculator.getFitness(fittestIndividual, parameters);
            generation = new Generation(generation, parameters);
            count++;
            printGeneration(generation, count, fittestIndividual, fitness);
            g.drawImage(fittestIndividual.getImage(parameters), 300, 0, null);
        }

    }

    private void printGeneration(Generation generation, int count, Individual fittestIndividual, long fitness) {
        System.out.println("Generation " + count + " : ");
        System.out.println("Fittest Individual : " + fittestIndividual);
        System.out.println("Fitness : " + fitness);
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