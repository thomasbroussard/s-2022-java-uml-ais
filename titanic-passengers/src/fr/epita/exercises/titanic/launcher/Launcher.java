package fr.epita.exercises.titanic.launcher;

import fr.epita.exercises.titanic.datamodel.Passenger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Launcher {

    public static void main(String[] args) throws IOException {
        File file = new File("titanic-passengers/titanic.csv");

        List<String> stringList = Files.readAllLines(file.toPath());


        stringList.remove(0);
        System.out.println(stringList);
        List<Passenger> passengers = new ArrayList<>();

        List<String> faultyPassengerIds = new ArrayList<>();
        for (String line : stringList){
            String[] parts = line.split(",");
            System.out.println("pclass: " + parts[2]);

            try {
                Passenger p = new Passenger(Integer.parseInt(parts[2])
                        , Integer.parseInt(parts[1])
                        , Integer.parseInt(parts[0])
                        , parts[3]
                        , Integer.parseInt(parts[4])
                        , parts[10]);
                passengers.add(p);
            }catch (Exception e){
                e.printStackTrace();
                faultyPassengerIds.add(parts[0]);
            }
        }
        System.out.println(passengers);
        System.out.println("faulty passengers size:" + faultyPassengerIds.size());

        Map<String, Integer> sexEncoding = new LinkedHashMap<>();
        sexEncoding.put("male", 0);
        sexEncoding.put("female",1);

        Map<String, Integer> embarkedEncoding = new LinkedHashMap<>();
        embarkedEncoding.put("S", 0);
        embarkedEncoding.put("Q",1);
        embarkedEncoding.put("C",2);

        double[][] passengerMatrix = new double[passengers.size()][];
        int i = 0;
        for (Passenger passenger : passengers){
            double[] passengerAsVector = new double[6];
            passengerAsVector[0] = passenger.getPassengerId();
            passengerAsVector[1] = passenger.getSurvived();
            passengerAsVector[2] = passenger.getPclass();
            passengerAsVector[3] = sexEncoding.get(passenger.getSex());
            passengerAsVector[4] = passenger.getAge();
            passengerAsVector[5] = embarkedEncoding.get(passenger.getEmbarked());
            passengerMatrix[i] = passengerAsVector;
            i++;
        }
        System.out.println(Arrays.deepToString(passengerMatrix));


    }




}
