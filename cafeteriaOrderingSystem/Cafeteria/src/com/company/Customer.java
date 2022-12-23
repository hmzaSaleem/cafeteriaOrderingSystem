package com.company;

import java.io.*;
import java.util.Scanner;

public class Customer{
    int userID;

    void addCustomer(){
        int max = 1000;
        int min = 1;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        File fout = null;
        BufferedWriter bw = null;
        try{
            fout = new File("Customers.txt");
            FileOutputStream fos = new FileOutputStream(fout,true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));

            userID = (int)(Math.random()*(max-min+1)+min);

            bw.write(userID + ";" + username + ";" + password);
            bw.newLine();
            System.out.println("\n------Customer Signup Succesfull------\n");

        }
        catch (Exception e){
            System.out.println(e);
        }
        finally {

            try {
                bw.close();
            }
            catch (Exception e){
                System.out.println(e);
            }

        }

    }

    void searchCustomer(){
        System.out.println("Enter Username:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        System.out.println("Enter Password:");
        String pass = in.nextLine();

        try{
            File file=new File("Customers.txt");
            FileReader fis=new FileReader(file);     //opens a connection to an actual file
            BufferedReader br = new BufferedReader(fis);
            String line;
            int correct = 0;
            while((line = br.readLine()) != null){
                if(line.contains(name)){
                    correct++;
                    String[] tokens= line.split(";");
                    if(tokens[1].equals(name) && tokens[2].equals(pass)){
                        System.out.println("\nLogin Successful\n");
                        Orders o = new Orders();
                        int choice = 0;
                        do{
                            choice = o.OrdersMenu();
                            if(choice == 1)
                                o.CreateOrder();
                            else if(choice == 2)
                                o.CompleteOrders();
                            else if(choice == 3)
                                o.InCompleteOrders();
                            else if(choice == 4)
                                o.AllOrders();
                        }while(choice != 5);
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
}

