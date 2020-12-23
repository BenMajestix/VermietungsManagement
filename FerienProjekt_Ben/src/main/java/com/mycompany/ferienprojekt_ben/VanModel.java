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
public class VanModel extends FahrzeugModel{
    boolean getoenteScheiben;

    public VanModel(boolean getoenteScheiben, String hersteller, String model, String farbe, boolean erhealtlich, boolean inReperartur, boolean inBenutzung, int stundenKosten, int fahrzeugNummer) {
        super(hersteller, model, farbe, erhealtlich, inReperartur, inBenutzung, stundenKosten, fahrzeugNummer);
        this.getoenteScheiben = getoenteScheiben;
        typ = "Van";
    }

    

    

    public boolean isGetoenteScheiben() {
        return getoenteScheiben;
    }

    public void setGetoenteScheiben(boolean getoenteScheiben) {
        this.getoenteScheiben = getoenteScheiben;
    }

    
    
    
}
