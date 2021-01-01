/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author benbartel
 */
public class mietenModel {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private FahrzeugModel fahrzeug;
    private KundenModel kunde;
    private int zeitraum;
    private int kosten;
    private boolean abgelaufen;
    private int mietenNummer;

    public mietenModel(LocalDateTime startDate, LocalDateTime endDate, FahrzeugModel fahrzeug, KundenModel kunde) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.fahrzeug = fahrzeug;
        this.kunde = kunde;
        abgelaufen = false;
        calcZeitraum();
        calcKosten();
        mietenNummer = App.getMieten().size() +1;
    }
    
    
    
    public void calcZeitraum(){
        Duration duration = Duration.between(startDate, endDate);
        zeitraum = (int) duration.toHours();
        System.out.println("zeitraum: "+zeitraum);
        calcKosten();
    }
    
    public void calcKosten(){
        kosten = zeitraum * fahrzeug.stundenKosten;
        System.out.println("kosten: "+ kosten);
    }
    
    
    public ArrayList returnAllVar(){
        ArrayList<String> allVar = new ArrayList();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
        String start = getStartDate().format(format);
        String ende = getEndDate().format(format);
        
        allVar.add("Start Datum: " + start);
        allVar.add("End Datum: " + ende);
        allVar.add(getFahrzeug().hersteller + " " + getFahrzeug().model);
        allVar.add(getKunde().vorname + " " + getKunde().nachname);
        allVar.add("Zeitraum: " + zeitraum + "h");
        allVar.add("Kosten: " + kosten + "€");
        
        return allVar;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public FahrzeugModel getFahrzeug() {
        return fahrzeug;
    }

    public void setFahrzeug(FahrzeugModel fahrzeug) {
        this.fahrzeug = fahrzeug;
    }

    public KundenModel getKunde() {
        return kunde;
    }

    public void setKunde(KundenModel kunde) {
        this.kunde = kunde;
    }

    public int getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(int zeitraum) {
        this.zeitraum = zeitraum;
    }

    public int getKosten() {
        return kosten;
    }

    public void setKosten(int kosten) {
        this.kosten = kosten;
    }

    public boolean isAbgelaufen() {
        return abgelaufen;
    }

    public void setAbgelaufen(boolean abgelaufen) {
        this.abgelaufen = abgelaufen;
    }

    public int getMietenNummer() {
        return mietenNummer;
    }

    public void setMietenNummer(int mietenNummer) {
        this.mietenNummer = mietenNummer;
    }
    
    
}
