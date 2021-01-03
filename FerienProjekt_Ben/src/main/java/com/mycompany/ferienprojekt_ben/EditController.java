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
import javafx.scene.control.CheckBox;
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
public class EditController implements Initializable {

    @FXML
    private ListView<String> lsItems;
    @FXML
    private TextField txtItemEdit;
    private int indexOfItem;
    private String arrOfItem;
    @FXML
    private Label lblInstructions;
    
    private GeschaeftskundeModel geschKunde;
    private PrivatkundeModel privKunde;
    
    private PkwModel pkw;
    private LkwModel lkw;
    private VanModel van;
    private AnhaengerModel anhaenger;
    private mietenModel miete;
    private int index;
    private FahrzeugModel chosenFahrzeugForMiete;
    private KundenModel chosenKundeForMiete;
    @FXML
    private CheckBox checkBox;
    @FXML
    private DatePicker datePickEdit;
    @FXML
    private ChoiceBox<String> choiceBoxTime;
    private boolean inMieteChangeMode;
    private String mieteEditChoice;
    @FXML
    private Button anwendenBtn;
    @FXML
    private Label lblWarning;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Es werden der Index und die Klasse des ausgewählten Objekts hier aus App genommen und hier gespeichert.
        //Dies gibt keine Technischen vorteile, aber so ist weniger code zu schreiben und es ist einfacher
        indexOfItem = App.getChoiceEditView();
        arrOfItem = App.getChoiceForEditViewTyp();
        //Die ListView wird mit den entsprechenden Variablen gefüllt
        fillLsView();
        lblInstructions.setText("Wählen Sie ein Item aus, um es zu ändern.");
        enableTextInput();
        fillChoiceBox();
    }   

    
    
    private void fillChoiceBox(){
        choiceBoxTime.getItems().add("01:00");
        choiceBoxTime.getItems().add("02:00");
        choiceBoxTime.getItems().add("03:00");
        choiceBoxTime.getItems().add("04:00");
        choiceBoxTime.getItems().add("05:00");
        choiceBoxTime.getItems().add("06:00");
        choiceBoxTime.getItems().add("07:00");
        choiceBoxTime.getItems().add("08:00");
        choiceBoxTime.getItems().add("09:00");
        choiceBoxTime.getItems().add("10:00");
        choiceBoxTime.getItems().add("11:00");
        choiceBoxTime.getItems().add("12:00");
        choiceBoxTime.getItems().add("13:00");
        choiceBoxTime.getItems().add("14:00");
        choiceBoxTime.getItems().add("15:00");
        choiceBoxTime.getItems().add("16:00");
        choiceBoxTime.getItems().add("17:00");
        choiceBoxTime.getItems().add("18:00");
        choiceBoxTime.getItems().add("19:00");
        choiceBoxTime.getItems().add("20:00");
        choiceBoxTime.getItems().add("21:00");
        choiceBoxTime.getItems().add("22:00");
        choiceBoxTime.getItems().add("23:00");
        choiceBoxTime.getItems().add("24:00");
    }
    
    //Methode welche die Änderungen auf das gewählte Objekt überschreibt
    @FXML
    private void btnAnwenden(ActionEvent event) {
        txtItemEdit.setDisable(true);
        anwendenBtn.setDisable(true);
        //Es muss abgefragt werden welches Object und welche Variable ausgewählt sind, damit die richtige Variable überschrieben werden kann.             
        lblInstructions.setText("Ein Item auswählen um es zu ändern.");
        lblWarning.setText("");
        if(pkw != null){
            //Wenn die Klasse des Objekts evaluiert wurde, wird eine Kopie des Objektes hier zwischengespeichert
            //Man kann nicht einfach das Objekt direkt aus der ArrayList benutzen, da es dann nicht die Variablen und Methoden der SubKlasse beinhalted.
            //Deshalb wird dieses Objekt hier speziell als PkwModel gespeichert.
            PkwModel p = (PkwModel) App.getFahrzeuge().get(indexOfItem -1);
            if(index == 0){
                //hier werden nur die ausgewählten Variablen geändert.
                p.setHersteller(txtItemEdit.getText());
            }
            else if(index == 1){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setModel(txtItemEdit.getText());}
            }
            else if(index == 2){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setFarbe(txtItemEdit.getText());}
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperatur(checkBox.isSelected());
            }
            else if(index == 5){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));}
            }
            else if(index == 6){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setSitze(Integer.parseInt(txtItemEdit.getText()));}
            }
            else if(index == 7){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setPs(Integer.parseInt(txtItemEdit.getText()));}
            }
            
            //Das veraltete Fahrzeug wird aus der ArrayList entfernt und das überarbeitete Objekt wird am selben Index hinzugefügt
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
            
        }
        else if(lkw != null){
            LkwModel p = (LkwModel) App.getFahrzeuge().get(indexOfItem -1);
            if(index == 0){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setHersteller(txtItemEdit.getText());}
            }
            else if(index == 1){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setModel(txtItemEdit.getText());}
            }
            else if(index == 2){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setFarbe(txtItemEdit.getText());}
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperatur(checkBox.isSelected());
            }
            else if(index == 5){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));}
            }
            else if(index == 6){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setLadeRaum(Integer.parseInt(txtItemEdit.getText()));}
            }
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
        }
        else if(van != null){
            VanModel p = (VanModel) App.getFahrzeuge().get(indexOfItem -1);
            if(index == 0){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setHersteller(txtItemEdit.getText());}
            }
            else if(index == 1){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setModel(txtItemEdit.getText());}
            }
            else if(index == 2){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setFarbe(txtItemEdit.getText());}
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperatur(checkBox.isSelected());
            }
            else if(index == 5){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));}
            }
            else if(index == 6){
                p.setGetoenteScheiben(checkBox.isSelected());
            }
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
            
        }
        else if(anhaenger != null){
            AnhaengerModel p = (AnhaengerModel) App.getFahrzeuge().get(indexOfItem -1);
            if(index == 0){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setHersteller(txtItemEdit.getText());}
            }
            else if(index == 1){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setModel(txtItemEdit.getText());}
            }
            else if(index == 2){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setFarbe(txtItemEdit.getText());}
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperatur(checkBox.isSelected());
            }
            else if(index == 5){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));}
            }
            else if(index == 6){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                p.setBeladungsLimit(Integer.parseInt(txtItemEdit.getText()));}
            }
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
        }
        else if(privKunde != null){
            PrivatkundeModel k = (PrivatkundeModel) App.getKunden().get(indexOfItem - 1);
            
            if(index == 0){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setVorname(txtItemEdit.getText());}
            }
            else if(index == 1){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setNachname(txtItemEdit.getText());}
            }
            else if(index == 2){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setTelefonNummer(txtItemEdit.getText());}
            }
            else if(index == 3){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setAddresse(txtItemEdit.getText());}
            }
            else if(index == 4){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setNotfallTelefon(txtItemEdit.getText());}
            }
            App.getKunden().remove(indexOfItem - 1);
            App.getKunden().add(indexOfItem - 1, k);
        }
        else if(!(geschKunde == null)){
            GeschaeftskundeModel k = (GeschaeftskundeModel) App.getKunden().get(indexOfItem - 1);
            
            if(index == 0){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setVorname(txtItemEdit.getText());}
            }
            else if(index == 1){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setNachname(txtItemEdit.getText());}
            }
            else if(index == 2){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setTelefonNummer(txtItemEdit.getText());}
            }
            else if(index == 3){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setAddresse(txtItemEdit.getText());}
            }
            else if(index == 4){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setArbeitsTelefon(txtItemEdit.getText());}
            }
            else if(index == 5){
                if("".equals(txtItemEdit.getText())){lblWarning.setText("Bitte machen sie eine Eingabe.");} else {
                k.setArbeitsAdresse(txtItemEdit.getText());}
            }
            App.getKunden().remove(indexOfItem - 1);
            App.getKunden().add(indexOfItem - 1, k);
            
        }
        else if(!(miete == null)){
            if(inMieteChangeMode){
                switch(mieteEditChoice){
                    case "Fahrzeug": 
                        App.getMieten().get(indexOfItem-1).setFahrzeug(chosenFahrzeugForMiete);
                        fillLsView();
                        break;
                    case "Kunde": 
                        App.getMieten().get(indexOfItem-1).setKunde(chosenKundeForMiete);
                        fillLsView();
                        break;

                }
                inMieteChangeMode = false;
            }
                else{
                switch(index){
                    case 0 : 
                        if(choiceBoxTime.getValue() == null || datePickEdit.getValue() == null){System.out.println("err"); lblWarning.setText("Beide Felder müssen ausgefüllt sein.");}
                        else{
                            LocalDate dateStart = datePickEdit.getValue();
                            LocalDateTime dateTimeStart = LocalDateTime.of(dateStart, createLocalTime());
                        if(dateTimeStart.isAfter(App.getCurrDateTime())){System.out.println("err"); lblWarning.setText("Das Startdatum darf nicht vor dem aktuellen Datum sein.");} else{
                        if(dateTimeStart.isAfter(App.getMieten().get(indexOfItem-1).getEndDate())){System.out.println("err"); lblWarning.setText("Das Startdatum darf nicht vor dem Enddatum sein.");} 
                            App.getMieten().get(indexOfItem-1).setStartDate(dateTimeStart);
                            App.getMieten().get(indexOfItem-1).calcZeitraum();
                            fillLsView();}}
                        break;
                    case 1 : 
                        if(choiceBoxTime.getValue() == null || datePickEdit.getValue() == null){System.out.println("err"); lblWarning.setText("Beide Felder müssen ausgefüllt sein.");}
                        else{
                            LocalDate dateEnd = datePickEdit.getValue();
                            LocalDateTime dateTimeEnd = LocalDateTime.of(dateEnd, createLocalTime());
                        if(dateTimeEnd.isBefore(App.getMieten().get(indexOfItem-1).getStartDate())){System.out.println("err"); lblWarning.setText("Das Enddatum darf nicht vor dem Startdatum sein.");}
                            App.getMieten().get(indexOfItem-1).setEndDate(dateTimeEnd);
                            App.getMieten().get(indexOfItem-1).calcZeitraum();
                            fillLsView();}
                        break;
                    
                    case 4 : 
                        lblWarning.setText("Dies kann nicht geändert werden.");
                                                
                        break; 
                    case 5 : 
                        lblWarning.setText("Dies kann nicht geändert werden.");
                        break;
                }
            }
        }
        else{
            System.out.println("err, auf kein item geklickt");
        }
        txtItemEdit.clear();
        
        //als Letztes wird noch die ListView geupdated.
        fillLsView();
    }
    
    
    public LocalTime createLocalTime(){
        LocalTime time = null;
        switch(choiceBoxTime.getValue()){
            case "01:00" : time = LocalTime.of(1, 0, 0, 0);break;
            case "02:00" : time = LocalTime.of(2, 0, 0, 0);break;
            case "03:00" : time = LocalTime.of(3, 0, 0, 0);break;
            case "04:00" : time = LocalTime.of(4, 0, 0, 0);break;
            case "05:00" : time = LocalTime.of(5, 0, 0, 0);break;
            case "06:00" : time = LocalTime.of(6, 0, 0, 0);break;
            case "07:00" : time = LocalTime.of(7, 0, 0, 0);break;
            case "08:00" : time = LocalTime.of(8, 0, 0, 0);break;
            case "09:00" : time = LocalTime.of(9, 0, 0, 0);break;
            case "10:00" : time = LocalTime.of(10, 0, 0, 0);break;
            case "11:00" : time = LocalTime.of(11, 0, 0, 0);break;
            case "12:00" : time = LocalTime.of(12, 0, 0, 0);break;
            case "13:00" : time = LocalTime.of(13, 0, 0, 0);break;
            case "14:00" : time = LocalTime.of(14, 0, 0, 0);break;
            case "15:00" : time = LocalTime.of(15, 0, 0, 0);break;
            case "16:00" : time = LocalTime.of(16, 0, 0, 0);break;
            case "17:00" : time = LocalTime.of(17, 0, 0, 0);break;
            case "18:00" : time = LocalTime.of(18, 0, 0, 0);break;
            case "19:00" : time = LocalTime.of(19, 0, 0, 0);break;
            case "20:00" : time = LocalTime.of(20, 0, 0, 0);break;
            case "21:00" : time = LocalTime.of(21, 0, 0, 0);break;
            case "22:00" : time = LocalTime.of(22, 0, 0, 0);break;
            case "23:00" : time = LocalTime.of(23, 0, 0, 0);break;
            case "24:00" : time = LocalTime.of(24, 0, 0, 0);break;
        }
        return time;
    }
    
    
    //button um zurück nach home zu kommen.
    @FXML
    private void btnHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }
    
    //befüllt die Listview in der Edit View 
    private void fillLsView(){
        lsItems.getItems().clear();
        //Abfrage ob das ausgewählte item, welches in der home view durch klick in die List view 
        //ausgewählt wurde, ein kunde oder ein fahrzeug ist.
        //dabei wird ein String in App abefragt, welcher diese Info zwischenspeichert.
        if("Fahrzeuge".equals(App.getChoiceForEditViewTyp())){
            //Es werden die Variablen welche aus der Superklasse FahrzeugModel kommen in die ListView hinzugefügt.
            //Ich habe Variablen rausgelassen welche nicht zum ändern gedacht sind.
            App.getFahrzeuge().get(indexOfItem - 1).returnAllVar().forEach(s -> {
                lsItems.getItems().add(s.toString());
            });
            
            switch(App.getFahrzeuge().get(indexOfItem - 1).typ){
                case "LKW" : lkw = (LkwModel) App.getFahrzeuge().get(indexOfItem - 1);
                    lsItems.getItems().add(Integer.toString(lkw.ladeRaum));
                    break;
                case "PKW" : pkw = (PkwModel) App.getFahrzeuge().get(indexOfItem - 1);
                    lsItems.getItems().add("Sitze: " + Integer.toString(pkw.sitze));
                    lsItems.getItems().add("PS: " + Integer.toString(pkw.ps));
                    break;
                case "Van" : van = (VanModel) App.getFahrzeuge().get(indexOfItem - 1);
                    if(van.getoenteScheiben){lsItems.getItems().add("Die Scheiben sind getönt.");}
                    else if(!(van.getoenteScheiben)){lsItems.getItems().add("Die Scheiben sind nicht getönt.");}
                    break;
                case "Anhaenger" : anhaenger = (AnhaengerModel) App.getFahrzeuge().get(indexOfItem - 1);
                    lsItems.getItems().add("BeladungsLimit: " + Integer.toString(anhaenger.beladungsLimit));
                    break;
            }
        }
        
        else if("Kunden".equals(App.getChoiceForEditViewTyp())){
            //Es werden die Variablen welche aus der Superklasse KundenModel kommen in die ListView hinzugefügt.
            //Ich habe Variablen rausgelassen welche nicht zum ändern gedacht sind.
            
            App.getKunden().get(indexOfItem - 1).returnAllVarEdit().forEach(s -> {
                lsItems.getItems().add(s.toString());
            });
            switch(App.getKunden().get(indexOfItem - 1).getKundenTyp()){
                    case "Geschäftskunde" : geschKunde = (GeschaeftskundeModel) App.getKunden().get(indexOfItem - 1);
                        lsItems.getItems().add(geschKunde.arbeitsAdresse);
                        lsItems.getItems().add(geschKunde.arbeitsTelefon);
                        break;
                    case "Privatkunde" : privKunde = (PrivatkundeModel) App.getKunden().get(indexOfItem - 1);
                        lsItems.getItems().add(privKunde.notfallTelefon);
                        break;
                }
            }
        
        else if("Miete".equals(App.getChoiceForEditViewTyp())){
            miete = App.getMieten().get(indexOfItem-1);
            miete.returnAllVar().forEach(s -> {
                lsItems.getItems().add(s.toString());
            });
        }
        
        
        System.out.println(pkw);
        System.out.println(lkw);
        System.out.println(van);
        System.out.println(anhaenger);
        System.out.println(privKunde);
        System.out.println(geschKunde);
    }    
    
    
    
    
    
    @FXML
    private void getListItemIndex(MouseEvent event) {
        lblWarning.setText("");
        txtItemEdit.setDisable(false);
        anwendenBtn.setDisable(false);
        index = lsItems.getFocusModel().getFocusedIndex();
        System.out.println(index);
        enableTextInput();
        if(inMieteChangeMode){
            index --;
            if(index == -1){} else {
            if("Fahrzeug".equals(mieteEditChoice)){
                txtItemEdit.setText(App.getFahrzeuge().get(index).hersteller + " " + App.getFahrzeuge().get(index).model);
                chosenFahrzeugForMiete = App.getFahrzeuge().get(index);
                //fillLsView();
            }
            else if("Kunde".equals(mieteEditChoice)){
                txtItemEdit.setText(App.getKunden().get(index).vorname + " " + App.getKunden().get(index).nachname);
                chosenKundeForMiete = App.getKunden().get(index);
                
                //fillLsView();
            }}
        }
        else{
            if(pkw != null){

                if(index == 0){
                    txtItemEdit.clear();
                    txtItemEdit.setText(pkw.hersteller);
                }
                else if(index == 1){
                    txtItemEdit.clear();
                    txtItemEdit.setText(pkw.model);
                }
                else if(index == 2){
                    txtItemEdit.clear();
                    txtItemEdit.setText(pkw.farbe);
                }
                else if(index == 3){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(pkw.inBenutzung);                
                }
                else if(index == 4){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(pkw.inReperatur);   
                }
                else if(index == 5){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(pkw.stundenKosten));
                }
                else if(index == 6){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(pkw.sitze));
                }
                else if(index == 7){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(pkw.ps));
                }


            }
            else if(lkw != null){
                if(index == 0){
                    txtItemEdit.clear();
                    txtItemEdit.setText(lkw.hersteller);
                }
                else if(index == 1){
                    txtItemEdit.clear();
                    txtItemEdit.setText(lkw.model);
                }
                else if(index == 2){
                    txtItemEdit.clear();
                    txtItemEdit.setText(lkw.farbe);
                }
                else if(index == 3){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(lkw.inBenutzung);
                }
                else if(index == 4){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(lkw.inReperatur); 
                }
                else if(index == 5){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(lkw.stundenKosten));
                }
                else if(index == 6){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(lkw.ladeRaum));
                }

            }
            else if(van != null){

                if(index == 0){
                    txtItemEdit.clear();
                    txtItemEdit.setText(van.hersteller);
                }
                else if(index == 1){
                    txtItemEdit.clear();
                    txtItemEdit.setText(van.model);
                }
                else if(index == 2){
                    txtItemEdit.clear();
                    txtItemEdit.setText(van.farbe);
                }
                else if(index == 3){
                    enableCheckBoxInput();
                    checkBox.setSelected(van.inBenutzung); 
                }
                else if(index == 4){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(van.inReperatur); 
                }
                else if(index == 5){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(van.stundenKosten));
                }
                else if(index == 6){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(van.getoenteScheiben); 
                }


            }
            else if(anhaenger != null){

                if(index == 0){
                    txtItemEdit.clear();
                    txtItemEdit.setText(anhaenger.hersteller);
                }
                else if(index == 1){
                    txtItemEdit.clear();
                    txtItemEdit.setText(anhaenger.model);
                }
                else if(index == 2){
                    txtItemEdit.clear();
                    txtItemEdit.setText(anhaenger.farbe);
                }
                else if(index == 3){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(anhaenger.inBenutzung); 
                }
                else if(index == 4){
                    txtItemEdit.clear();
                    enableCheckBoxInput();
                    checkBox.setSelected(anhaenger.inReperatur); 
                }
                else if(index == 5){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(anhaenger.stundenKosten));
                }
                else if(index == 6){
                    txtItemEdit.clear();
                    txtItemEdit.setText(Integer.toString(anhaenger.beladungsLimit));
                }

            }
            else if(privKunde != null){
                System.out.println("priv kunde");

                if(index == 0){
                    txtItemEdit.clear();
                    txtItemEdit.setText(privKunde.vorname);
                }
                else if(index == 1){
                    txtItemEdit.clear();
                    txtItemEdit.setText(privKunde.nachname);
                }
                else if(index == 2){
                    txtItemEdit.clear();
                    txtItemEdit.setText(privKunde.telefonNummer);
                }
                else if(index == 3){
                    txtItemEdit.clear();
                    txtItemEdit.setText(privKunde.addresse);
                }
                else if(index == 4){
                    txtItemEdit.clear();
                    txtItemEdit.setText(privKunde.notfallTelefon);
                }


            }
            else if(geschKunde != null){
                System.out.println("gesch kunde");

                if(index == 0){
                    txtItemEdit.clear();
                    txtItemEdit.setText(geschKunde.vorname);
                }
                else if(index == 1){
                    txtItemEdit.clear();
                    txtItemEdit.setText(geschKunde.nachname);
                }
                else if(index == 2){
                    txtItemEdit.clear();
                    txtItemEdit.setText(geschKunde.telefonNummer);
                }
                else if(index == 3){
                    txtItemEdit.clear();
                    txtItemEdit.setText(geschKunde.addresse);
                }
                else if(index == 4){
                    txtItemEdit.clear();
                    txtItemEdit.setText(geschKunde.arbeitsAdresse);
                }
                 else if(index == 5){
                    txtItemEdit.clear();
                    txtItemEdit.setText(geschKunde.arbeitsTelefon);
                }


            }
            else if(!(miete == null)){
                switch(index){
                    case 0: 
                        txtItemEdit.clear(); 
                        enableDateTimePick();
                        break;
                    case 1: 
                        txtItemEdit.clear();
                        enableDateTimePick();
                        break;
                    case 2: 
                        enableTextInput();
                        lsItems.getItems().clear();
                        mieteEditChoice = "Fahrzeug";
                        lsItems.getItems().add("   | Fahrzeuge |  ");
                        for(FahrzeugModel f : App.getFahrzeuge()){
                            if(f.isErhealtlich() == true){
                                lsItems.getItems().add(f.hersteller + " " + f.model + " " + f.farbe + " Typ: " + f.typ);}
                            }
                        inMieteChangeMode = true;
                        break;
                    case 3: 
                        enableTextInput();
                        lsItems.getItems().clear();
                        mieteEditChoice = "Kunde";
                        lsItems.getItems().add("   | Kunden |  ");
                        for(KundenModel k : App.getKunden()){
                            lsItems.getItems().add(k.vorname + " " + k.nachname + " " + k.telefonNummer + " " + k.kundenTyp);
                        }
                        inMieteChangeMode = true;
                        break;
                    case 4: 
                        System.out.println("nicht änderbar");
                        break;
                    case 5: 
                        System.out.println("nicht änderbar");
                        break;
                }
            }
            else{
                System.out.println("err, auf kein item geklickt");
            }
        }
    }

    private void enableTextInput(){
        txtItemEdit.setVisible(true);
        txtItemEdit.setDisable(false);
        
        choiceBoxTime.setDisable(true);
        choiceBoxTime.setVisible(false);
        datePickEdit.setDisable(true);
        datePickEdit.setVisible(false);
        
        checkBox.setDisable(true);
        checkBox.setVisible(false);
    }
    
    private void enableCheckBoxInput(){
        txtItemEdit.setVisible(false);
        txtItemEdit.setDisable(true);
        
        choiceBoxTime.setDisable(true);
        choiceBoxTime.setVisible(false);
        datePickEdit.setDisable(true);
        datePickEdit.setVisible(false);
        
        checkBox.setDisable(false);
        checkBox.setVisible(true);
        checkBox.setText(" ");
    }
    
    private void enableDateTimePick(){
        choiceBoxTime.setDisable(false);
        choiceBoxTime.setVisible(true);
        datePickEdit.setDisable(false);
        datePickEdit.setVisible(true);
        
        txtItemEdit.setVisible(false);
        txtItemEdit.setDisable(true);
        
        checkBox.setDisable(true);
        checkBox.setVisible(false);
    }
    
    public int getIndexOfItem() {
        return indexOfItem;
    }

    public void setIndexOfItem(int indexOfItem) {
        this.indexOfItem = indexOfItem;
    }

    public String getArrOfItem() {
        return arrOfItem;
    }

    public void setArrOfItem(String arrOfItem) {
        this.arrOfItem = arrOfItem;
    }

    public GeschaeftskundeModel getGeschKunde() {
        return geschKunde;
    }

    public void setGeschKunde(GeschaeftskundeModel geschKunde) {
        this.geschKunde = geschKunde;
    }

    public PrivatkundeModel getPrivKunde() {
        return privKunde;
    }

    public void setPrivKunde(PrivatkundeModel privKunde) {
        this.privKunde = privKunde;
    }

    public PkwModel getPkw() {
        return pkw;
    }

    public void setPkw(PkwModel pkw) {
        this.pkw = pkw;
    }

    public LkwModel getLkw() {
        return lkw;
    }

    public void setLkw(LkwModel lkw) {
        this.lkw = lkw;
    }

    public VanModel getVan() {
        return van;
    }

    public void setVan(VanModel van) {
        this.van = van;
    }

    public AnhaengerModel getAnhaenger() {
        return anhaenger;
    }

    public void setAnhaenger(AnhaengerModel anhaenger) {
        this.anhaenger = anhaenger;
    }

    public boolean isInMieteChangeMode() {
        return inMieteChangeMode;
    }

    public void setInMieteChangeMode(boolean inMieteChangeMode) {
        this.inMieteChangeMode = inMieteChangeMode;
    }
    
    
}
