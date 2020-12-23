/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author benbartel
 */
public class HomeController implements Initializable {

    @FXML
    private ListView<String> lsMain;
    @FXML
    private ChoiceBox<String> choiceList;
    @FXML
    private ChoiceBox<String> choiceTyp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //die choice box fürs auswählen der Superklasse wird mit den Optionen befüllt
        choiceList.getItems().add("Kunden");
        choiceList.getItems().add("Fahrzeuge");
        choiceList.setValue("Kunden");
        
        
        fillLsView();
    }    

    
    
    @FXML
    public void fillLsView(){
        //falls keine option in der choice box ausgewählt wurde cleared sich die listview
        if(null == getChoice(choiceTyp)){
            lsMain.getItems().clear();
        }
        
        //jenachdem welche choice ausgewählt ist, kann man sich alle oder nur bestimmte arten von Fahrzeugen oder kunden anzeigen lassen.
        else switch (getChoice(choiceTyp)) {
            case "PKW":
                //die listView wird zuerst gecleared, damit nicht mehrere items doppelt erscheinen
                lsMain.getItems().clear();
                //hier werden alle objekte aus der Fahrzeuge ArrayList ausgegeben und dann überprüft ob sie der gewählten Klasse entsprechen.
                for(FahrzeugModel f : App.getFahrzeuge()){
                    if("PKW".equals(f.getTyp())){
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " FahrzeugNummer: " + f.getFahrzeugNummer());
                    }
                }   break;
            case "LKW":
                System.out.println("test2");
                lsMain.getItems().clear();
                for(FahrzeugModel f : App.getFahrzeuge()){
                    if("LKW".equals(f.getTyp())){
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " FahrzeugNummer: " + f.getFahrzeugNummer());
                    }
                }   break;
            case "Van":
                lsMain.getItems().clear();
                for(FahrzeugModel f : App.getFahrzeuge()){
                    if("Van".equals(f.getTyp())){
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " FahrzeugNummer: " + f.getFahrzeugNummer());
                    }
                }   break;
            case "Anhänger":
                lsMain.getItems().clear();
                for(FahrzeugModel f : App.getFahrzeuge()){
                    if("Anhaenger".equals(f.getTyp())){
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " FahrzeugNummer: " + f.getFahrzeugNummer());
                    }
                }   break;
            case "Alle Fahrzeuge":
                lsMain.getItems().clear();
                for(FahrzeugModel f : App.getFahrzeuge()){
                    lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " FahrzeugNummer: " + f.getFahrzeugNummer());
                }   break;
                
            case "Privatkunde":
                lsMain.getItems().clear();
                for(KundenModel f : App.getKunden()){
                    if("Privatkunde".equals(f.getKundenTyp())){
                        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Telefonnummer: " + f.telefonNummer + " Kundennummer : " + f.kundenNummer);
                    }
                }   break;
            case "Geschäftskunde":
                lsMain.getItems().clear();
                for(KundenModel f : App.getKunden()){
                    if("Geschäftskunde".equals(f.getKundenTyp())){
                        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Telefonnummer: " + f.telefonNummer + " Kundennummer : " + f.kundenNummer);
                    }
                }   break;
            case "Alle Kunden":
                lsMain.getItems().clear();
                for(KundenModel f : App.getKunden()){
                    lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Telefonnummer: " + f.telefonNummer + " Kundennummer : " + f.kundenNummer);
                }   break;
                
            default:
                System.out.println("hallko");
                lsMain.getItems().clear();
                break;
        }
    }
    
    
    
    @FXML
    private void btnFahrzeugAdd(ActionEvent event) throws IOException {
        App.setRoot("hinzufuegenView");
    }

    
    
    
    
    //gibt die ausgewählte Option der ersten Choice box wieder.
    private String getChoice(ChoiceBox<String> choiceTyp){
        String choice = choiceTyp.getValue();
        return choice;
    }
    
    //diese methode ändert, anhand der choice aus der ersten choice box, die choices der zweiten choice box.
    private void refrChoice(){
        choiceTyp.getItems().clear();
        //überprüft welche superklasse ausgewählt ist und gibt dementsprechend mehr choices in der zweiten choice box
        if("Fahrzeuge".equals(choiceList.getValue())){
            choiceTyp.getItems().add("PKW");
            choiceTyp.getItems().add("LKW");
            choiceTyp.getItems().add("Van");
            choiceTyp.getItems().add("Anhänger");
            choiceTyp.getItems().add("Alle Fahrzeuge");
            choiceTyp.setValue("Alle Fahrzeuge");
        }
        else if("Kunden".equals(choiceList.getValue())){
            choiceTyp.getItems().add("Privatkunde");
            choiceTyp.getItems().add("Geschäftskunde");
            choiceTyp.getItems().add("Alle Kunden");
            choiceTyp.setValue("Alle Kunden");
        }
    }

    
    @FXML
    private void btnAnwendenList(ActionEvent event) {
        fillLsView();
    }

    @FXML
    private void btnAnwendenChoice(ActionEvent event) throws InterruptedException {
        refrChoice();
    }

    //diese methode wird aktiviert, wenn auf die listview geklickt wird.
    @FXML
    private void getListItemIndex(MouseEvent event){
        //um err zu vermeiden wird, falls der nutzer auf kein Item geklickt hat, nichts ausgeführt.
        if(lsMain.getFocusModel().getFocusedItem() == null){System.out.println("nichts ausgewählt");}
        else{
            //falls auf ein item geklickt wurde, wird der string des Items zwischengespeichert.
            String index = lsMain.getFocusModel().getFocusedItem();
            //dann wird das letze wort aus dem langen string herausgeschnitten
            //hiermit ermitteln wir, da das letzte wort eine zahl ist, welche repräsentiert, an welcher stelle das objekt in seiner Listview ist und wo das objekt in der arrList ist.
            //durch diese methode herauszufinden welches Objekt man auswählt ist praktisch, da man dann die Listview nach belieben sortieren kann, wie ich es auch tue
            String lastWord = index.substring(index.lastIndexOf(" ")+1);
            //diese zahl wird noch als int gespeichert damit man leichter damit arbeiten kann.
            int indexForEdit = Integer.parseInt(lastWord);
            
            System.out.println(indexForEdit);
            
            if("Fahrzeuge".equals(choiceList.getValue())){
                //falls das ausgewählte objekt ein fahrzeug ist, wird dies in app gespeichert, damit es vom editController gut auslesbar ist.
                App.setChoiceForEditViewTyp("Fahrzeuge");
                
            }
            else if("Kunden".equals(choiceList.getValue())){
                //hier auch, nur mit Kunden.
                App.setChoiceForEditViewTyp("Kunden");
                
            }
            //es wird noch der Index des Ausgewählten Objekts in App gespeichert für den editController
            App.setChoiceForEditViewIndex(indexForEdit);
            
            //hier wird nur die editView aufgerufen.
            try {
                toEdit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void toEdit() throws IOException{
        App.setRoot("editView");
    }

    @FXML
    private void btnNeueMiete(ActionEvent event) throws IOException {
        App.setRoot("mietenView");
    }
}
