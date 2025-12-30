package com.ProjectTwo;

// this is acting as a struct
public class SummaryStatistics{

    // for average percentage of evs
    public int _totalCount;
    public double _totalPercentEV;
    public double _avgPercentEvs;

    // for total number of EVs (BEV and PHEV)
    public int _totalEvs;

    public String _highestEVPer;

    public SummaryStatistics(){
        _totalCount = 0;
        _totalPercentEV = 0;
        _avgPercentEvs = 0;
        _totalEvs = 0;
        _highestEVPer = null;
    }

}
