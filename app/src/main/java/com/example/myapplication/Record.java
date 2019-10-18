package com.example.myapplication;


public class Record {
    public Record(String placet, String object, String colort, String not, String datet, String timet, String desct,String emailt) {
        this.placet = placet;
        this.object = object;
        this.colort = colort;
        this.not = not;
        this.emailt=emailt;
        this.datet = datet;
        this.timet = timet;
        this.desct = desct;
    }

    Record()
    {

    }
    String placet;
    String object;
    String colort;
    String not;
    String datet;
    String timet;

    public String getEmailt() {
        return emailt;
    }

    public void setEmailt(String emailt) {
        this.emailt = emailt;
    }

    String emailt;

    public String getPlacet() {
        return placet;
    }

    public void setPlacet(String placet) {
        this.placet = placet;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getColort() {
        return colort;
    }

    public void setColort(String colort) {
        this.colort = colort;
    }

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public String getDatet() {
        return datet;
    }

    public void setDatet(String datet) {
        this.datet = datet;
    }

    public String getTimet() {
        return timet;
    }

    public void setTimet(String timet) {
        this.timet = timet;
    }

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desct) {
        this.desct = desct;
    }

    String desct;



}