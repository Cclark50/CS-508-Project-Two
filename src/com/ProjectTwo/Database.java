package com.ProjectTwo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.function.Predicate;

public class Database {

    private List<DBEntry> _DB;

    private Map<String, StateIndex> _stateIndex;

    private SummaryStatistics _summaryStatistics;

    private String _inFile;

    public Database(String inFile) {
        _inFile = inFile;
        _DB = new ArrayList<>();
        _stateIndex = new HashMap<>();
        _summaryStatistics = new SummaryStatistics();
    }

    public SummaryStatistics GetSummary(){
        return _summaryStatistics;
    }

    public Map<String, StateIndex> GetStateIndex(){
        return _stateIndex;
    }

    public ArrayList<DBEntry> LoadDB() {
        ArrayList<DBEntry> list = new ArrayList<>();
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(_inFile))){
            br.readLine(); //trash the first line that just delineates what each column is
            int count = 0;
            double totalEVPercent = 0.0f;
            while((line = br.readLine()) != null){
                var split = line.split(",");
                if(split.length != 10){
                    throw new Exception("Database not correct for this program");
                }
                count++;
                DBEntry entry = new DBEntry(
                        split[0], split[1], split[2], split[3], Integer.parseInt(split[4]),
                        Integer.parseInt(split[5]), Integer.parseInt(split[6]), Integer.parseInt(split[7]), Integer.parseInt(split[8]), Double.parseDouble(split[9])
                );
                totalEVPercent += entry.get_elecPercent();
                _summaryStatistics._totalEvs += entry.get_bev() + entry.get_phev();
                list.add(entry);
                if(!_stateIndex.containsKey(entry.get_state())){
                    _stateIndex.put(entry.get_state(), new StateIndex(entry.get_state()));
                }
                _stateIndex.get(entry.get_state()).AddEntry(entry);
            }
            _summaryStatistics._totalCount = count;
            _summaryStatistics._totalPercentEV = totalEVPercent;
            _summaryStatistics._avgPercentEvs = _summaryStatistics._totalPercentEV / _summaryStatistics._totalCount;
            System.out.println("Number of states in state index: " + _stateIndex.size());
            QuickSortInPlace(list, 0, list.size() - 1, Comparator.comparingDouble(DBEntry::get_elecPercent).reversed());
            WriteToOutput(list, "Sorted out.csv");
            _DB = list;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }

    public List<DBEntry> FilterByState(ArrayList<DBEntry> list, String state){
        Predicate<DBEntry> isState = entry -> entry.get_state().equals(state);
        List<DBEntry> filtered = list.parallelStream()
                .filter(isState)
                .toList();
        return filtered;
    }

    public List<DBEntry> GetFilteredByState(String state){
        return _stateIndex.get(state).GetEntries();
    }

    public void PrintList(){
        for(DBEntry entry : _DB){
            System.out.println(entry);
        }
    }

    public void SortByElecPercent(){
        _DB.sort(Comparator.comparing(DBEntry::get_elecPercent).reversed());
        WriteToOutput(_DB, "out.csv");
    }

    public boolean WriteToOutput(List<DBEntry> list, String outFile){
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

    // Using my quicksort in place I wrote for project one but modified for an arraylist
    public static <T> void QuickSortInPlace(List<T> list, int a, int b, Comparator<T> c){
        if (a >= b) return;
        T pivot = list.get(b);
        T temp;
        int left = a;
        int right = b - 1;
        while (left <= right){
            while(left <= right && c.compare(list.get(left), pivot) < 0){
                left++;
            }
            while(left <= right && c.compare(list.get(right), pivot) > 0){
                right--;
            }
            if(left <= right){
                temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }
        temp = list.get(left);
        list.set(left, list.get(b));
        list.set(b, temp);

        QuickSortInPlace(list, a, left - 1, c);
        QuickSortInPlace(list, left + 1, b, c);
    }

}
