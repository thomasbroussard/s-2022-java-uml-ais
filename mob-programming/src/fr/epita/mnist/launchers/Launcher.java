package fr.epita.mnist.launchers;

import fr.epita.mnist.datamodel.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Launcher {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("mob-programming/resources/mnist_train_sample.csv");


        Scanner scanner = new Scanner(file);
        scanner.nextLine();

        List<Image> imageList = new ArrayList<>();

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            String[] flattenMatrixAsString = line.split(",");

            double[] flattenMatrix = new double[784];
            //Exercise 1:
            // do a for loop over the flattenMatrixAsString
            // convert every cell from String to double
            // store it in the "flattenMatrix" variable
            for ( int i = 1; i < flattenMatrixAsString.length ; i++){
                flattenMatrix[i-1] = Integer.parseInt(flattenMatrixAsString[i]);
            }

            Image image = new Image(Integer.valueOf(flattenMatrixAsString[0]), flattenMatrix);
            imageList.add(image);
        }

        Image image = imageList.get(4);
        double[][] pixels = image.getPixels();
        System.out.println(image.getDigit());
        for (int i = 0 ; i < pixels.length; i ++){
            for (int j = 0; j < pixels[i].length; j++){
                double v = pixels[i][j];
                if (v > 127){
                    System.out.print("xx");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

    }
}
