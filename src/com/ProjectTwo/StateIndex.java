package com.ProjectTwo;

import java.util.ArrayList;
import java.util.List;

public class StateIndex {

    private List<DBEntry> _entries;

    private String _state;

    private double _totalEVPercent;

    private double _avgEVPercent;

    public StateIndex(String state){
        _entries = new ArrayList<DBEntry>();
        _totalEVPercent = 0;
        _avgEVPercent = 0;
        _state = state;
    }

    public void AddEntry(DBEntry entry){
        _entries.add(entry);
        _totalEVPercent += entry.get_elecPercent();
    }

    public List<DBEntry> GetEntries(){
        return _entries;
    }

    public double GetAvgEVPercent(){
        _avgEVPercent = _totalEVPercent / (double)_entries.size();
        return _avgEVPercent;
    }
}
