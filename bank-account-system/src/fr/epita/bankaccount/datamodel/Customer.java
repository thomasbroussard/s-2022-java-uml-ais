package fr.epita.bankaccount.datamodel;

import java.util.List;

public class Customer {
    private String name;
    private String address;

    private List<Account> accountList;

    public Customer(String name, String address){
        this.name = name;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null){
            return;
        }
        this.name = name;
    }
}
