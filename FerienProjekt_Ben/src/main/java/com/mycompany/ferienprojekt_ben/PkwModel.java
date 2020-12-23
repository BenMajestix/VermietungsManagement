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
public class PkwModel extends FahrzeugModel{
    int sitze;
    int ps;

    public PkwModel(int sitze, int ps, String hersteller, String model, String farbe, boolean erhealtlich, boolean inReperartur, boolean inBenutzung, int stundenKosten, int fahrzeugNummer) {
        super(hersteller, model, farbe, erhealtlich, inReperartur, inBenutzung, stundenKosten, fahrzeugNummer);
        this.sitze = sitze;
        this.ps = ps;
        typ = "PKW";
    }

    

    

    public int getSitze() {
        return sitze;
    }

    public void setSitze(int sitze) {
        this.sitze = sitze;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
    
}   
