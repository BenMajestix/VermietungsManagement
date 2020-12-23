/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

/**
 *
 * @author benbartel
 */
public class AnhaengerModel extends FahrzeugModel{
    int beladungsLimit;

    public AnhaengerModel(int beladungsLimit, String hersteller, String model, String farbe, boolean erhealtlich, boolean inReperartur, boolean inBenutzung, int stundenKosten, int fahrzeugNummer) {
        super(hersteller, model, farbe, erhealtlich, inReperartur, inBenutzung, stundenKosten, fahrzeugNummer);
        this.beladungsLimit = beladungsLimit;
        typ = "Anhaenger";
    }

    

    

    
    
    

    public int getBeladungsLimit() {
        return beladungsLimit;
    }

    public void setBeladungsLimit(int beladungsLimit) {
        this.beladungsLimit = beladungsLimit;
    }
    
    
}
