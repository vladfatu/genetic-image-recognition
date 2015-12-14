package com.gir.ga;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Vlad on 12-Dec-15.
 */
public class IndividualGenerator {

    public Individual generateRandomIndividual(GAParameters parameters) {
        List<Gene> dna= new ArrayList<Gene>();
        for (int i=0; i<parameters.getDnaLength(); i++) {
            dna.add(generateRandomGene(parameters));
        }
        return new Individual(dna);
    }

    public Individual generateIndividualThroughCrossover(Match match, GAParameters parameters) {
        int firstDnaSize = match.getFirstIndividual().getDna().size();
        int secondDnaSize = match.getSecondIndividual().getDna().size();
        int maxDna = firstDnaSize;
        if (secondDnaSize > maxDna) {
            maxDna = secondDnaSize;
        }
        List<Gene> newDna = new ArrayList<Gene>();
        for (int i = 0; i < maxDna; i++) {
            if (Math.random() <= 0.5) {
                if (firstDnaSize > i) {
                    newDna.add(match.getFirstIndividual().getGene(i).clone());
                }
            } else {
                if (secondDnaSize > i) {
                    newDna.add(match.getSecondIndividual().getGene(i).clone());
                }
            }
        }
        return new Individual(newDna);
    }

    public Gene generateRandomGene(GAParameters parameters) {
        Random random = new Random();
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(256));
        int geneSize = random.nextInt(parameters.getMaxGeneSize()/4);
        List<Point> points = new ArrayList<Point>();
        for (int i=0; i<geneSize; i++) {
            points.add(generateRandomPoint(parameters));
        }
        return new Gene(points, color);
    }

    public Point generateRandomPoint(GAParameters parameters) {
        Random random = new Random();
        int x = random.nextInt(parameters.getTargetImage().getWidth());
        int y = random.nextInt(parameters.getTargetImage().getHeight());
        return new Point(x, y);
    }

}
