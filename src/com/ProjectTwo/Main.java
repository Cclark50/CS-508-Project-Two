package com.ProjectTwo;
import javax.xml.crypto.Data;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu(){
        String inputFile = "CS 508 Project Two Data Set.csv";
        Database db = new Database(inputFile);
        ArrayList<DBEntry> list = db.LoadDB();
        Scanner scnr = new Scanner(System.in);
        boolean exit = false;
        Character[] allowedChars = {'a', 'b', 'q'};
        String input;
        try{
            while(!exit){
                PrintMenu();
                input = scnr.nextLine();
                if(input.isEmpty()){
                    throw new IllegalArgumentException("Empty input");
                }
                if(!List.of(allowedChars).contains(input.charAt(0))){
                    throw new IllegalArgumentException("Invalid input");
                }
                switch (input.charAt(0)){
                    case 'a':
                        StateFilterSelection(scnr, db);
                        break;
                    case 'b':
                        GenerateStatistics(db);
                        break;
                    case 'q':
                        exit = true;
                        System.out.println("Shutting down...");
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception ex){
            System.out.println("Please input a valid selection");
        }
    }

    private static void GenerateStatistics(Database db) {
        SummaryStatistics stats = db.GetSummary();
        System.out.printf("Average EV Percentage: %.2f%%\n", stats._avgPercentEvs);
        System.out.println("Total number of EVs (BEVs and PHEVs): " + stats._totalEvs);
        var states = db.GetStateIndex();
        double max = 0.0f;
        String highestPer = "nil";
        for(Map.Entry<String, StateIndex> state : states.entrySet()){
            if(state.getValue().GetAvgEVPercent() > max){
                max = state.getValue().GetAvgEVPercent();
                highestPer = state.getKey();
            }
        }
        System.out.printf("State with the Highest Percentage of EVs: %s\n", highestPer);
    }

    public static void StateFilterSelection(Scanner scnr, Database db){
        System.out.println("Enter the state code to filter by");
        String input = scnr.nextLine();
        try{
            if(input.isEmpty()){
                throw new IllegalArgumentException("Empty input");
            }
            if(input.length() != 2){
                throw new IllegalArgumentException("Invalid input");
            }
            //var filtered = db.FilterByState(list, input);
            var filtered = db.GetFilteredByState(input);
            PrintList(filtered);
            System.out.println("\n------------------------------------------");
            System.out.println("Write this filtered list to output? Y or N");
            System.out.println("------------------------------------------");
            String contString = scnr.nextLine();
            if(contString.isEmpty()){
                return;
            }
            if(contString.charAt(0) != 'y' && contString.charAt(0) != 'Y'){
                return;
            }
            String outputFile = "Filtered " + input + " output.csv";
            db.WriteToOutput(filtered, outputFile);
            System.out.println("Wrote file to " + outputFile);
        }catch (Exception ex){
            System.out.println("Invalid input");
        }
    }

    public static void PrintList(List<DBEntry> list){
        for(DBEntry entry : list){
            System.out.println(entry);
        }
    }

    public static void PrintMenu(){
        System.out.println("\nProject Two Menu:");
        System.out.println("a - Filter by State");
        System.out.println("b - Generate Statistics");
        System.out.println("q - Quit");
        System.out.println("------------------------");
    }

}