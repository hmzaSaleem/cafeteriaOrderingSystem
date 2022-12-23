package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("-----Menu------\n");
            System.out.println("1. Sign up as Customer");
            System.out.println("2. Login as Customer");
            System.out.println("3. Login as Manager");
            System.out.println("4. Exit Main Menu");
            System.out.println("\nChoose an option");

            choice  = scanner.nextInt();

            switch (choice){
                case 1:
                    Customer c = new Customer();
                    c.addCustomer();
                    break;
                case 2:
                    Customer c1 = new Customer();
                    c1.searchCustomer();
                    break;
                case 3:
                    Manager m1 = new Manager();
                    m1.searchManager();
                    break;
            }

        }while (choice != 4);
    }
}
