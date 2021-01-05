/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.util.ArrayList;

/**
 *
 * @author benbartel
 */
public abstract class FahrzeugModel {
    String hersteller;
    String model;
    String farbe;
    boolean erhealtlich;
    boolean inReperatur;
    boolean inBenutzung;
    int stundenKosten;
    String typ;
    int fahrzeugNummer;

    public FahrzeugModel(String hersteller, String model, String farbe, boolean erhealtlich, boolean inReperartur, boolean inBenutzung, int stundenKosten, int fahrzeugNummer) {
        this.hersteller = hersteller;
        this.model = model;
        this.farbe = farbe;
        this.erhealtlich = erhealtlich;
        this.inReperatur = inReperartur;
        this.inBenutzung = inBenutzung;
        this.stundenKosten = stundenKosten;
        this.fahrzeugNummer = fahrzeugNummer;
    }

    public ArrayList returnAllVar(){
        ArrayList<String> allVar = new ArrayList();
        
        allVar.add(getHersteller());
        allVar.add(getModel());
        allVar.add(getFarbe());
        if(inBenutzung == true){
            allVar.add("In Benutzung");
        }
        else if(inBenutzung == false){
            allVar.add("Nicht in Benutzung");
        }
        if(inReperatur == true){
            allVar.add("In Reperatur");
        }
        else if(inReperatur == false){
            allVar.add("Nicht in Reperatur");
        }
        allVar.add("Stundenkosten: " + getStundenKosten());
        
        return allVar;
    }
    
    //Returns all Data for the Search in homeView
    public ArrayList returnAllData(){
        ArrayList<String> allData = new ArrayList();
        
        allData.add(getHersteller());
        allData.add(getModel());
        allData.add(getFarbe());
        
        String kosten = getStundenKosten() + "";
        allData.add(kosten.strip());
        
        String nummer = fahrzeugNummer + "";
        allData.add(nummer.strip());
        return allData;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public boolean isInReperatur() {
        return inReperatur;
    }

    public void setInReperatur(boolean inReperatur) {
        this.inReperatur = inReperatur;
    }

    public boolean isInBenutzung() {
        return inBenutzung;
    }

    public void setInBenutzung(boolean inBenutzung) {
        this.inBenutzung = inBenutzung;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public boolean isErhealtlich() {
        return erhealtlich;
    }

    public void setErhealtlich(boolean erhealtlich) {
        this.erhealtlich = erhealtlich;
    }

    public int getStundenKosten() {
        return stundenKosten;
    }

    public void setStundenKosten(int stundenKosten) {
        this.stundenKosten = stundenKosten;
    }

    public String getTyp() {
        return typ;
    }

    public int getFahrzeugNummer() {
        return fahrzeugNummer;
    }

    public void setFahrzeugNummer(int fahrzeugNummer) {
        this.fahrzeugNummer = fahrzeugNummer;
    }
    
    
    
    
}
