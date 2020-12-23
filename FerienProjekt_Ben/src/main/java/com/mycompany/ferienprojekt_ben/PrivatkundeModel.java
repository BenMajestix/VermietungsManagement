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
public class PrivatkundeModel extends KundenModel{
    String notfallTelefon;

    public PrivatkundeModel(String notfallTelefon, String vorname, String nachname, int kundenNummer, String telefonNummer, String addresse) {
        super(vorname, nachname, kundenNummer, telefonNummer, addresse);
        this.notfallTelefon = notfallTelefon;
        kundenTyp = "Privatkunde";
    }

    public String getNotfallTelefon() {
        return notfallTelefon;
    }

    public void setNotfallTelefon(String notfallTelefon) {
        this.notfallTelefon = notfallTelefon;
    }
    
    
}
