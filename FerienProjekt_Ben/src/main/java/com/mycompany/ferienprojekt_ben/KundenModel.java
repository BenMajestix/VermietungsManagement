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
public abstract class KundenModel {
    String vorname;
    String nachname;
    int kundenNummer;
    String telefonNummer;
    String addresse;
    String kundenTyp;
    

    public KundenModel(String vorname, String nachname, int kundenNummer, String telefonNummer, String addresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.kundenNummer = kundenNummer;
        this.telefonNummer = telefonNummer;
        this.addresse = addresse;
        
        
    }

    public ArrayList returnAllVar(){
        ArrayList<String> allVar = new ArrayList();
        
        allVar.add(getVorname());
        allVar.add(getNachname());
        allVar.add(getTelefonNummer());
        allVar.add(getAddresse());
        allVar.add(getKundenTyp());
        allVar.add("KundenNummer : " + getKundenNummer());
        
        return allVar;
    }
    public ArrayList returnAllVarEdit(){
        ArrayList<String> allVar = new ArrayList();
        
        allVar.add(getVorname());
        allVar.add(getNachname());
        allVar.add(getTelefonNummer());
        allVar.add(getAddresse());
        
        return allVar;
    }
    
    //Returns all Data for the Search in homeView
    public ArrayList returnAllData(){
        ArrayList<String> allData = new ArrayList();
        
        allData.add(getVorname());
        allData.add(getNachname());
        allData.add(getTelefonNummer());
        allData.add(getAddresse());
        allData.add(getKundenTyp());
        
        String nummer = getKundenNummer() + " ";
        allData.add(nummer.strip());
        return allData;
    }
    
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getKundenNummer() {
        return kundenNummer;
    }

    public void setKundenNummer(int kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getKundenTyp() {
        return kundenTyp;
    }
    
    
}
