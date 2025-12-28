package com.ProjectTwo;
public class DBEntry {

    private String _date;
    private String _county;
    private String _state;
    private String _vpu;
    private int _bev;
    private int _phev;
    private int _evTotal;
    private int _nonElectTotal;
    private int _totVehicles;
    private double _elecPercent;

    public DBEntry(
            String _date,
            String _county,
            String _state,
            String _vpu,
            int _bev,
            int _phev,
            int _evTotal,
            int _nonElectTotal,
            int _totVehicles,
            double _elecPercent
    ) {
        this._date = _date;
        this._county = _county;
        this._state = _state;
        this._vpu = _vpu;
        this._bev = _bev;
        this._phev = _phev;
        this._evTotal = _evTotal;
        this._nonElectTotal = _nonElectTotal;
        this._totVehicles = _totVehicles;
        this._elecPercent = _elecPercent;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public String get_county() {
        return _county;
    }

    public void set_county(String _county) {
        this._county = _county;
    }

    public String get_state() {
        return _state;
    }

    public void set_state(String _state) {
        this._state = _state;
    }

    public String get_vpu() {
        return _vpu;
    }

    public void set_vpu(String _vpu) {
        this._vpu = _vpu;
    }

    public int get_bev() {
        return _bev;
    }

    public void set_bev(String _bev) {
        this._bev = Integer.parseInt(_bev);
    }

    public int get_phev() {
        return _phev;
    }

    public void set_phev(String _phev) {
        this._phev = Integer.parseInt(_phev);
    }

    public int get_evTotal() {
        return _evTotal;
    }

    public void set_evTotal(String _evTotal) {
        this._evTotal = Integer.parseInt(_evTotal);
    }

    public int get_nonElectTotal() {
        return _nonElectTotal;
    }
    public void set_nonElectTotal(String _nonElectTotal) {
        this._nonElectTotal = Integer.parseInt(_nonElectTotal);
    }

    public int get_totVehicles() {
        return _totVehicles;
    }

    public void set_totVehicles(String _totVehicles) {
        this._totVehicles = Integer.parseInt(_totVehicles);
    }

    public double get_elecPercent() {
        return _elecPercent;
    }

    public void set_elecPercent(String _elecPercent) {
        this._elecPercent = Double.parseDouble(_elecPercent);
    }

    @Override
    public String toString() {
        return _date + "," + _county + "," + _state + "," + _vpu + "," + _bev + "," + _phev + "," + _evTotal + "," + _nonElectTotal + "," + _totVehicles + "," + _elecPercent;
    }
}
