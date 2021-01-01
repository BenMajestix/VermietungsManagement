/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
/**
 * FXML Controller class
 *
 * @author benbartel
 */
public class HinzufuegenController implements Initializable {


    @FXML
    private Label lblInstructions;
    @FXML
    private ChoiceBox<String> choiceErstellen;
    @FXML
    private TextField txtInput;
    @FXML
    private Button weiterBtn;
    @FXML
    private ListView<String> lvNeuesObjekt;
    @FXML
    private Button anwendenBtn;
    
    private int indexForInput;
    private String value0Input;
    private String value1Input;
    private String value2Input;
    private String value3Input;
    private String value4Input;
    private String value5Input;
    private String value6Input;
    private String value7Input;
    
    private int classToCreate;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label lblErklaerung;
    private boolean chosenInputMethod;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceErstellen.getItems().add("PKW");
        choiceErstellen.getItems().add("LKW");
        choiceErstellen.getItems().add("Van");
        choiceErstellen.getItems().add("Anhaenger");
        choiceErstellen.getItems().add("Geschaefts Kunde");
        choiceErstellen.getItems().add("Privat Kunde");
        
        weiterBtn.setDisable(true);
        txtInput.setDisable(true);
        
        indexForInput = 0;
        
        deactCheck();
        checkBox.setIndeterminate(true);
    }    
    
    
    
    
    @FXML
    private void btnWeiter(ActionEvent event) throws IOException {
        boolean inputEmpty = false;
        if(chosenInputMethod){
            if("".equals(txtInput.getText().strip())){
                inputEmpty = true;
            }
            else{inputEmpty = false;}
        }
        else if(!(chosenInputMethod)){
            if(checkBox.isIndeterminate()){
                inputEmpty = true;
            }
            else{inputEmpty = false;}
        }
        
        
        if(indexForInput == 0){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            //else if(checkForInt())
            else{
            value0Input = txtInput.getText();
            txtInput.clear();
            indexForInput ++;
            changePrompt();
            addToListView(value0Input);}
        }
        else if(indexForInput == 1){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            value1Input = txtInput.getText();
            txtInput.clear();
            indexForInput ++;
            changePrompt();
            addToListView(value1Input);}
        }
        else if(indexForInput == 2){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            value2Input = txtInput.getText();
            txtInput.clear();
            indexForInput ++;
            changePrompt();
            addToListView(value2Input);}
        }
        else if(indexForInput == 3){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            if(txtInput.isDisabled()){
            value3Input = Boolean.toString(checkBox.isSelected());
            checkBox.setIndeterminate(true);}
            else{value3Input = txtInput.getText();
            txtInput.clear();}
            indexForInput ++;
            changePrompt();
            addToListView(value3Input);}
        }
        else if(indexForInput == 4){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            if(txtInput.isDisabled()){
            value4Input = Boolean.toString(checkBox.isSelected());
            checkBox.setIndeterminate(true);}
            else{value4Input = txtInput.getText();
            txtInput.clear();}
            indexForInput ++;
            changePrompt();
            addToListView(value4Input);}
        }
        else if(indexForInput == 5){
            if(classToCreate == 5){beenden();}else{
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            value5Input = txtInput.getText();
            txtInput.clear();}
            indexForInput ++;
            changePrompt();
            addToListView(value5Input);}
        }
        else if(indexForInput == 6){
            if(classToCreate == 4){beenden();}else{
                if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
                else{
                if(txtInput.isDisabled()){
                    value6Input = Boolean.toString(checkBox.isSelected());
                checkBox.setIndeterminate(true);}
                else{
                    value6Input = txtInput.getText();
                    txtInput.clear();
            }   }
            indexForInput ++;
            changePrompt();
            addToListView(value6Input);}
        }
        else if(indexForInput == 7){
            if(classToCreate == 1 || classToCreate == 2 || classToCreate == 3){beenden();}else{
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            value7Input = txtInput.getText();
            txtInput.clear();}
            indexForInput ++;
            changePrompt();
            addToListView(value7Input);}
        }
        else{
            beenden();
        }
    }

    
    public void addToListView(String input){
        lvNeuesObjekt.getItems().add(input);
    }
    
    
    
    @FXML
    private void btnHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }

    public void beenden() throws IOException{
        if(classToCreate == 0){
            PkwModel p = new PkwModel(Integer.parseInt(value6Input), Integer.parseInt(value7Input), value0Input, value1Input, value2Input, true, Boolean.valueOf(value3Input), Boolean.valueOf(value4Input), Integer.parseInt(value5Input),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 1){
            LkwModel p = new LkwModel(Integer.parseInt(value6Input), value0Input, value1Input, value2Input, true, Boolean.valueOf(value3Input), Boolean.valueOf(value4Input), Integer.parseInt(value5Input),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 2){
            VanModel p = new VanModel(Boolean.valueOf(value6Input), value0Input, value1Input, value2Input, true, Boolean.valueOf(value3Input), Boolean.valueOf(value4Input), Integer.parseInt(value5Input),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 3){
            AnhaengerModel p = new AnhaengerModel(Integer.parseInt(value6Input), value0Input, value1Input, value2Input, true, Boolean.valueOf(value3Input), Boolean.valueOf(value4Input), Integer.parseInt(value5Input),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 4){
            GeschaeftskundeModel g = new GeschaeftskundeModel(value5Input, value4Input, value0Input, value1Input, App.getKunden().size() + 1, value2Input, value3Input);
            App.getKunden().add(g);
        }
        else if(classToCreate == 5){
            PrivatkundeModel g = new PrivatkundeModel(value4Input, value0Input, value1Input, App.getKunden().size() + 1, value2Input, value3Input);
            App.getKunden().add(g);
        }
        value0Input = null;
        value1Input = null;
        value2Input = null;
        value3Input = null;
        value4Input = null;
        value5Input = null;
        value6Input = null;
        value7Input = null;
        System.out.println("done");
        System.out.println(App.getKunden().get(App.getKunden().size()-1).nachname);
        System.out.println(App.getFahrzeuge().get(App.getFahrzeuge().size()-1).farbe);
        
        if(App.isHinzuDirFromMiete()){
            App.setRoot("mietenView");
        }
        else{
        App.setRoot("homeView");}
        
    }
    
    public void actCheck(){
        txtInput.setOpacity(0);
        txtInput.setDisable(true);
        checkBox.setOpacity(100);
        checkBox.setDisable(false);
        checkBox.setText(" ");
        chosenInputMethod = false;
    }
    public void deactCheck(){
        txtInput.setOpacity(100);
        txtInput.setDisable(false);
        checkBox.setOpacity(0);
        checkBox.setDisable(true);
        chosenInputMethod = true;
    }
    
    
    
    public void changePrompt(){
        lblInstructions.setText("");
        deactCheck();
        if(classToCreate == 0){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Hersteller:"); break;
                case 1: lblErklaerung.setText("Modell:"); break;
                case 2: lblErklaerung.setText("Farbe:"); break;
                case 3: lblErklaerung.setText("In Benutzung?"); actCheck(); break;
                case 4: lblErklaerung.setText("In Reperatur?"); actCheck(); break;
                case 5: lblErklaerung.setText("Stundenkosten:"); break;
                case 6: lblErklaerung.setText("Sitze:"); break;
                case 7: lblErklaerung.setText("PS:"); break;
                case 8: lblErklaerung.setText("Beenden"); 
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);break;
            }
        }
        else if(classToCreate == 1){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Hersteller:"); break;
                case 1: lblErklaerung.setText("Modell:"); break;
                case 2: lblErklaerung.setText("Farbe:"); break;
                case 3: lblErklaerung.setText("In Benutzung?"); actCheck(); break;
                case 4: lblErklaerung.setText("In Reperatur?"); actCheck(); break;
                case 5: lblErklaerung.setText("Stundenkosten:"); break;
                case 6: lblErklaerung.setText("Laderaum in m^3:"); break;
                case 7: lblErklaerung.setText("Beenden"); 
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);break;
            }
        }
        else if(classToCreate == 2){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Hersteller:"); break;
                case 1: lblErklaerung.setText("Modell:"); break;
                case 2: lblErklaerung.setText("Farbe:"); break;
                case 3: lblErklaerung.setText("In Benutzung?"); actCheck(); break;
                case 4: lblErklaerung.setText("In Reperatur?"); actCheck(); break;
                case 5: lblErklaerung.setText("Stundenkosten:"); break;
                case 6: lblErklaerung.setText("Getoente Scheiben?"); actCheck(); break;
                case 7: lblErklaerung.setText("Beenden");
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);break;
            }
        }
        else if(classToCreate == 3){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Hersteller:"); break;
                case 1: lblErklaerung.setText("Modell:"); break;
                case 2: lblErklaerung.setText("Farbe:"); break;
                case 3: lblErklaerung.setText("In Benutzung?"); actCheck(); break;
                case 4: lblErklaerung.setText("In Reperatur?"); actCheck(); break;
                case 5: lblErklaerung.setText("Stundenkosten:"); break;
                case 6: lblErklaerung.setText("Beladungslimit in kg:"); break;
                case 7: lblErklaerung.setText("Beenden");
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);break;
            }
        }
        else if(classToCreate == 4){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Vorname:"); break;
                case 1: lblErklaerung.setText("Nachname:"); break;
                case 2: lblErklaerung.setText("Tel. Nummer:"); break;
                case 3: lblErklaerung.setText("Addresse:"); break;
                case 4: lblErklaerung.setText("Geschäfts Tel.:"); break;
                case 5: lblErklaerung.setText("Geschäfts Addr.:"); break;
                case 6: lblErklaerung.setText("Beenden"); 
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);break;
            }
        }
        else if(classToCreate == 5){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Vorname:"); break;
                case 1: lblErklaerung.setText("Nachname:"); break;
                case 2: lblErklaerung.setText("Tel. Nummer:"); break;
                case 3: lblErklaerung.setText("Addresse:"); break;
                case 4: lblErklaerung.setText("Notfall Kontakt:"); break;
                case 5: lblErklaerung.setText("Beenden"); 
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);break;
            }
        }                                      
        
    }
    
    private void checkForInt(){
        
    }
    
    public void checkIntegerInput() throws InterruptedException{
        if("".equals(txtInput.getText())) {}
        else{
            if(classToCreate == 0){
                if(indexForInput == 5 || indexForInput == 6 || indexForInput == 7){
                    intTry();
                }
            }
            if(classToCreate == 1){
                if(indexForInput == 5 || indexForInput == 6){
                    intTry();
                }
            }
            if(classToCreate == 2 && indexForInput == 5){
                intTry();
            }
            if(classToCreate == 3){
                if(indexForInput == 5 || indexForInput == 6){
                    intTry();
                }
            }
        }
    }
    
    private void intTry(){
        try{
            int i = Integer.parseInt(txtInput.getText().strip() );
            changePrompt();
        }
        catch(NumberFormatException e){
            lblErklaerung.setText("Es dürfen nur Zahlen eingegeben werden.");
        }
        
    }
    
    @FXML
    private void btnAnwenden(ActionEvent event) {
        switch(choiceErstellen.getValue()){
            case "PKW" : classToCreate = 0; lblErklaerung.setText("Hersteller:"); break;
            case "LKW" : classToCreate = 1; lblErklaerung.setText("Hersteller:"); break;
            case "Van" : classToCreate = 2; lblErklaerung.setText("Hersteller:"); break;
            case "Anhaenger" : classToCreate = 3; lblErklaerung.setText("Hersteller:"); break;
            case "Geschaefts Kunde" : classToCreate = 4; lblErklaerung.setText("Vorname:"); break;
            case "Privat Kunde" : classToCreate = 5; lblErklaerung.setText("Vorname:"); break;
        }
        
        weiterBtn.setDisable(false);
        txtInput.setDisable(false);
        choiceErstellen.setDisable(true);
        anwendenBtn.setDisable(true);
        
        
    }

    public int getIndexForInput() {
        return indexForInput;
    }

    public void setIndexForInput(int indexForInput) {
        this.indexForInput = indexForInput;
    }

    @FXML
    private void keyCheck(KeyEvent event) {
        
        
    }

    @FXML
    private void checkInput(ActionEvent event) {
    }

    
    
    
}
