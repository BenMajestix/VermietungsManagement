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
    
    
    //Es wird der Zeitruam zwischen dem Start und Enddatum ausgerechnet
    public void calcZeitraum(){
        Duration duration = Duration.between(startDate, endDate);
        zeitraum = (int) duration.toHours();
        calcKosten();
    }
    //Und die Kosten auch gleich erneuert
    public void calcKosten(){
        kosten = zeitraum * fahrzeug.stundenKosten;
    }
    
    
    public ArrayList returnAllVar(){
        ArrayList<String> allVar = new ArrayList();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
        String start = getStartDate().format(format);
        String ende = getEndDate().format(format);
        
        allVar.add("Startdatum: " + start);
        allVar.add("Enddatum: " + ende);
        allVar.add(getFahrzeug().hersteller + " " + getFahrzeug().model);
        allVar.add(getKunde().vorname + " " + getKunde().nachname);
        allVar.add("Zeitraum: " + zeitraum + "h");
        allVar.add("Kosten: " + kosten + "€");
        
        return allVar;
    }

    //Returns all Data for the Search in homeView
    public ArrayList returnAllData(){
        ArrayList<String> allData = new ArrayList();
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm");  
        String start = getStartDate().format(format);
        String ende = getEndDate().format(format);
        
        allData.add("Start Datum: " + start);
        allData.add("End Datum: " + ende);
        allData.add(getFahrzeug().hersteller);
        allData.add(getFahrzeug().model);
        allData.add(getKunde().vorname);
        allData.add(getKunde().nachname);
        
        String zeit = zeitraum + " ";
        allData.add(zeit.strip());
        
        String hrKosten = kosten + " ";
        allData.add(hrKosten.strip());
        
        return allData;
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
