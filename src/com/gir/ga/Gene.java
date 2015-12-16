package com.gir.ga;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Vlad on 13-Dec-15.
 */
public class Gene implements MutateableEntity{

    private List<Point> points;
    private Color color;

    public Gene(List<Point> points, Color color) {
        this.points = points;
        this.color = color;
    }

    @Override
    public void mutate(GAParameters parameters) {
        Random random = new Random();
        mutateColor(random, parameters);
        mutatePoints(random, parameters);
        performPointCountMutation(parameters);
    }

    public Gene clone() {
        return new Gene(new ArrayList<Point>(points), color);
    }

    private void performPointCountMutation(GAParameters parameters) {
        Random random = new Random();
        IndividualGenerator generator = new IndividualGenerator();
        if (points.size() < parameters.getMaxGeneSize() && random.nextDouble() <= parameters.getMutationRatio()) {
            points.add(generator.generateRandomPoint(parameters));
        }
        if (points.size() > 1 && random.nextDouble() <= parameters.getMutationRatio()) {
            points.remove(random.nextInt(points.size()));
        }
    }

    private void mutatePoints(Random random, GAParameters parameters) {
        for (int i=0; i<points.size(); i++) {
            int x = points.get(i).getX();
            int y = points.get(i).getY();
            boolean changed =false;
            if (random.nextDouble() <= parameters.getMutationRatio()) {
                x = getNewCoordinate(random, parameters, x, parameters.getTargetImage().getWidth()+1);
                changed = true;
            }
            if (random.nextDouble() <= parameters.getMutationRatio()) {
                y = getNewCoordinate(random, parameters, x, parameters.getTargetImage().getHeight()+1);
                changed = true;
            }
            if (changed) {
                points.set(i, new Point(x, y));
            }
        }
    }

    private int getNewCoordinate(Random random, GAParameters parameters, int previousCoordinate, int limit) {
        int newCoordinate = previousCoordinate + getMutation(random, parameters, limit);
        if (newCoordinate < 0) {
            return limit + newCoordinate;
        } else {
            return newCoordinate % limit;
        }
    }

    private void mutateColor(Random random, GAParameters parameters) {
        int newRed = color.getRed();
        int newGreen = color.getGreen();
        int newBlue = color.getBlue();
        int newAlpha = color.getAlpha();
        boolean changed = false;
        if (random.nextDouble() <= parameters.getMutationRatio()) {
            newRed = getNewColorProperty(random, parameters, color.getRed());
            changed = true;
        }
        if (random.nextDouble() <= parameters.getMutationRatio()) {
            newGreen = getNewColorProperty(random, parameters, color.getGreen());
            changed = true;
        }
        if (random.nextDouble() <= parameters.getMutationRatio()) {
            newBlue = getNewColorProperty(random, parameters, color.getBlue());
            changed = true;
        }
        if (random.nextDouble() <= parameters.getMutationRatio()) {
            newAlpha = getNewColorProperty(random, parameters, color.getAlpha());
            changed = true;
        }
        if (changed) {
            color = new Color(newRed, newGreen, newBlue, newAlpha);
        }
    }

    private int getNewColorProperty(Random random, GAParameters parameters, int previousColorProperty) {
        int newColorProperty = previousColorProperty + getMutation(random, parameters, 255);
        if (newColorProperty < 0) {
            return 255 + newColorProperty;
        } else {
            return newColorProperty % 255;
        }
    }

    private int getMutation(Random random, GAParameters parameters, int limit) {
        int maxMutation = parameters.getMaxMutation(limit);
        return random.nextInt(maxMutation * 2) - maxMutation;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }
}
