package com.ProjectTwo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.Inet4Address;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Menu();
    }

    public static void Menu(){
        String inputFile = "CS 508 Project Two Data Set.csv";
        ArrayList<DBEntry> list = LoadDB(inputFile);
        Scanner scnr = new Scanner(System.in);
        boolean exit = false;
        Character[] allowedChars = {'a', 'b', 'c', 'q'};
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
                        StateFilterSelection(list, scnr);
                        break;
                    case 'b':
                        GenerateStatistics(list);
                        break;
                    case 'c':
                        SortByElecPercent(list);
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

    private static void GenerateStatistics(ArrayList<DBEntry> list) {
        // TODO: Implement GenerateStatistics
        System.out.println("Todo: Implement GenerateStatistics");
    }

    public static void StateFilterSelection(ArrayList<DBEntry> list, Scanner scnr){
        System.out.println("Enter the state code to filter by");
        String input = scnr.nextLine();
        try{
            if(input.isEmpty()){
                throw new IllegalArgumentException("Empty input");
            }
            if(input.length() != 2){
                throw new IllegalArgumentException("Invalid input");
            }
            var filtered = FilterByState(list, input);
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
            WriteToOutput(filtered, outputFile);
            System.out.println("Wrote file to " + outputFile);
        }catch (Exception ex){
            System.out.println("Invalid input");
        }
    }

    public static void PrintMenu(){
        System.out.println("\nProject Two Menu:");
        System.out.println("a - Filter by State");
        System.out.println("b - Generate Statistics");
        System.out.println("c - Sort by Electric Vehicle Percentage");
        System.out.println("q - Quit");
        System.out.println("------------------------");
    }

    public static ArrayList<DBEntry> LoadDB(String inputFile) {
        ArrayList<DBEntry> list = new ArrayList<>();
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            br.readLine(); //trash the first line that just delineates what each column is
            while((line = br.readLine()) != null){
                var split = line.split(",");
                if(split.length != 10){
                    throw new Exception("Database not correct for this program");
                }
                DBEntry entry = new DBEntry(
                        split[0], split[1], split[2], split[3], Integer.parseInt(split[4]),
                        Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]), Double.parseDouble(split[9])
                );
                list.add(entry);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public static List<DBEntry> FilterByState(ArrayList<DBEntry> list, String state){
        Predicate<DBEntry> isWA = entry -> entry.get_state().equals(state);
        List<DBEntry> filtered = list.parallelStream()
                .filter(isWA)
                .toList();
        return filtered;
    }

    public static void PrintList(List<DBEntry> list){
        for(DBEntry entry : list){
            System.out.println(entry);
        }
    }

    public static void SortByElecPercent(ArrayList<DBEntry> list){
        list.sort(Comparator.comparing(DBEntry::get_elecPercent).reversed());
        WriteToOutput(list, "out.csv");
    }

    public static boolean WriteToOutput(List<DBEntry> list, String outFile){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))){
            bw.write("Date,County,State,Vehicle Primary Use,Battery Electric Vehicles (BEVs),Plug-In Hybrid Electric Vehicles (PHEVs),Electric Vehicle (EV) Total,Non-Electric Vehicle Total,Total Vehicles,Percent Electric Vehicles\n");
            for(DBEntry entry : list){
                bw.write(entry + "\n");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}