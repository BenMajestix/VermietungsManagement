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
public class GeschaeftskundeModel extends KundenModel{
    String arbeitsAdresse;
    String arbeitsTelefon;

    public GeschaeftskundeModel(String arbeitsAdresse, String arbeitsTelefon, String vorname, String nachname, int kundenNummer, String telefonNummer, String addresse) {
        super(vorname, nachname, kundenNummer, telefonNummer, addresse);
        this.arbeitsAdresse = arbeitsAdresse;
        this.arbeitsTelefon = arbeitsTelefon;
        kundenTyp = "Geschäftskunde";
    }

    public String getArbeitsAdresse() {
        return arbeitsAdresse;
    }

    public void setArbeitsAdresse(String arbeitsAdresse) {
        this.arbeitsAdresse = arbeitsAdresse;
    }

    public String getArbeitsTelefon() {
        return arbeitsTelefon;
    }

    public void setArbeitsTelefon(String arbeitsTelefon) {
        this.arbeitsTelefon = arbeitsTelefon;
    }
    
    
}
