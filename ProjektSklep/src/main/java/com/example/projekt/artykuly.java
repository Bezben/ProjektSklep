package com.example.projekt;

public class artykuly {

    public int id, cena , cena_app;
    public String nazwa, ilosc;


    public artykuly(int id, int cena, int cena_app, String nazwa, String ilosc) {
        this.id = id;
        this.cena = cena;
        this.cena_app = cena_app;
        this.nazwa = nazwa;
        this.ilosc = ilosc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public void setCena_app(int cena_app) {
        this.cena_app = cena_app;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;

    }

    public int getCena() {
        return cena;
    }

    public int getCena_app() {
        return cena_app;
    }

    public String getIlosc() {
        return ilosc;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }





}
