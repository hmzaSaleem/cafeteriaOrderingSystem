package com.company;

import java.io.*;
import java.util.Scanner;

public class Orders {

    public int OrdersMenu(){
        System.out.println("-----Orders Menu------\n");
        System.out.println("1. Create Order");
        System.out.println("2. Show Complete Orders");
        System.out.println("3. Show Incomplete Orders");
        System.out.println("4. Show All Orders");
        System.out.println("5. Exit Orders Menu");
        System.out.println("\nChoose an option");

        Scanner scanner = new Scanner(System.in);
        int choice  = scanner.nextInt();
        return choice;
    }

    public void CreateOrder() {
        String item = "";
        try {
            File file = new File("Items.txt");
            FileReader fis = new FileReader(file);
            BufferedReader br = new BufferedReader(fis);
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("\nChoose name of an item(Name should be Exact)");
            Scanner in = new Scanner(System.in);
            item = in.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            File file2 = new File("ItemsCopy.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            BufferedReader br = new BufferedReader(fis2);
            String line;
            int check = 0;
            while ((line = br.readLine()) != null) {
                if (line.contains(item)) {
                    check++;
                    String[] tokens= line.split(";");
                    addOrder(tokens);
                }
            }
            if(check == 0){
                System.out.println("Please select from available items");
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }


    public void addOrder(String[] tokens) {
        try {
            File fout = new File("Orders.txt");
            FileOutputStream fos = new FileOutputStream(fout, true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(tokens[0] + ";" + tokens[1] + ";" + tokens[2] + ";" + "Incomplete");
            bw.newLine();
            System.out.println("\n------Order Placed Successfully, for recieving your Order and Payment Please Check Incomplete Orders List------\n");
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void CompleteOrders(){
        BufferedReader br = null;
        try {
            File file2 = new File("Orders.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            br = new BufferedReader(fis2);
            String line;
            System.out.println("Item\t\tPrice\t\tStatus");
            while ((line = br.readLine()) != null) {
                if (line.contains("Complete")) {
                    String[] tokens= line.split(";");
                    System.out.println(tokens[1] + "\t\t" + tokens[2] + "\t\t" + tokens[3]);
                }
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void InCompleteOrders(){
        try {
            File file2 = new File("Orders.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            BufferedReader br = new BufferedReader(fis2);
            String line;
            System.out.println("Item\t\tPrice\t\tStatus");
            while ((line = br.readLine()) != null) {
                if (line.contains("Incomplete")) {
                    String[] tokens= line.split(";");
                    System.out.println(tokens[1] + "\t\t" + tokens[2] + "\t\t" + tokens[3]);
                }
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
        System.out.println("\nPlease select your item for Payment");
        Scanner scanner = new Scanner(System.in);
        String item = scanner.nextLine();
        System.out.println("Please Pay the Required Amount");
        int pay = scanner.nextInt();
        ClearOrder(item,pay);
    }

    public void ClearOrder(String item, int pay){
        BufferedReader br = null;
        try {
            File file2 = new File("Orders.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            br = new BufferedReader(fis2);
            String line;
            int count = 0;
            int Price = 0;
            while ((line = br.readLine()) != null) {
                if (line.contains(item)) {
                    count++;
                    String[] tokens= line.split(";");
                    Price = Integer.parseInt(tokens[2]);
                    if(Price == pay){
                        System.out.println("Payment Cleared\nChange $0");
                        tokens[3] = "Complete";
                        Update(tokens,item);
                        break;
                    }
                    else if(Price > pay){
                        System.out.println("Insufficient Payment! Come Again");
                        break;
                    }
                    else{
                        int change = pay - Price;
                        System.out.println("Payment Cleared\nChange $"+change);
                        tokens[3] = "Complete";
                        Update(tokens,item);
                        break;
                    }
                }
            }
            if(count == 0)
                System.out.println("Item not Found! Please place order first");
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public void Update(String[] tokens, String item){
        try{
            File file2 = new File("Orders.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            BufferedReader br = new BufferedReader(fis2);
            File fout = new File("temp.txt");
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(item)) {
                    bw.write(tokens[0] + ";" + tokens[1] + ";" + tokens[2] + ";" + tokens[3]);
                    bw.newLine();
                }
                else {
                    bw.write(line);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            FileRenew();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public void FileRenew(){
        try{
            File file2 = new File("temp.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            BufferedReader br = new BufferedReader(fis2);
            File fout = new File("Orders.txt");
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            String line;
            while ((line = br.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
            }
            br.close();
            bw.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public void AllOrders(){
        try {
            File file2 = new File("Orders.txt");
            FileReader fis2 = new FileReader(file2);     //opens a connection to an actual file
            BufferedReader br = new BufferedReader(fis2);
            String line;
            System.out.println("Item\t\tPrice\t\tStatus");
            while ((line = br.readLine()) != null) {
                String[] tokens= line.split(";");
                System.out.println(tokens[1] + "\t\t" + tokens[2] + "\t\t" + tokens[3]);
            }
            br.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
