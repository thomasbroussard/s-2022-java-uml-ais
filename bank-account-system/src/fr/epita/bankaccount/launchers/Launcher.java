package fr.epita.bankaccount.launchers;

import fr.epita.bankaccount.datamodel.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Launcher {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("hello!");

        Customer customer = new Customer("thomas", "paris");

        System.out.println(customer.getName());
        System.out.println(customer.getAddress());


        File file = new File("./bank-account-system/customers.csv");

        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        List<Customer> customerList = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            System.out.println("name:" + parts[0]);
            System.out.println("address:" + parts[1]);
            Customer currentCustomer = new Customer(parts[0], parts[1]);
            currentCustomer.setAge(Double.parseDouble(parts[2]));
            customerList.add(currentCustomer);
        }


        double[][] matrix = new double[customerList.size()][2];

        Map<String,Integer> cityEncoding  = new LinkedHashMap<>();

        for (int i = 0; i < customerList.size(); i ++){
            Customer currentCustomer = customerList.get(i);
            double[] customerVector = new double[2];
            Integer encodedCityValue = cityEncoding.get(currentCustomer.getAddress());
            if (encodedCityValue == null){
                encodedCityValue = cityEncoding.size();
                cityEncoding.put(currentCustomer.getAddress(), encodedCityValue);
            }
            customerVector[0] = encodedCityValue;
            customerVector[1] = currentCustomer.getAge();
            matrix[i] = customerVector;
        }

        System.out.println(cityEncoding);
        System.out.println(Arrays.deepToString(matrix));

    }
}
