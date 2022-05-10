package fr.epita.exercises.titanic.launcher;

import fr.epita.exercises.titanic.datamodel.Passenger;
import org.knowm.xchart.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Launcher {

    private static final Map<String, Integer> sexEncoding = new LinkedHashMap<>();
    static {
        sexEncoding.put("male", 0);
        sexEncoding.put("female", 1);
    }

    private static final Map<String, Integer> embarkedEncoding = new LinkedHashMap<>();
    static {
        embarkedEncoding.put("S", 0);
        embarkedEncoding.put("Q", 1);
        embarkedEncoding.put("C", 2);
    }

    public static void main(String[] args) throws IOException {
        File file = new File("titanic-passengers/titanic.csv");

        List<Passenger> passengers = getPassengers(file);

        double[][] passengerMatrix = getPassengersMatrix(passengers, sexEncoding, embarkedEncoding);

       visualize(passengers);

      //  reconstructPassengersFromMatrix(sexEncoding, embarkedEncoding, passengerMatrix);

    }

    private static void reconstructPassengersFromMatrix(Map<String, Integer> sexEncoding, Map<String, Integer> embarkedEncoding, double[][] passengerMatrix) {
        System.out.println(Arrays.deepToString(passengerMatrix));
        // vector to passenger

        List<Passenger> reconstructedPassengers = new ArrayList<>();
        for (double[] passengerVector : passengerMatrix){

            //vector to passenger
            Passenger passenger = new Passenger(Double.valueOf(passengerVector[2]).intValue(),
                    Double.valueOf(passengerVector[1]).intValue(),
                    Double.valueOf(passengerVector[0]).intValue(),
                    keyFromValue( Double.valueOf(passengerVector[3]).intValue(), sexEncoding),
                    Double.valueOf(passengerVector[4]).intValue(),
                    keyFromValue( Double.valueOf(passengerVector[5]).intValue(), embarkedEncoding));
            reconstructedPassengers.add(passenger);
        }

        System.out.println(reconstructedPassengers.size());
    }

    private static void visualize(List<Passenger> passengers) {
        //from the matrix, we'll extract the age and the survived
        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        List<Double> xDataNotSurvived = new LinkedList<>();
        List<Double> yDataNotSurvived = new LinkedList<>();
        List<Double> xData = new LinkedList<>();
        List<Double> yData = new LinkedList<>();
        int survived = 0;
        int notSurvived = 0;


        for (Passenger passenger: passengers){
            if (passenger.getSurvived() == 0) {
                xDataNotSurvived.add((double) passenger.getSurvived());
                yDataNotSurvived.add((double) passenger.getAge());
                notSurvived += 1;
            } else {
                xData.add((double) passenger.getSurvived());
                yData.add((double) passenger.getAge());
                survived += 1;
            }
        }

        XYSeries seriesNotSurvived = chart.addSeries("not survived", xDataNotSurvived, yDataNotSurvived);
        XYSeries seriesSurvived = chart.addSeries("survived", xData, yData);


        CategoryChart distribution =
                new CategoryChartBuilder()
                        .width(800)
                        .height(600)
                        .title("survived vs not survived")
                        .xAxisTitle("Score")
                        .yAxisTitle("Number")
                        .build();

        distribution.addSeries("distribution", Arrays.asList("survived", "not survived"), Arrays.asList(survived, notSurvived));

        Map<Integer, Long> countOfSurvivedPerPclass = passengers.stream()
                .filter(passenger -> passenger.getSurvived() == 1)
                .collect(Collectors.groupingBy(Passenger::getPclass, Collectors.counting()));

        System.out.println(countOfSurvivedPerPclass);
        Map<Integer, Long> countOfNotSurvivedPerPclass = passengers.stream().filter(passenger -> passenger.getSurvived() == 0)
                .collect(Collectors.groupingBy(Passenger::getPclass, Collectors.counting()));


        CategoryChart distributionPerPClass =
                new CategoryChartBuilder()
                        .width(800)
                        .height(600)
                        .title("survived vs not survived")
                        .xAxisTitle("Class")
                        .yAxisTitle("Number")
                        .build();

        distributionPerPClass.addSeries("survived", Arrays.asList("1st", "2nd","3rd"), new ArrayList(countOfSurvivedPerPclass.values()));
        distributionPerPClass.addSeries("notSurvived", Arrays.asList("1st", "2nd","3rd"), new ArrayList(countOfNotSurvivedPerPclass.values()));
        distributionPerPClass.getStyler().setStacked(true);
        new SwingWrapper(distributionPerPClass).displayChart();
    }

    private static double[][] getPassengersMatrix(List<Passenger> passengers, Map<String, Integer> sexEncoding, Map<String, Integer> embarkedEncoding) {
        double[][] passengerMatrix = new double[passengers.size()][];
        int i = 0;
        for (Passenger passenger : passengers){

            //passenger to vector
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
        return passengerMatrix;
    }

    private static List<Passenger> getPassengers(File file) throws IOException {
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
        return passengers;
    }

    public static String keyFromValue(Integer value, Map<String, Integer> map){
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue().equals(value)){
                return entry.getKey();
            }
        }
        return null;
    }




}
