package com.bring.lab002;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

    @Autowired
    private Address address;

    private Salary salary;

    private Company company;



    @Autowired
    public void setCompany(Company company){
        this.company=company;
    }

    public User(Salary salary){
        this.salary=salary;
    }


    public void printUserDetails(){
        System.out.println("User Details : User1,1999,developer");
        salary.printSalary();
        address.printAddress();
        company.printCompany();

    }

}
