/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author benbartel
 */
public class mietenModel {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private FahrzeugModel fahrzeug;
    private KundenModel kunde;
    private double zeitraum;
    private double kosten;

    public mietenModel(LocalDateTime startDate, LocalDateTime endDate, FahrzeugModel fahrzeug, KundenModel kunde) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.fahrzeug = fahrzeug;
        this.kunde = kunde;
        
        //calcZeitraum();
        //calcKosten();
    }
    
    
    
    public void calcZeitraum(){
        Duration duration = Duration.between(startDate, endDate);
        zeitraum = duration.toHours();
        System.out.println("zeitraum: "+zeitraum);
    }
    
    public void calcKosten(){
        kosten = zeitraum * fahrzeug.stundenKosten;
        System.out.println("kosten: "+kosten);
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

    public double getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(double zeitraum) {
        this.zeitraum = zeitraum;
    }

    public double getKosten() {
        
        
        
        
        return kosten;
    }

    public void setKosten(double kosten) {
        this.kosten = kosten;
    }
    
    
}
