/**
 * Created by Martin88 on 6/5/17.
 * Piggy Bank program
 * #1
 */

import jdk.nashorn.internal.scripts.JO;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class piggyBankTEST {

    //OBJECTS **********************************************************************

    static piggyBank pb = new piggyBank(); //From class piggyBank
    static BufferedWriter bw; //To write init file
    static BufferedReader br; //To read init file
    static File f = new File(   "init.txt"); //To search init file
    static wlClass[] wl = new wlClass[10]; //For the multiple wls - max10
    static JOptionPane window = new JOptionPane(); //Dialog boxes lol
    static String[] menuOptions = {"EXIT", "ADVANCED","EDIT DATA"};
    static String[] editDataOptions = {"Edit WL Items","Edit Money"};
    static String[] editMoneyOptions = {"Substract Money", "Add Money"};
    static String[] editwlOptions = {"Edit WL Items", "Delete WL Items", "Add WL Items"};
    static String[] nameOrMoney = {"Costs", "Name"};

    //INIT FILE VARIABLES **********************************************************************

    static String initOne; //True or false for boolean init
    static String initUser; //Username
    static String initMoney; //Money Saved
    static String[] initWL = new String[10]; //Name of the wls
    static String[] initWLCost = new String[10]; //Cost per WL Items
    static int[] initWLCostInts = new int[10]; // Cost per WL Items in Integers

    //Variables **********************************************************************

    static boolean init; // LINE 1 IN INIT TEXT FILE
    static int tempInt;
    static String nameUser;  // LINE 2 IN INIT TEXT FILE
    static String tempForParsing;
    static int amountMoney; //LINE 3 IN INIT TEXT FILE
    static boolean wlLoop = true;
    static int cont = 0; //For the 'for' loops
    static int wlMoney;
    static String printData;
    static int menuOption;
    static boolean menuLoop;

    public static void main (String [] args){

        //SEARCHES FOR INIT.TXT FILE, IF FOUND RUNS THE FUNCTIONS, IF NOT, RUNS THE SETUP
        if(f.exists()) {
            readInit();
            variableWrite();
        } else{
            init = false;
        }


        if (init){ //There is an init file with the users data written on it

            window.showMessageDialog(null, "Welcome to PiggyBank", "PiggyBank", JOptionPane.INFORMATION_MESSAGE);

            do {
                menuLoop = true;
            menu();
            switch (menuOption) {

                case -1:// X icon
                    menuLoop = false;
                    System.exit(0);
                    break;

                case 0: //EXIT
                    System.exit(0);
                    break;

                case 1: //ADVANCED
                    break;

                case 2: //EDIT DATA
                    editData();
                    writeInit();
                    break;

                default:
                    break;

            }//Switch
        }while(menuLoop);

        }//IF -> APP HAS HAD BEEN INIT
        else{

            initFunction();
            fillData();
            writeInit();

            do {
                menuLoop = true;
                menu();
                switch (menuOption) {

                    case -1:// X icon
                        menuLoop = false;
                        System.exit(0);
                        break;

                    case 0: //EXIT
                        System.exit(0);
                        break;

                    case 1: //ADVANCED
                        break;

                    case 2: //EDIT DATA
                        editData();
                        writeInit();
                        break;

                    default:
                        break;

                }//Switch
            }while(menuLoop);


        }//ELSE -> APP HASN'T BEEN INIT

    }//main


    //FUNCTIONS **********************************************************************

    public static void variableWrite(){

        //Fills the Object Array
        for(cont = 0; cont<10; cont++){
            wl[cont] = new wlClass();
        }

        //Username
        nameUser = initUser;
        pb.editName(nameUser);

        //SAVED MONEY
        amountMoney = Integer.parseInt(initMoney);
        pb.editMoney(amountMoney);

        //wl NAMES
        for(cont = 0; cont<10; cont++){
            wl[cont].editWLName(initWL[cont]);
        }

        //wl MONEY
        for(cont = 0; cont<10; cont++){
            wl[cont].editWLMoney(initWLCostInts[cont]);
        }

    }

    public static void readInit(){ //Reads init file

        try{

            br = new BufferedReader(new FileReader("init.txt"));
            initOne = br.readLine(); //init boolean
            initUser = br.readLine(); //Username
            initMoney = br.readLine(); //Amount of money saved

            for(cont = 0; cont<10; cont++){ //wl Names
                initWL[cont] = br.readLine();
            }

            for(cont = 0; cont<10; cont++){ //Cost per WL Item
                initWLCost[cont] = br.readLine();
            }

            //Parses money in strings to ints
            for(cont = 0; cont<10; cont++){
                initWLCostInts[cont] = Integer.parseInt(initWLCost[cont]);
            }


        }catch(IOException e){
                e.printStackTrace();
        }

        tempInt = Integer.parseInt(initOne);
        if(tempInt == 1){init = true;}; //Assigns boolean value to the init variable
        if(tempInt == 0){init = false;};

    }//readInit

    public static void writeInit(){

        String money = Integer.toString(pb.showMoney());
        String tempGoaMoney;

      try{
          bw = new BufferedWriter(new FileWriter("init.txt"));
          bw.write("1"); //IF THE APP WAS ALREADY INIT
          bw.newLine();
          bw.write(pb.showName()); //NAME OF THE USER
          bw.newLine();
          bw.write(money); //AMOUNT OF MONEY SAVED
          bw.newLine();

          for(cont = 0; cont<10; cont++){  //WL Items NAMES

              bw.write(wl[cont].showWLName());
              bw.newLine();

          }//for

          for(cont = 0; cont<10; cont++){//wl MONEY

              tempGoaMoney = Integer.toString(wl[cont].showWLMoney());
              bw.write(tempGoaMoney);
              bw.newLine();

          }//for
        
          bw.flush();
          bw.close();

      }catch(IOException e){
          e.printStackTrace();
        }

    }//writeInit


    public static void initFunction() { //SETUP for the new User
        init = true;
        window.showMessageDialog(null, "\tWelcome to PiggyBank,", "PiggyBank", JOptionPane.INFORMATION_MESSAGE);
        nameUser = window.showInputDialog(null, "\tType your name here:", "New User", JOptionPane.INFORMATION_MESSAGE);
        pb.editName(nameUser);
        window.showMessageDialog(null, "\tGreat! Welcome to PiggyBank " + pb.showName() + ".", "WELCOME", JOptionPane.INFORMATION_MESSAGE);
        window.showMessageDialog(null, "\tIt's time to fill in your data.", "PiggyBank", JOptionPane.PLAIN_MESSAGE);

    } //INIT FUNCTION


    public static void fillData(){

        int loopDec;

        tempForParsing = window.showInputDialog(null, "\tHow much money do you have saved right now ? [No comas or points]", "SAVED MONEY", JOptionPane.QUESTION_MESSAGE);
        amountMoney = Integer.parseInt(tempForParsing);
        pb.editMoney(amountMoney);
        window.showMessageDialog(null, "\tNext, you will fill in your WishList [Max 10 wls]", "wls", JOptionPane.PLAIN_MESSAGE);

        for (cont = 0; cont < 10; cont++) {

            if (wlLoop) {

                wl[cont] = new wlClass();
                tempForParsing = window.showInputDialog(null, "\tName of the item: ", "WishList", JOptionPane.PLAIN_MESSAGE);
                wl[cont].editWLName(tempForParsing);
                tempForParsing = window.showInputDialog(null, "\tCost of the item", "WishList", JOptionPane.PLAIN_MESSAGE);
                wlMoney = Integer.parseInt(tempForParsing);
                wl[cont].editWLMoney(wlMoney);
                loopDec = window.showConfirmDialog(null, "\tYou have more Whishlist Items ? ", "WishListq", JOptionPane.YES_NO_OPTION);
                if (loopDec == JOptionPane.NO_OPTION) {
                    wlLoop = false;
                }
            } else {
                wl[cont] = new wlClass();
            }

        }
        window.showMessageDialog(null, "\tOkay " + pb.showName() + ", all things are set, WELCOME!", "WELCOME", JOptionPane.PLAIN_MESSAGE);
    }// FILL DATA FUNCTION


    public static void menu(){
        printData = "\t" + pb.getNameUser() + "\n\t" + "Money Saved: $" + pb.showMoney();
        printData += "\n ******** WishList ********";

        for (cont = 0; cont < 10; cont++) {
            printData += "\n\t" + wl[cont].showWLName() + "\t\t ---- " + "\t$"+ wl[cont].showWLMoney();
        }//for

        menuOption = window.showOptionDialog(null, printData, "PiggyBank", 0, JOptionPane.QUESTION_MESSAGE, null, menuOptions, menuOptions[0]);

    }

    public static void editData(){

            //Variables **********************************************************************

            String wlNames = "";
            int editOption;
            int moneyAmount;
            String edit;
            int editwl;
            String amount;
            int amountInt;
            String newName;
            boolean emptySpace = false;
            int emptySpaceIndex = 0;
            String newItemName;
            int newItemCost;

            editOption = window.showOptionDialog(null, "\tWhat do you want to Edit ?", "EDIT DATA", 0, JOptionPane.PLAIN_MESSAGE, null, editDataOptions, editDataOptions[0]);
            switch (editOption) {

                case 0: // wlS **********************************************************************

                    editOption = window.showOptionDialog(null,"","EDIT WhisList",0,JOptionPane.PLAIN_MESSAGE,null, editwlOptions,editwlOptions[0]);

                    for (cont = 0; cont < 10; cont++) {
                        wlNames += "\n \t"+ (cont+1)+ ".-" + wl[cont].showWLName() + "\t\t ---- " + "\t$"+ wl[cont].showWLMoney();
                    }//for

                    switch(editOption){

                        case 0: //EDIT WL


                            editOption = window.showOptionDialog(null, wlNames , "EDIT WL",0,JOptionPane.PLAIN_MESSAGE,null, nameOrMoney,nameOrMoney[0]);

                                    switch (editOption){

                                        case 0: //Money

                                                do {

                                                    edit = window.showInputDialog(null, " TYPE THE NUMBER OF THE ITEM YOU WANT TO EDIT" + wlNames, "EDIT ITEM COST", JOptionPane.INFORMATION_MESSAGE);
                                                    editwl = Integer.parseInt(edit);

                                                    if(editwl <= 0 || editwl > 10)
                                                    window.showMessageDialog(null, "THE NUMBER YOU TYPED IS NOT IN THE RANGE OF THE 10 ITEMS.\nTRY AGAIN", "ERROR",JOptionPane.ERROR_MESSAGE);

                                                }while(editwl <= 0 || editwl > 10);

                                                editwl -= 1;

                                                amount = window.showInputDialog(null, "How much will '" + wl[editwl].showWLName() +"' cost now ? [No comas or points]" , "EDIT ITEM COST", JOptionPane.QUESTION_MESSAGE);
                                                amountInt = Integer.parseInt(amount);
                                                wl[editwl].editWLMoney(amountInt);

                                            break;

                                        case 1: //Name

                                            do {

                                                edit = window.showInputDialog(null, " TYPE THE NUMBER OF THE ITEM YOU WANT TO EDIT" + wlNames, "EDIT ITEM NAME", JOptionPane.INFORMATION_MESSAGE);
                                                editwl = Integer.parseInt(edit);

                                                if(editwl <= 0 || editwl > 10)
                                                    window.showMessageDialog(null, "THE NUMBER YOU TYPED IS NOT IN THE RANGE OF THE 10 ITEMS.\nTRY AGAIN", "ERROR",JOptionPane.ERROR_MESSAGE);

                                            }while(editwl <= 0 || editwl > 10);

                                            editwl -= 1;
                                            newName = window.showInputDialog(null, "What's the new name for '" + wl[editwl].showWLName() +"' ?", "EDIT ITEM NAME",JOptionPane.QUESTION_MESSAGE);
                                            wl[editwl].editWLName(newName);

                                            break;

                                        default:
                                            break;

                                    }//switch

                            break;

                        case 1: //DELETE wl **********************************************************************

                            do {

                                edit = window.showInputDialog(null, " TYPE THE NUMBER OF THE ITEM YOU WANT TO EDIT" + wlNames, "EDIT ITEM NAME", JOptionPane.INFORMATION_MESSAGE);
                                editwl = Integer.parseInt(edit);

                                if(editwl <= 0 || editwl > 10)
                                    window.showMessageDialog(null, "THE NUMBER YOU TYPED IS NOT IN THE RANGE OF THE 10 ITEMS.\nTRY AGAIN", "ERROR",JOptionPane.ERROR_MESSAGE);

                            }while(editwl <= 0 || editwl > 10);

                            editwl -= 1;
                            window.showMessageDialog(null, "'" + wl[editwl].showWLName() + "' has been DELETED", "ITEM DELETED", JOptionPane.INFORMATION_MESSAGE);
                            wl[editwl] = new wlClass();

                            break;

                        case 2://ADD wl **********************************************************************

                            for(cont = 0; cont<10; cont++){
                                        if(wl[cont].showWLName().equals("---") && wl[cont].showWLMoney() == 0){
                                            emptySpace = true;
                                            emptySpaceIndex = cont;
                                            break;
                                        }//if
                                        else {
                                            emptySpace = false;
                                        }
                            }//for

                            if(emptySpace) {

                                newItemName = window.showInputDialog(null, "Type the name of the new item:", "NEW ITEM", JOptionPane.INFORMATION_MESSAGE);
                                wl[emptySpaceIndex].editWLName(newItemName);
                                tempForParsing = window.showInputDialog(null, "Type de cost of the new item [No comas or points]:", "NEW ITEM", JOptionPane.INFORMATION_MESSAGE);
                                newItemCost = Integer.parseInt(tempForParsing);
                                wl[emptySpaceIndex].editWLMoney(newItemCost);
                            }//if
                            else{
                                window.showMessageDialog(null, "You don't have enough space for another item.\nDelete one to make space for a new item", "ERROR",JOptionPane.ERROR_MESSAGE);
                            }

                            break;

                        default:
                            break;

                    }//switch

                    break;

                case 1: //MONEY **********************************************************************

                    editOption = window.showOptionDialog(null, "", "EDIT MONEY", 0, JOptionPane.PLAIN_MESSAGE, null, editMoneyOptions, editMoneyOptions[0]);
                    switch (editOption) {

                        case 0: //SUBS MONEY

                            do {
                                tempForParsing = window.showInputDialog(null, "\tType the amount of money you want to substract form your savings [No comas or points] :", "MONEY", JOptionPane.PLAIN_MESSAGE);
                                moneyAmount = Integer.parseInt(tempForParsing);

                                if (moneyAmount > pb.showMoney()) {
                                    window.showMessageDialog(null, "\tSorry, you want to substract more money than you have \n\t Try with another amount", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }

                            } while (moneyAmount > pb.showMoney());

                            pb.subsMoney(moneyAmount);
                            break;

                        case 1: //ADDS MONEY

                            tempForParsing = window.showInputDialog(null, "\t Type the amount of money you want to add to your savings [No comas or points] :","MONEY",JOptionPane.PLAIN_MESSAGE);
                            moneyAmount = Integer.parseInt(tempForParsing);
                            pb.addMoney(moneyAmount);
                            break;

                        default:
                            break;

                    }

                    break;

                default:
                    break;

            }

    }//Edit Data    WORKING ON THIS /\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\

}//class
