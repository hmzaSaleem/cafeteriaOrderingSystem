package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Manager  {
    int managerID;

    void searchManager(){
        System.out.println("Enter Username:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Enter Password:");
        String pass = in.nextLine();

        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("Manager.txt")));
            String line;
            int correct = 0;
            while((line = br.readLine()) != null){
                if(line.contains(name)){
                    correct++;
                    String[] tokens= line.split(";");
                    if(tokens[1].equals(name) && tokens[2].equals(pass)){
                        System.out.println("\nLogin Successful\n");
                        menu();
                    }
                    else
                        System.out.println("Incorrect Password");
                }
            }
            if(correct == 0)
                System.out.println("Username not found");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            System.out.println("-----Manager Menu------\n");
            System.out.println("1. Show Customers info");
            System.out.println("2. Show Items info");
            System.out.println("3. Show Orders info");
            System.out.println("4. Show Managers info");
            System.out.println("5. Exit Manager Menu");
            System.out.println("\nChoose an option");

            choice =  scanner.nextInt();
            switch(choice){
                case 1:
                    CustomerInfo();
                    break;
                case 2:
                    ItemsInfo();
                    break;
                case 3:
                    OrdersInfo();
                    break;
                case 4:
                    ManagerInfo();
                    break;
            }
        }while(choice != 5);
    }

    public void CustomerInfo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader( new File("Customers.txt")));
            String line;
            System.out.println("ID\t\tName\t\tPassword");
            while ((line = br.readLine()) != null) {
                    String[] tokens= line.split(";");
                    System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t" + tokens[2]);
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void ItemsInfo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader( new File("ItemsCopy.txt")));
            String line;
            System.out.println("Sr.\t\tItem\t\tPrice($)");
            while ((line = br.readLine()) != null) {
                String[] tokens= line.split(";");
                System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t" + tokens[2]);
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void OrdersInfo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader( new File("Orders.txt")));
            String line;
            System.out.println("Sr.\t\tItem\t\tPrice($)\t\tStatus");
            while ((line = br.readLine()) != null) {
                String[] tokens= line.split(";");
                System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t" + tokens[2]+ "\t\t" + tokens[3]);
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void ManagerInfo(){
        try {
            BufferedReader br = new BufferedReader(new FileReader( new File("Manager.txt")));
            String line;
            System.out.println("ID\t\tName\t\tPassword");
            while ((line = br.readLine()) != null) {
                String[] tokens= line.split(";");
                System.out.println(tokens[0] + "\t\t" + tokens[1] + "\t\t" + tokens[2]);
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
