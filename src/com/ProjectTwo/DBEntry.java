package com.ProjectTwo;
public class DBEntry {

    private String _date;
    private String _county;
    private String _state;
    private String _vpu;
    private String _bev;
    private String _phev;
    private String _evTotal;
    private String _nonElectTotal;
    private String _totVehicles;
    private String _elecPercent;

    public DBEntry(
            String _date,
            String _county,
            String _state,
            String _vpu,
            String _bev,
            String _phev,
            String _evTotal,
            String _nonElectTotal,
            String _totVehicles,
            String _elecPercent
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

    public String get_bev() {
        return _bev;
    }

    public void set_bev(String _bev) {
        this._bev = _bev;
    }

    public String get_phev() {
        return _phev;
    }

    public void set_phev(String _phev) {
        this._phev = _phev;
    }

    public String get_evTotal() {
        return _evTotal;
    }

    public void set_evTotal(String _evTotal) {
        this._evTotal = _evTotal;
    }

    public String get_nonElectTotal() {
        return _nonElectTotal;
    }
    public void set_nonElectTotal(String _nonElectTotal) {
        this._nonElectTotal = _nonElectTotal;
    }

    public String get_totVehicles() {
        return _totVehicles;
    }

    public void set_totVehicles(String _totVehicles) {
        this._totVehicles = _totVehicles;
    }

    public String get_elecPercent() {
        return _elecPercent;
    }

    public void set_elecPercent(String _elecPercent) {
        this._elecPercent = _elecPercent;
    }

}
