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
/**
 * FXML Controller class
 *
 * @author benbartel
 */
public class HinzufuegenController implements Initializable {
    @FXML
    private ListView<String> lvNeuesObjekt;
    @FXML
    private Label lblInstructions;
    @FXML
    private Label lblErklaerung;
    @FXML
    private ChoiceBox<String> choiceErstellen;
    @FXML
    private TextField txtInput;
    @FXML
    private Button anwendenBtn;
    @FXML
    private Button weiterBtn;
    @FXML
    private CheckBox checkBox;
    
    //An welcher Stelle des Erstellvorgangs der Benutzer gerade ist
    private int indexForInput;
    
    String[] inputValue;
    
    //Welches Objekt, also von welcher Klasse, es erstellt werden soll
    private int classToCreate;
    //Ob ein Input durch das Textfield oder durch die CheckBox passiert
    private boolean chosenInputMethod;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(App.isHinzuDirFromMiete() == 1){
            choiceErstellen.getItems().add("PKW");
            choiceErstellen.getItems().add("LKW");
            choiceErstellen.getItems().add("Van");
            choiceErstellen.getItems().add("Anhaenger");
            System.out.println("--Choices Erstellt Fahrzeuge"); 
        }
        else if(App.isHinzuDirFromMiete() == 2){
            choiceErstellen.getItems().add("Geschäftskunde");
            choiceErstellen.getItems().add("Privatkunde");
            System.out.println("--Choices Erstellt Kunden"); 
        }
        else{
            choiceErstellen.getItems().add("PKW");
            choiceErstellen.getItems().add("LKW");
            choiceErstellen.getItems().add("Van");
            choiceErstellen.getItems().add("Anhaenger");
            choiceErstellen.getItems().add("Geschäftskunde");
            choiceErstellen.getItems().add("Privatkunde");
            System.out.println("--Choices Erstellt"); 
        }
        
        
        
        //die Btns und textFelder werden disabled, damit es zu keinen Fehlern kommen kann.
        weiterBtn.setDisable(true);
        txtInput.setDisable(true);
        
        //Der Index wird hier initialisiert gesetzt um eine nullPointerException zu vermeiden
        indexForInput = 0;
        
        //Die Checkbox wird deaktiviert, da es immer zuerst TextInput gibt.
        deactCheck();
        
        //Die checkBox wird unentschlossen gesetzt, damit man erkennen kann ob der User eine Input gemacht hat.
        checkBox.setIndeterminate(true);
        
        //Es steht von sich aus Label dort, das wollen wir nicht haben.
        lblInstructions.setText(" ");
        
        //Hier wird das String Array initialisiert mit einer länge von 7, da es nicht mehr Strings halten muss
        inputValue = new String[7];
    }    
    
    
    
    //Der Button um das eingegebene zu bestätigen und zur nächsten Variable
    @FXML
    private void btnWeiter(ActionEvent event) throws IOException {
        boolean inputEmpty = false;
        
        //Wenn es gerade einen TextInput gibt, wird gecheckt ob das TextFeld leer ist. Dies wird dann gespeichert.
        if(chosenInputMethod){
            inputEmpty = "".equals(txtInput.getText().strip());
        }
        //Falls es gerade einen CheckBox Input gibt, wird geschaut ob die Checkbpx noch auf isIndeterminate ist, also es noch keinen Input gab
        else if(!(chosenInputMethod)){
            inputEmpty = checkBox.isIndeterminate();
        }
        
        
        //switch, an welchem punkt der erstellung der nutzer ist.        
        if(indexForInput == 0){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            //else if(checkForInt())
            else{
                //Der Input wird als String in dem entsprechendem String gespeichert
                inputValue[0] = txtInput.getText();
                txtInput.clear();
                indexForInput ++;
                changePrompt();
                //Der Input wird dann noch in der ListView gespeichert.
                addToListView(inputValue[0]);}
        }
        else if(indexForInput == 1){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
                inputValue[1] = txtInput.getText();
                txtInput.clear();
                indexForInput ++;
                changePrompt();
                addToListView(inputValue[1]);}
        }
        else if(indexForInput == 2){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
                inputValue[2] = txtInput.getText();
                txtInput.clear();
                indexForInput ++;
                changePrompt();
                addToListView(inputValue[2]);}
        }
        else if(indexForInput == 3){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            if(txtInput.isDisabled()){
                inputValue[3] = Boolean.toString(checkBox.isSelected());
                checkBox.setIndeterminate(true);}
            else{inputValue[3] = txtInput.getText();
                txtInput.clear();}
                indexForInput ++;
                changePrompt();
                addToListView(inputValue[3]);}
        }
        else if(indexForInput == 4){
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            if(txtInput.isDisabled()){
                inputValue[4] = Boolean.toString(checkBox.isSelected());
                checkBox.setIndeterminate(true);}
            else{inputValue[4] = txtInput.getText();
                txtInput.clear();}
                indexForInput ++;
                changePrompt();
                addToListView(inputValue[4]);}
        }
        else if(indexForInput == 5){
            if(classToCreate == 5){beenden();}else{
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
                inputValue[5] = txtInput.getText();
                txtInput.clear();}
                indexForInput ++;
                changePrompt();
                addToListView(inputValue[5]);}
        }
        else if(indexForInput == 6){
            if(classToCreate == 4){beenden();}else{
                if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
                else{
                if(txtInput.isDisabled()){
                    inputValue[6] = Boolean.toString(checkBox.isSelected());
                    checkBox.setIndeterminate(true);}
                else{
                    inputValue[6] = txtInput.getText();
                    txtInput.clear();
            }   }
            indexForInput ++;
            changePrompt();
            addToListView(inputValue[6]);}
        }
        else if(indexForInput == 7){
            if(classToCreate == 1 || classToCreate == 2 || classToCreate == 3){beenden();}else{
            if(inputEmpty){lblErklaerung.setText("Bitte machen Sie eine Eingabe.");}
            else{
            inputValue[7] = txtInput.getText();
            txtInput.clear();}
            indexForInput ++;
            changePrompt();
            addToListView(inputValue[7]);}
        }
        else{
            beenden();
        }
    }

    //Eine helfer Funktion um das hinzufügen in die listView einfacher zu machen
    public void addToListView(String input){
        lvNeuesObjekt.getItems().add(input);
    }
    
    
    
    @FXML
    private void btnHome(ActionEvent event) throws IOException {
        App.setRoot("homeView");
    }

    //Wird ausgeführt wenn der User auf den Beenden btn klickt
    public void beenden() throws IOException{
        //Je nachdem welche Klasse ausgewählt ist, wird das jeweilige Objekt erstellt und der App ArrayList hinzugefügt
        if(classToCreate == 0){
            PkwModel p = new PkwModel(Integer.parseInt(inputValue[6]), Integer.parseInt(inputValue[7]), inputValue[0], inputValue[1], inputValue[2], true, Boolean.valueOf(inputValue[3]), Boolean.valueOf(inputValue[4]), Integer.parseInt(inputValue[5]),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 1){
            LkwModel p = new LkwModel(Integer.parseInt(inputValue[6]), inputValue[0], inputValue[1], inputValue[2], true, Boolean.valueOf(inputValue[3]), Boolean.valueOf(inputValue[4]), Integer.parseInt(inputValue[5]),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 2){
            VanModel p = new VanModel(Boolean.valueOf(inputValue[6]), inputValue[0], inputValue[1], inputValue[2], true, Boolean.valueOf(inputValue[3]), Boolean.valueOf(inputValue[4]), Integer.parseInt(inputValue[5]),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 3){
            AnhaengerModel p = new AnhaengerModel(Integer.parseInt(inputValue[6]), inputValue[0], inputValue[1], inputValue[2], true, Boolean.valueOf(inputValue[3]), Boolean.valueOf(inputValue[4]), Integer.parseInt(inputValue[5]),  App.getFahrzeuge().size() + 1);
            App.getFahrzeuge().add(p);
        }
        else if(classToCreate == 4){
            GeschaeftskundeModel g = new GeschaeftskundeModel(inputValue[5], inputValue[4], inputValue[0], inputValue[1], App.getKunden().size() + 1, inputValue[2], inputValue[3]);
            App.getKunden().add(g);
        }
        else if(classToCreate == 5){
            PrivatkundeModel g = new PrivatkundeModel(inputValue[4], inputValue[0], inputValue[1], App.getKunden().size() + 1, inputValue[2], inputValue[3]);
            App.getKunden().add(g);
        }
        System.out.println("--Objekt erstellt");
        System.out.println(App.getKunden().get(App.getKunden().size()-1).nachname);
        System.out.println(App.getFahrzeuge().get(App.getFahrzeuge().size()-1).farbe);
        
        //Falls der User aus mietenView hergeleitet wurde, werden Sie auch dorthin zurückgeschickt
        if(App.isHinzuDirFromMiete() == 1 || App.isHinzuDirFromMiete() == 2){
            App.setRoot("mietenView");
        }
        else{
            //Falls nicht, dann nach Hause
        App.setRoot("homeView");}
        
    }
    //Activates CheckBox
    public void actCheck(){
        txtInput.setOpacity(0);
        txtInput.setDisable(true);
        checkBox.setOpacity(100);
        checkBox.setDisable(false);
        checkBox.setText(" ");
        chosenInputMethod = false;
        
    }
    //Deactivates CheckBox
    public void deactCheck(){
        txtInput.setOpacity(100);
        txtInput.setDisable(false);
        checkBox.setOpacity(0);
        checkBox.setDisable(true);
        chosenInputMethod = true;
    }
    
    
    //Ändert den Text des Labels anhand der zu erstellenden Klasse und dem Fortschritt in der Erstellung des Objekts
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
                case 8: lblErklaerung.setVisible(false);
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);
                txtInput.setVisible(false);break;
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
                case 7:lblErklaerung.setVisible(false);
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);
                txtInput.setVisible(false);break;
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
                case 7: lblErklaerung.setVisible(false);
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);
                txtInput.setVisible(false);break;
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
                case 7:lblErklaerung.setVisible(false);
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);
                txtInput.setVisible(false);break;
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
                case 6:
                    lblErklaerung.setVisible(false);
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);
                txtInput.setVisible(false);break;
            }
        }
        else if(classToCreate == 5){
            switch(indexForInput){
                case 0: lblErklaerung.setText("Vorname:"); break;
                case 1: lblErklaerung.setText("Nachname:"); break;
                case 2: lblErklaerung.setText("Tel. Nummer:"); break;
                case 3: lblErklaerung.setText("Addresse:"); break;
                case 4: lblErklaerung.setText("Notfall Kontakt:"); break;
                case 5: 
                lblErklaerung.setVisible(false);
                weiterBtn.setText("Beenden");
                txtInput.setDisable(true);
                txtInput.setVisible(false);
                        break;
            }
        }                                      
        
    }
    
    //Falls der String ein Integer ist, wird true wiedergegeben, also checkt er ob ein String in einen Int umgewandelt werden kann.
    private boolean checkForInt(){
        boolean isInt = true;
        if("".equals(txtInput.getText())) {}
        else{
            if(classToCreate == 0){
                if(indexForInput == 5 || indexForInput == 6 || indexForInput == 7){
                    //Er versucht einen Integer aus einem String zu machen, falls das nicht klappt wird false returned
                    try{
                        int i = Integer.parseInt(txtInput.getText().strip());
                    }
                    catch(NumberFormatException e){
                        isInt = false;
                    }
                }
            }
            if(classToCreate == 1){
                if(indexForInput == 5 || indexForInput == 6){
                    try{
                        int i = Integer.parseInt(txtInput.getText().strip());
                    }
                    catch(NumberFormatException e){
                        isInt = false;
                    }
                }
            }
            if(classToCreate == 2 && indexForInput == 5){
                try{
                    int i = Integer.parseInt(txtInput.getText().strip());
                }
                catch(NumberFormatException e){
                    isInt = false;
                }
            }
            if(classToCreate == 3){
                if(indexForInput == 5 || indexForInput == 6){
                    try{
                        int i = Integer.parseInt(txtInput.getText().strip());
                    }
                    catch(NumberFormatException e){
                        isInt = false;
                    }
                }
            }
        }
        return isInt;
    }
    
    //Wird aufgerufen, wenn es einen KeyBoard Input gab.
    public void checkIntegerInput(){
        if(!(checkForInt())){
            //Wenn der Input kein Integer war, gibt es eine Warnung
            lblErklaerung.setText("Es dürfen nur Zahlen eingegeben werden.");
            //Das nicht passende zeichen wird entfernt und der Caret wird am ende des Textes platziert
            //Der Caret ist das ding was einem anzeigt wo im text man sich befinded
            String edit = txtInput.getText().substring(0, txtInput.getText().length()-1);
            txtInput.setText(edit);
            txtInput.positionCaret(txtInput.getText().length());
        }
        else{changePrompt();}
    }
    
    
    
    
    //Es wird ermittelt, welches Objekt erstellt werden soll und die entsprechend Variablen werden gesetzt um es später zu identifizieren
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
    private void checkInput(ActionEvent event) {
    }

    
    
    
}
