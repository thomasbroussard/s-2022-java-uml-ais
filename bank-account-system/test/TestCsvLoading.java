import fr.epita.bankaccount.datamodel.Account;
import fr.epita.bankaccount.datamodel.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TestCsvLoading {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("./bank-account-system/customers.csv");
        if (file.exists()) {
            System.out.println("csv file was found!");
        } else {
            System.out.println("csv file does not exist");
            return;
        }
        Scanner scanner = null;

        scanner = new Scanner(file);
        scanner.nextLine();
        List<Customer> customerList = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            System.out.println("name:" + parts[0]);
            System.out.println("address:" + parts[1]);
            Customer customer = new Customer(parts[0], parts[1]);
            customer.setAge(Double.parseDouble(parts[2]));
            customerList.add(customer);
        }

        System.out.println(customerList.size());
        double averageAge = 0;
        for(Customer customer :customerList){
            averageAge += customer.getAge();
        }
        averageAge = averageAge / customerList.size();
        System.out.println(" average age :" +  averageAge);

//        OptionalDouble averageAge = customerList.stream()
//                .mapToDouble(Customer::getAge)
//                .average();


    }
}
