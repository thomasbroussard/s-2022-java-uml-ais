package fr.epita.mnist;

import fr.epita.mnist.datamodel.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Launcher {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("java-mnist-quick/resources/mnist_train_sample.csv");

        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        List<Image> imageList = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] split = line.split(",");
            String digit = split[0];
            double[][] pixels = new double[28][28];

            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[i].length; j++) {
                    pixels[i][j] = Double.parseDouble(split[i * pixels.length + j]);
                }
            }
            Image image = new Image();
            image.setDigit(Integer.parseInt(digit));
            image.setPixels(pixels);
            imageList.add(image);
        }
        List<Image> centroids = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            List<Image> imagesForIndex = imageList.stream()
                    .filter(im -> im.getDigit() == index)
                    .collect(Collectors.toList());

            //processing the centroid for this i index
            double[][] centroidForDigit = new double[28][28];
            for (int j = 0; j < imagesForIndex.size(); j++) {
                for (int m = 0; m < 28; m++) {
                    for (int n = 0; n < 28; n++) {
                        double valueAtIndex = imagesForIndex.get(j).getPixels()[m][n];
                        centroidForDigit[m][n] += valueAtIndex / imagesForIndex.size();
                    }
                }
            }
            Image centroid = new Image();
            centroid.setPixels(centroidForDigit);
            centroid.setDigit(i);
            centroids.add(centroid);
        }

    }

    public double distance(double[][] matrixA, double[][] matrixB){
        double distance = 0.0;
        for (int i = 0; i < matrixA.length; i ++){
            for (int j = 0; j < matrixA[i].length; j++){
               distance += Math.abs(matrixA[i][j] - matrixB[i][j]);
            }
        }
        return distance;
    }

}


