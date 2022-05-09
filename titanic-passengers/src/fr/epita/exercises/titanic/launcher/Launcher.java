package fr.epita.exercises.titanic.launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) throws IOException {
        File file = new File("titanic-passengers/titanic.csv");

        List<String> stringList = Files.readAllLines(file.toPath());


        stringList.remove(0);
        System.out.println(stringList);
        for (String line : stringList){
            String[] parts = line.split(",");
            System.out.println("pclass: " + parts[2]);
        }
    }




}
