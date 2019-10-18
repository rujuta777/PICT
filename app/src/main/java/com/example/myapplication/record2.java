package com.example.myapplication;


public class record2
{
     String objectt,colort,not,desct;

    public record2() {
    }

    public record2(String objectt, String colort, String not, String desct) {
        this.objectt = objectt;
        this.colort = colort;
        this.not = not;
        this.desct = desct;
    }
    public String getObjectt() {
        return objectt;
    }

    public void setObjectt(String objectt) {
        this.objectt = objectt;
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

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desct) {
        this.desct = desct;
    }


}