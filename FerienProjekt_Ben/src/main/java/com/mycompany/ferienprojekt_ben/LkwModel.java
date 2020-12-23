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
public class LkwModel extends FahrzeugModel{
    int ladeRaum;

    public LkwModel(int ladeRaum, String hersteller, String model, String farbe, boolean erhealtlich, boolean inReperartur, boolean inBenutzung, int stundenKosten, int fahrzeugNummer) {
        super(hersteller, model, farbe, erhealtlich, inReperartur, inBenutzung, stundenKosten, fahrzeugNummer);
        this.ladeRaum = ladeRaum;
        typ = "LKW";
    }

    

    

    public int getLadeRaum() {
        return ladeRaum;
    }

    public void setLadeRaum(int ladeRaum) {
        this.ladeRaum = ladeRaum;
    }
    
    
}
