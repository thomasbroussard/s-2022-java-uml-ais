package fr.epita.bankaccount.launchers;

import fr.epita.bankaccount.datamodel.Customer;

public class Launcher {

    public static void main(String[] args) {
        System.out.println("hello!");

        Customer customer = new Customer("thomas", "paris");

        System.out.println(customer.getName());
        System.out.println(customer.getAddress());


    }
}
