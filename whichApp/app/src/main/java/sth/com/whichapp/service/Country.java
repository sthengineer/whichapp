package sth.com.whichapp.service;

public class Country {
    String name;
    String iso;
    String number;

    public Country(String name, String iso, String number) {
        this.name = name;
        this.iso = iso;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
