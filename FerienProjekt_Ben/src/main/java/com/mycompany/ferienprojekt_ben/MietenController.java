/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author benbartel
 */
public class MietenController implements Initializable {

    @FXML
    private ListView<String> lsMieten;
    @FXML
    private DatePicker datePickStart;
    @FXML
    private DatePicker datePickEnd;
    @FXML
    private TextField txtFahrzeug;
    @FXML
    private TextField txtKunde;
    @FXML
    private Label lblFahrzeug;
    @FXML
    private Label lblKunde;
    @FXML
    private ChoiceBox<String> timePickStart;
    @FXML
    private ChoiceBox<String> timePickEnd;
    @FXML
    private Button erstellenBtn;
    
    //Ob in der ListView die Kunden oder die fahrzeuge angezeigt werden
    int listChoice;
    //Die Startzeiten und Endzeiten der Miete, wird später zu LocalDateTime zusammengefasst
    LocalTime startTime = null;
    LocalTime endTime = null;   
    //Die Startdaten und Enddaten der Miete, wird später zu LocalDateTime zusammengefasst
    LocalDate startDate;
    LocalDate endDate;
    //Der Start und das Ende der Miete, mit Zeit und Datum
    LocalDateTime chosenStartDateTime;
    LocalDateTime chosenEndDateTime;
    
    //Das ausgewählte Fahrzeug/Kunde für die Miete
    FahrzeugModel chosenFahrzeug;
    KundenModel chosenKunde;
    @FXML
    private Label lblWarning;
    @FXML
    private Label lblDate;
    
    
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Die Optionen werden in die ChoiceBoxes gesetzt
        fillChoiceBox();
        //Falls etwas zwischengespeichert wurde, wird es wieder hergestellt
        restore();
    }    

    
    //Die Optionen werden in die ChoiceBoxes gesetzt
    public void fillChoiceBox(){
        timePickStart.getItems().add("01:00");
        timePickStart.getItems().add("02:00");
        timePickStart.getItems().add("03:00");
        timePickStart.getItems().add("04:00");
        timePickStart.getItems().add("05:00");
        timePickStart.getItems().add("06:00");
        timePickStart.getItems().add("07:00");
        timePickStart.getItems().add("08:00");
        timePickStart.getItems().add("09:00");
        timePickStart.getItems().add("10:00");
        timePickStart.getItems().add("11:00");
        timePickStart.getItems().add("12:00");
        timePickStart.getItems().add("13:00");
        timePickStart.getItems().add("14:00");
        timePickStart.getItems().add("15:00");
        timePickStart.getItems().add("16:00");
        timePickStart.getItems().add("17:00");
        timePickStart.getItems().add("18:00");
        timePickStart.getItems().add("19:00");
        timePickStart.getItems().add("20:00");
        timePickStart.getItems().add("21:00");
        timePickStart.getItems().add("22:00");
        timePickStart.getItems().add("23:00");
        timePickStart.getItems().add("24:00");
        
        timePickEnd.getItems().add("01:00");
        timePickEnd.getItems().add("02:00");
        timePickEnd.getItems().add("03:00");
        timePickEnd.getItems().add("04:00");
        timePickEnd.getItems().add("05:00");
        timePickEnd.getItems().add("06:00");
        timePickEnd.getItems().add("07:00");
        timePickEnd.getItems().add("08:00");
        timePickEnd.getItems().add("09:00");
        timePickEnd.getItems().add("10:00");
        timePickEnd.getItems().add("11:00");
        timePickEnd.getItems().add("12:00");
        timePickEnd.getItems().add("13:00");
        timePickEnd.getItems().add("14:00");
        timePickEnd.getItems().add("15:00");
        timePickEnd.getItems().add("16:00");
        timePickEnd.getItems().add("17:00");
        timePickEnd.getItems().add("18:00");
        timePickEnd.getItems().add("19:00");
        timePickEnd.getItems().add("20:00");
        timePickEnd.getItems().add("21:00");
        timePickEnd.getItems().add("22:00");
        timePickEnd.getItems().add("23:00");
        timePickEnd.getItems().add("24:00");
    }
    
    @FXML
    private void btnHome(ActionEvent event) throws IOException {
        //Speichert die Choices des Nutzers zwischen und geht zurück zum homeview
        storeInCache();
        App.setRoot("homeView");
    }
    //Wenn das TextFeld für die Fahrzeuge gecklickt wird
    @FXML
    private void txtFahrClicked(MouseEvent event) {
        //Dann werden alle verfügbaren Fahrzeuge in der Listview gezeigt damit der User die Auswählen kann
        fillListView("fahrzeug");
        listChoice = 1;
    }
    //Wenn das TextFeld für die Kunden gecklickt wird
    @FXML
    private void txtKundClicked(MouseEvent event) {
        //Dann werden alle kunden in der Listview gezeigt damit der User die Auswählen kann
        fillListView("kunde");
        listChoice = 2;
    }

    
    @FXML
    private void btnNewKunde(ActionEvent event) throws IOException {
        //Es werden die jetzigen choices gespeichert 
        storeInCache();
        //Es wird spezifiziert dass der user aus der mietenview kam und ein kunden erstellen will
        App.setHinzuDirFromMiete(2);
        App.setRoot("hinzufuegenView");
        
    }

    @FXML
    private void btnNewFahrz(ActionEvent event) throws IOException {
        //Es werden die jetzigen choices gespeichert 
        storeInCache();
        //Es wird spezifiziert dass der user aus der mietenview kam un ein Fahrzeug erstellen will
        App.setHinzuDirFromMiete(1);
        App.setRoot("hinzufuegenView");
        
    }

    //Es werden alle user choices in App zwischengespeichert
    //Das ist schön für den User, denn wenn er einen neuen Kunden oder Fahrzeug erstellen möchte, muss er nicht wieder alles neu eingeben
    public void storeInCache(){
        //wenn eine Value nicht null ist, wird sie gespeichert
        if(!(timePickStart.getValue() == null)){
            createLocalTimeStart();
            App.setCacheMieteStartTime(startTime);
        }
        if(!(timePickEnd.getValue() == null)){
            createLocalTimeEnd();
            App.setCacheMieteEndTime(endTime);
        }
        if(!(datePickStart.getValue() == null)){
            LocalDate startDateTemp = datePickStart.getValue();
            App.setCacheMieteStartDate(startDateTemp);
        }
        if(!(datePickEnd.getValue() == null)){
            LocalDate endDateTemp = datePickEnd.getValue();
            App.setCacheMieteEndDate(endDateTemp);
        }
        if(!(chosenFahrzeug == null)){
            App.setCacheMieteFahrzeug(chosenFahrzeug);
        }
        if(!(chosenKunde == null)){
            App.setCacheMieteKunde(chosenKunde);
        }
    }
    //Hier werden alle gespeicherten Values wiederhergestellt und in die Felder gesetzt
    public void restore(){
        
        //Wenn eine Value in App nicht null ist, dann wird diese Value wiederhergestellt
        if(!(App.getCacheMieteStartTime() == null)){
            startTime = App.getCacheMieteStartTime();
            switch(startTime.getHour()){
                case 0: timePickStart.setValue("24:00"); break;
                case 1: timePickStart.setValue("01:00"); break;
                case 2: timePickStart.setValue("02:00"); break;
                case 3: timePickStart.setValue("03:00"); break;
                case 4: timePickStart.setValue("04:00"); break;
                case 5: timePickStart.setValue("05:00"); break;
                case 6: timePickStart.setValue("06:00"); break;
                case 7: timePickStart.setValue("07:00"); break;
                case 8: timePickStart.setValue("08:00"); break;
                case 9: timePickStart.setValue("09:00"); break;
                case 10: timePickStart.setValue("10:00"); break;
                case 11: timePickStart.setValue("11:00"); break;
                case 12: timePickStart.setValue("12:00"); break;
                case 13: timePickStart.setValue("13:00"); break;
                case 14: timePickStart.setValue("14:00"); break;
                case 15: timePickStart.setValue("15:00"); break;
                case 16: timePickStart.setValue("16:00"); break;
                case 17: timePickStart.setValue("17:00"); break;
                case 18: timePickStart.setValue("18:00"); break;
                case 19: timePickStart.setValue("19:00"); break;
                case 20: timePickStart.setValue("20:00"); break;
                case 21: timePickStart.setValue("21:00"); break;
                case 22: timePickStart.setValue("22:00"); break;
                case 23: timePickStart.setValue("23:00"); break;
                
            }
        }
        if(!(App.getCacheMieteEndTime() == null)){
            endTime = App.getCacheMieteEndTime();
            switch(endTime.getHour()){
                case 0: timePickEnd.setValue("24:00"); break;
                case 1: timePickEnd.setValue("01:00"); break;
                case 2: timePickEnd.setValue("02:00"); break;
                case 3: timePickEnd.setValue("03:00"); break;
                case 4: timePickEnd.setValue("04:00"); break;
                case 5: timePickEnd.setValue("05:00"); break;
                case 6: timePickEnd.setValue("06:00"); break;
                case 7: timePickEnd.setValue("07:00"); break;
                case 8: timePickEnd.setValue("08:00"); break;
                case 9: timePickEnd.setValue("09:00"); break;
                case 10: timePickEnd.setValue("10:00"); break;
                case 11: timePickEnd.setValue("11:00"); break;
                case 12: timePickEnd.setValue("12:00"); break;
                case 13: timePickEnd.setValue("13:00"); break;
                case 14: timePickEnd.setValue("14:00"); break;
                case 15: timePickEnd.setValue("15:00"); break;
                case 16: timePickEnd.setValue("16:00"); break;
                case 17: timePickEnd.setValue("17:00"); break;
                case 18: timePickEnd.setValue("18:00"); break;
                case 19: timePickEnd.setValue("19:00"); break;
                case 20: timePickEnd.setValue("20:00"); break;
                case 21: timePickEnd.setValue("21:00"); break;
                case 22: timePickEnd.setValue("22:00"); break;
                case 23: timePickEnd.setValue("23:00"); break;
                
            }
        }
        if(!(App.getCacheMieteStartDate() == null)){
            startDate = App.getCacheMieteStartDate();
            datePickStart.setValue(startDate);
        }
        if(!(App.getCacheMieteEndDate() == null)){
            endDate = App.getCacheMieteEndDate();
            datePickEnd.setValue(endDate);
        }
        if(!(App.getCacheMieteFahrzeug() == null)){
            chosenFahrzeug = App.getCacheMieteFahrzeug();
            txtFahrzeug.setText(chosenFahrzeug.hersteller + " " + chosenFahrzeug.model);
        }
        if(!(App.getCacheMieteKunde() == null)){
            chosenKunde = App.getCacheMieteKunde();
            txtKunde.setText(chosenKunde.vorname + " " + chosenKunde.nachname);
        }
        
        nullCache();
    }
    
    //Wenn eine Miete erstellt wird, wird der Zwischenspeicher komplett gelöscht
    public void nullCache(){
        App.setCacheMieteStartTime(null);
        App.setCacheMieteEndTime(null);
        App.setCacheMieteStartDate(null);
        App.setCacheMieteEndDate(null);
        App.setCacheMieteFahrzeug(null);
        App.setCacheMieteKunde(null);
    }
    
    //Die ListView wird mit den Fahrzeugen oder Kunden gefüllt 
    public void fillListView(String choice){
        lsMieten.getItems().clear();
        //Wenn die Fahrzeuge gewählt sind, dann werden alle erhealtlichen Fahrzeuge zur auswahl gestellt
        if("fahrzeug".equals(choice)){
            for(FahrzeugModel f : App.getFahrzeuge()){
                if(f.isErhealtlich() == true){
                lsMieten.getItems().add(f.hersteller + " " + f.model + " " + f.farbe + " Typ: " + f.typ);}
            }
        }
        //Wenn die Kunden gewählt sind, dann werden alle kunden zur auswahl gestellt
        else if("kunde".equals(choice)){
            for(KundenModel k : App.getKunden()){
                lsMieten.getItems().add(k.vorname + " " + k.nachname + " " + k.telefonNummer + " " + k.kundenTyp);
            }
        }
    }
    //Es wird mit der auswahl in der ChoiceBox ein LocalTime erstellt um später ein LocalDateTime zu erstellen
    public void createLocalTimeStart(){
        LocalTime start = null;
        //Da man nicht direkt mit der Value ein LocalTime erstellen kann, muss ich ein Switch machen 
        switch(timePickStart.getValue()){
            case "01:00" : start = LocalTime.of(1, 0, 0, 0);break;
            case "02:00" : start = LocalTime.of(2, 0, 0, 0);break;
            case "03:00" : start = LocalTime.of(3, 0, 0, 0);break;
            case "04:00" : start = LocalTime.of(4, 0, 0, 0);break;
            case "05:00" : start = LocalTime.of(5, 0, 0, 0);break;
            case "06:00" : start = LocalTime.of(6, 0, 0, 0);break;
            case "07:00" : start = LocalTime.of(7, 0, 0, 0);break;
            case "08:00" : start = LocalTime.of(8, 0, 0, 0);break;
            case "09:00" : start = LocalTime.of(9, 0, 0, 0);break;
            case "10:00" : start = LocalTime.of(10, 0, 0, 0);break;
            case "11:00" : start = LocalTime.of(11, 0, 0, 0);break;
            case "12:00" : start = LocalTime.of(12, 0, 0, 0);break;
            case "13:00" : start = LocalTime.of(13, 0, 0, 0);break;
            case "14:00" : start = LocalTime.of(14, 0, 0, 0);break;
            case "15:00" : start = LocalTime.of(15, 0, 0, 0);break;
            case "16:00" : start = LocalTime.of(16, 0, 0, 0);break;
            case "17:00" : start = LocalTime.of(17, 0, 0, 0);break;
            case "18:00" : start = LocalTime.of(18, 0, 0, 0);break;
            case "19:00" : start = LocalTime.of(19, 0, 0, 0);break;
            case "20:00" : start = LocalTime.of(20, 0, 0, 0);break;
            case "21:00" : start = LocalTime.of(21, 0, 0, 0);break;
            case "22:00" : start = LocalTime.of(22, 0, 0, 0);break;
            case "23:00" : start = LocalTime.of(23, 0, 0, 0);break;
            case "24:00" : start = LocalTime.of(24, 0, 0, 0);break;
        }
        startTime = start;
    }
    
    public void createLocalTimeEnd(){
        
        LocalTime end = null;
        switch(timePickEnd.getValue()){
            case "01:00" : end = LocalTime.of(1, 0, 0, 0);break;
            case "02:00" : end = LocalTime.of(2, 0, 0, 0);break;
            case "03:00" : end = LocalTime.of(3, 0, 0, 0);break;
            case "04:00" : end = LocalTime.of(4, 0, 0, 0);break;
            case "05:00" : end = LocalTime.of(5, 0, 0, 0);break;
            case "06:00" : end = LocalTime.of(6, 0, 0, 0);break;
            case "07:00" : end = LocalTime.of(7, 0, 0, 0);break;
            case "08:00" : end = LocalTime.of(8, 0, 0, 0);break;
            case "09:00" : end = LocalTime.of(9, 0, 0, 0);break;
            case "10:00" : end = LocalTime.of(10, 0, 0, 0);break;
            case "11:00" : end = LocalTime.of(11, 0, 0, 0);break;
            case "12:00" : end = LocalTime.of(12, 0, 0, 0);break;
            case "13:00" : end = LocalTime.of(13, 0, 0, 0);break;
            case "14:00" : end = LocalTime.of(14, 0, 0, 0);break;
            case "15:00" : end = LocalTime.of(15, 0, 0, 0);break;
            case "16:00" : end = LocalTime.of(16, 0, 0, 0);break;
            case "17:00" : end = LocalTime.of(17, 0, 0, 0);break;
            case "18:00" : end = LocalTime.of(18, 0, 0, 0);break;
            case "19:00" : end = LocalTime.of(19, 0, 0, 0);break;
            case "20:00" : end = LocalTime.of(20, 0, 0, 0);break;
            case "21:00" : end = LocalTime.of(21, 0, 0, 0);break;
            case "22:00" : end = LocalTime.of(22, 0, 0, 0);break;
            case "23:00" : end = LocalTime.of(23, 0, 0, 0);break;
            case "24:00" : end = LocalTime.of(24, 0, 0, 0);break;
        }
        
        endTime = end;
    }
    
    //Hier wird die LocalDate anhand des DatePickers erstellt
    public void createDateTime(){
        try{
            startDate = datePickStart.getValue();}
        catch(Exception e){System.out.println("err");}
        try{
            endDate = datePickEnd.getValue();}
        catch(Exception e){System.out.println("err");}
        
        createLocalTimeStart();
        createLocalTimeEnd();
        
        chosenStartDateTime = LocalDateTime.of(startDate, startTime);
        chosenEndDateTime = LocalDateTime.of(endDate, endTime);
        
        System.out.println(chosenStartDateTime);
        System.out.println(chosenEndDateTime);
    }
    
    
    @FXML
    private void btnErstellen(ActionEvent event) throws IOException {
        //Es müssen alle Felder ausgefüllt sein, damit es zu keinen Fehlern kommen kann
        if(datePickStart.getValue() != null && datePickEnd.getValue() != null && timePickEnd.getValue() != null && timePickStart.getValue() != null && !"".equals(txtFahrzeug.getText().strip()) && !"".equals(txtKunde.getText().strip())){
            //Es werden die LocalDates erstellt und dann werden dates gesetzt mit der user Choice
            LocalDate s;
            LocalDate e;
            s = datePickStart.getValue();
            e = datePickEnd.getValue();
            createDateTime();
            //Falls das Start nach dem EndDatum ist wird es einen Fehler geben
            if(chosenEndDateTime.isBefore(chosenStartDateTime)){
                erstellenBtn.setText("Das EndDatum muss nach dem StartDatum sein!");
            }
            else{
                //Das gewählte Fahrzeug wird benutzt gesetzt.
                chosenFahrzeug.setInBenutzung(true);
                //Die Miete wird erstellt und die MietenNummer wird gesetzt
                mietenModel newMiete = new mietenModel(chosenStartDateTime, chosenEndDateTime, chosenFahrzeug, chosenKunde);
                //Die nummer ist nur der Index des Objektes in der ArrList
                newMiete.setMietenNummer(App.getMieten().size()+1);
                //Die Miete wird Hinzugefügt
                App.getMieten().add(newMiete);
                //Updated welche Fahzeuge noch erhaeltlich sind
                App.checkMietenFahrzeuge();
                System.out.println(App.getMieten().get(App.getMieten().size() -1));
                App.setRoot("homeView");
                nullCache();
            }
        }
        else{
            erstellenBtn.setText("Alle Felder müssen ausgefüllt sein!");
        }
    }

    //Der Index des gewählten Objektes in der ListView wird gespeichert
    @FXML
    private void getListIndex(MouseEvent event) {
        int index = lsMieten.getFocusModel().getFocusedIndex();
        //Dann wird das gewählte Objekt anhand des Index's aus der ArrList genommen und gespeichert
        if(listChoice == 1){
            chosenFahrzeug = App.getFahrzeuge().get(index);
            txtFahrzeug.setText(chosenFahrzeug.hersteller + " " + chosenFahrzeug.model);
        }
        else if(listChoice == 2){
            chosenKunde = App.getKunden().get(index);
            txtKunde.setText(chosenKunde.vorname + " " + chosenKunde.nachname);
        }
    }
    
}
