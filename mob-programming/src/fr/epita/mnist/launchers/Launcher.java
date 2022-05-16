package fr.epita.mnist.launchers;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Scanner;

public class Launcher {


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("mob-programming/mnist_train_sample.csv");


        Scanner scanner = new Scanner(file);
        scanner.nextLine();

        while(scanner.hasNext()){
            double[][] matrix;
            double[] flattenMatrix = new double[784];
            String line = scanner.nextLine();
            String[] flattenMatrixAsString = line.split(",");
            int i = Integer.parseInt(flattenMatrixAsString[0]);
            //Exercise 1:
            // do a for loop over the flattenMatrixAsString
            // convert every cell from String to double
            // store it in the "flattenMatrix" variable
        }

    }
}
