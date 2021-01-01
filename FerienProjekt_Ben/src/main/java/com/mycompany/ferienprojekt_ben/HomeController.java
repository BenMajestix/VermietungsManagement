/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private Label lblCurrTime;
    @FXML
    private Label lblCurrDate;
    
    private boolean inObjectView;
    private boolean inSearch;
    
    private FahrzeugModel chosenFahrzeug;
    private KundenModel chosenKunde;
    private mietenModel chosenMiete;
    
    private int superClassChoice;
    private int subClassChoice;
    
    private ArrayList<Integer> searchResultSuperClass;
    @FXML
    private MenuButton menuSuperClass;
    @FXML
    private MenuItem menuItemFahrz;
    @FXML
    private MenuItem menuItemKunde;
    @FXML
    private MenuItem menuItemMiete;
    @FXML
    private MenuButton menuSubClass;
    @FXML
    private MenuItem menuItemSub1;
    @FXML
    private MenuItem menuItemSub2;
    @FXML
    private MenuItem menuItemSub3;
    @FXML
    private MenuItem menuItemSub4;
    @FXML
    private MenuItem menuItemSub5;
    @FXML
    private TextField searchbar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //die choice box fürs auswählen der Superklasse wird mit den Optionen befüllt
        menuSuperClass.setText("Bitte Auswählen");
        menuSubClass.setText("Bitte Auswählen");
        menuSubClass.setDisable(true);
        menuItemFahrz.setText("Fahrzeuge");
        menuItemKunde.setText("Kunden");
        menuItemMiete.setText("Mieten");
        subClassChoice = 0;
        searchResultSuperClass = new ArrayList(); 
        
        fillLsView();
        
        lblCurrDate.setText(App.getCurrDateTime().getDayOfMonth() + ". " + App.getCurrDateTime().getMonth());
        lblCurrTime.setText(App.getCurrDateTime().getHour() + ":" + App.getCurrDateTime().getMinute() + " Uhr");
        
        inObjectView = false;
        
        
        checkMietenAblauf();
        
    }    
    
    public void reloadDateTime(){
        lblCurrDate.setText(App.getCurrDateTime().getDayOfMonth() + ". " + App.getCurrDateTime().getMonth());
        lblCurrTime.setText(App.getCurrDateTime().getHour() + ":" + App.getCurrDateTime().getMinute() + " Uhr");
        checkMietenAblauf();
    }
    
    
    public void fillLsView(){
        inSearch = false;
        switch(subClassChoice){
            case 0: lsMain.getItems().clear(); break;
            case 1: 
                System.out.println("sub1");
                System.out.println(superClassChoice);
                switch(superClassChoice){
                    case 1: 
                        System.out.println("super 1");
                        //die listView wird zuerst gecleared, damit nicht mehrere items doppelt erscheinen
                        lsMain.getItems().clear();
                        //hier werden alle objekte aus der Fahrzeuge ArrayList ausgegeben und dann überprüft ob sie der gewählten Klasse entsprechen.
                        App.getFahrzeuge().stream().filter(f -> ("PKW".equals(f.getTyp()))).forEachOrdered(f -> {
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                        });
                        menuSubClass.setText("PKW");
                        break;
                    case 2: 
                        System.out.println("super 2");
                        lsMain.getItems().clear();
                        App.getKunden().stream().filter(f -> ("Privatkunde".equals(f.getKundenTyp()))).forEachOrdered(f -> {
                        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Tel.: " + f.telefonNummer + " K.Num.: " + f.kundenNummer);
                        });
                        menuSubClass.setText("Privat Kunden");
                        break;

                    case 3: 
                        System.out.println("super 3");
                        lsMain.getItems().clear();
                        App.getMieten().stream().filter(m -> (!(m.isAbgelaufen()))).forEachOrdered(m -> {
                        lsMain.getItems().add("Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer());
                        });
                        menuSubClass.setText("Laufende Mieten");
                        break;
                }
            break; 
            case 2: 
                System.out.println("sub2");
                System.out.println(superClassChoice);
                switch(superClassChoice){
                    case 1: 
                        lsMain.getItems().clear();
                        App.getFahrzeuge().stream().filter(f -> ("LKW".equals(f.getTyp()))).forEachOrdered(f -> {
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                        }); 
                        menuSubClass.setText("LKW");
                        break;
                    case 2:
                        lsMain.getItems().clear();
                        App.getKunden().stream().filter(f -> ("Geschäftskunde".equals(f.getKundenTyp()))).forEachOrdered(f -> {
                        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Tel.: " + f.telefonNummer + " K.Num.: " + f.kundenNummer);
                        });
                        menuSubClass.setText("Geschäftskunden");
                        break;

                    case 3:
                        lsMain.getItems().clear();
                        App.getMieten().stream().filter(m -> (m.isAbgelaufen())).forEachOrdered(m -> {
                        lsMain.getItems().add("Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer());
                        });
                        menuSubClass.setText("Abgeschlossene Mieten");
                        break;
                }
            break; 
                
            case 3: 
                System.out.println("sub3");
                System.out.println(superClassChoice);
                switch(superClassChoice){
                    case 1:
                        lsMain.getItems().clear();
                        App.getFahrzeuge().stream().filter(f -> ("Van".equals(f.getTyp()))).forEachOrdered(f -> {
                        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                        });
                        menuSubClass.setText("Van");
                        break;
                    case 2:
                        lsMain.getItems().clear();
                        App.getKunden().forEach(f -> {
                        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Tel.: " + f.telefonNummer + " K.Num.: " + f.kundenNummer);
                        });
                        menuSubClass.setText("Alle Kunden");
                        break;

                    case 3:
                        lsMain.getItems().clear();
                        App.getMieten().forEach(m -> {
                        lsMain.getItems().add("Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer());
                        });
                        menuSubClass.setText("Alle Mieten");
                        break;
                }
            break; 
            case 4: 
                System.out.println("sub4");
                System.out.println(superClassChoice);
                lsMain.getItems().clear();
                    App.getFahrzeuge().stream().filter(f -> ("Anhaenger".equals(f.getTyp()))).forEachOrdered(f -> {
                    lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                    });
                    menuSubClass.setText("Anhänger");
            break; 
            
            case 5: 
                System.out.println("sub5");
                System.out.println(superClassChoice);
                lsMain.getItems().clear();
                App.getFahrzeuge().forEach(f -> {
                lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                });
                menuSubClass.setText("Alle Fahrzeuge"); 
            break; 
        }

    }
    
    
    @FXML
    private void searchStart(KeyEvent event) {
        inSearch = true;
        ArrayList<SearchResultModel> hits = new ArrayList();
        
        lsMain.getItems().clear();
        searchResultSuperClass.clear();
        
        String keyWord = searchbar.getText();
        String[] keyWordArr;
        
        keyWordArr = keyWord.split(" ", -1);
        
        System.out.println("fahrzeuge");
        for(FahrzeugModel f : App.getFahrzeuge()){
            System.out.println(f.model);
            boolean keyWordFound = false;
            int trefferScore = 0;
            
            for (int i = 0; i < keyWordArr.length; i++){
                for(var s : f.returnAllVar()){
                    String checkStringLow = s.toString().toLowerCase();
                    
                    if(s.toString().equals(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 12;
                        System.out.println("exact hit");
                    }
                    if(checkStringLow.equals(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore = trefferScore + 5;
                        System.out.println("exact hit in lower");
                    }
                    if(s.toString().contains(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 2;
                        System.out.println("hit");
                    }
                    if(checkStringLow.contains(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore ++;
                        System.out.println("hit in lowercase");
                    }
                }
            }
            String output = "Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer();
            if(keyWordFound){
                SearchResultModel hit = new SearchResultModel(output, trefferScore, 1);
                hits.add(hit);
                System.out.println(hit.searchScore);
            }
        }
        System.out.println("kunden");
        for(KundenModel k : App.getKunden()){
            System.out.println(k.vorname);
            boolean keyWordFound = false;
            int trefferScore = 0;
            
            for (int i = 0; i < keyWordArr.length; i++){
                for(var s : k.returnAllVar()){
                    String checkStringLow = s.toString().toLowerCase();
                    
                    if(s.toString().equals(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 12;
                        System.out.println("exact hit");
                    }
                    if(checkStringLow.equals(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore = trefferScore + 5;
                        System.out.println("exact hit in lower");
                    }
                    if(s.toString().contains(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 2;
                        System.out.println("hit");
                    }
                    if(checkStringLow.contains(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore ++;
                        System.out.println("hit in lowercase");
                    }
                }
            }
            String output = "Name: " + k.vorname + " " + k.nachname + " " + " Tel.: " + k.telefonNummer + " K.Num.: " + k.kundenNummer;
            if(keyWordFound){
                SearchResultModel hit = new SearchResultModel(output, trefferScore, 1);
                hits.add(hit);
                System.out.println(hit.searchScore);
            }
        }
        System.out.println("mieten");
        for(mietenModel m : App.getMieten()){
            System.out.println(m.getKunde().nachname);
            boolean keyWordFound = false;
            int trefferScore = 0;
            
            for (int i = 0; i < keyWordArr.length; i++){
                for(var s : m.returnAllVar()){
                    String checkStringLow = s.toString().toLowerCase();
                    
                    if(s.toString().equals(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 12;
                        System.out.println("exact hit");
                    }
                    if(checkStringLow.equals(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore = trefferScore + 5;
                        System.out.println("exact hit in lower");
                    }
                    if(s.toString().contains(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 2;
                        System.out.println("hit");
                    }
                    if(checkStringLow.contains(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore ++;
                        System.out.println("hit in lowercase");
                    }
                    
                }
            }
            String output = "Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer();
            if(keyWordFound){
                SearchResultModel hit = new SearchResultModel(output, trefferScore, 1);
                hits.add(hit);
                System.out.println(hit.searchScore);
            }
        }
        sortResults(hits, keyWord);
    }
    
    private void sortResults(ArrayList<SearchResultModel> hits, String keyWord){
        Collections.sort(hits, new Comparator<SearchResultModel>() {
            @Override
            public int compare(SearchResultModel o1, SearchResultModel o2) {
                return Double.compare(o1.getSearchScore(), o2.getSearchScore());
            }
        });
        Collections.reverse(hits);
        for(SearchResultModel s : hits){
            
            switch(s.superClass){
                case 1:
                    lsMain.getItems().add(s.output);
                    searchResultSuperClass.add(1);
                    break;
                case 2:
                    lsMain.getItems().add(s.output);
                    searchResultSuperClass.add(2);
                    break;
                case 3:
                    lsMain.getItems().add(s.output);
                    searchResultSuperClass.add(3);
                    break;
            }
        }
        System.out.println("stop");
    }
    
    
    
    
    @FXML
    private void btnFahrzeugAdd(ActionEvent event) throws IOException {
        App.setRoot("hinzufuegenView");
        System.out.println("works");
    }

    
    

    //diese methode wird aktiviert, wenn auf die listview geklickt wird.
    @FXML
    private void getListItemIndex(MouseEvent event) throws IOException{
        
        //um err zu vermeiden wird, falls der nutzer auf kein Item geklickt hat, nichts ausgeführt.
        if(lsMain.getFocusModel().getFocusedItem() == null){System.out.println("nichts ausgewählt");}
        else{
            if(inObjectView == true){
                //ZURÜCK BTN in listview in der details ansicht
                if(lsMain.getFocusModel().getFocusedIndex() == lsMain.getItems().size() - 1){
                    inObjectView = false;
                    fillLsView();
                    chosenFahrzeug = null;
                    chosenKunde = null;
                }
                //bearbeiten btn in der listview details ansicht
                else if(lsMain.getFocusModel().getFocusedIndex() == lsMain.getItems().size() - 2){
                    if(!(chosenFahrzeug == null)){
                        //falls das ausgewählte objekt ein fahrzeug ist, wird dies in app gespeichert, damit es vom editController gut auslesbar ist.
                        App.setChoiceForEditViewTyp("Fahrzeuge");
                        App.setChoiceForEditViewIndex(chosenFahrzeug.fahrzeugNummer);
                        toEdit();
                        inObjectView = false;
                    }
                    else if(!(chosenKunde == null)){
                        //hier auch, nur mit Kunden.
                        App.setChoiceForEditViewTyp("Kunden"); 
                        App.setChoiceForEditViewIndex(chosenKunde.kundenNummer);
                        toEdit();
                        inObjectView = false;
                    }
                    else if(!(chosenMiete == null)){
                        App.setChoiceForEditViewTyp("Miete"); 
                        App.setChoiceForEditViewIndex(chosenMiete.getMietenNummer());
                        toEdit();
                    }
                }

            }
            else if(inSearch){
                inObjectView = true;
                int index = lsMain.getFocusModel().getFocusedIndex();
                int superClass = searchResultSuperClass.get(index);
                //falls auf ein item geklickt wurde, wird der inhalt der zeile als String zwischengespeichert.
                String line = lsMain.getFocusModel().getFocusedItem().trim();
                //dann nehme ich mir das letzte wort der zeile, welches immer der index des gewählten objekts in der jeweiligen ArrayList ist.
                String[] words = line.split(" ");
                String lastWord = words[words.length - 1];
                //dieser Index wird noch zum einfacherem Bearbeiten als integer gespeichert.
                int indexForEdit = Integer.parseInt(lastWord);
                //searchResultSuperClass
                if(superClass == 1){
                    //es wird aus der choice box ausgelesen welche klasse ausgewählt ist
                    //daraufhin wird dann das objekt mit dem index hier gespeichert
                    chosenFahrzeug = App.getFahrzeuge().get(indexForEdit-1);
                    //mit dieser methode wird die listview gelehrt und gefüllt mit allen variablen des objekts
                    fillListViewItem();
                    System.out.println("details fahrzeug");
                }
                else if(superClass == 2){
                    chosenKunde = App.getKunden().get(indexForEdit-1);
                    fillListViewItem();
                    System.out.println("details kunden");
                }
                else if(superClass == 3){
                    chosenMiete = App.getMieten().get(indexForEdit - 1);
                    fillListViewItem();
                    System.out.println("details mieten");
                }
            }
            
            //wird ausgeführt wenn man in normal ansicht ist, also nicht details ansicht
            else{
                inObjectView = true;
                //falls auf ein item geklickt wurde, wird der inhalt der zeile als String zwischengespeichert.
                String line = lsMain.getFocusModel().getFocusedItem().trim();
                //dann nehme ich mir das letzte wort der zeile, welches immer der index des gewählten objekts in der jeweiligen ArrayList ist.
                String[] words = line.split(" ");
                String lastWord = words[words.length - 1];
                //dieser Index wird noch zum einfacherem Bearbeiten als integer gespeichert.
                int indexForEdit = Integer.parseInt(lastWord);

                System.out.println("index: " + indexForEdit);

                if(superClassChoice == 1){
                    //es wird aus der choice box ausgelesen welche klasse ausgewählt ist
                    //daraufhin wird dann das objekt mit dem index hier gespeichert
                    chosenFahrzeug = App.getFahrzeuge().get(indexForEdit-1);
                    //mit dieser methode wird die listview gelehrt und gefüllt mit allen variablen des objekts
                    fillListViewItem();
                    System.out.println("details fahrzeug");
                }
                else if(superClassChoice == 2){
                    chosenKunde = App.getKunden().get(indexForEdit-1);
                    fillListViewItem();
                    System.out.println("details kunden");
                }
                else if(superClassChoice == 3){
                    chosenMiete = App.getMieten().get(indexForEdit - 1);
                    fillListViewItem();
                    System.out.println("details mieten");
                }
            }
        }
        
    }
    
    public void fillListViewItem(){
        lsMain.getItems().clear();
        
        if(!(chosenFahrzeug == null)){
            chosenFahrzeug.returnAllVar().forEach(s -> {
                lsMain.getItems().add(s.toString());
            });
            switch(chosenFahrzeug.typ){
                case "LKW" : LkwModel lkw = (LkwModel) chosenFahrzeug;
                    lsMain.getItems().add(Integer.toString(lkw.ladeRaum));
                    break;
                case "PKW" : 
                    PkwModel pkw = (PkwModel) chosenFahrzeug;
                    lsMain.getItems().add("Sitze: " + Integer.toString(pkw.sitze));
                    lsMain.getItems().add("PS: " + Integer.toString(pkw.ps));
                    break;
                case "Van" : VanModel van = (VanModel) chosenFahrzeug;
                    if(van.getoenteScheiben){lsMain.getItems().add("Die Scheiben sind getönt.");}
                    else if(!(van.getoenteScheiben)){lsMain.getItems().add("Die Scheiben sind nicht getönt.");}
                    break;
                case "Anhaenger" : AnhaengerModel anhaenger = (AnhaengerModel) chosenFahrzeug;
                    lsMain.getItems().add("BeladungsLimit: " + Integer.toString(anhaenger.beladungsLimit));
                    break;
            }
        }
        else if(!(chosenKunde == null)){
            chosenKunde.returnAllVar().forEach(s -> {
                lsMain.getItems().add(s.toString());
            });
            switch(chosenKunde.getKundenTyp()){
                    case "Geschäftskunde" : GeschaeftskundeModel gKunde = (GeschaeftskundeModel) chosenKunde; 
                        lsMain.getItems().add(gKunde.arbeitsAdresse);
                        lsMain.getItems().add(gKunde.arbeitsTelefon);
                        break;
                    case "Privatkunde" : PrivatkundeModel pKunde = (PrivatkundeModel) chosenKunde;
                        lsMain.getItems().add(pKunde.notfallTelefon);
                        break;
                }
        }
        else if(!(chosenMiete == null)){
            chosenMiete.returnAllVar().forEach(s -> {
                lsMain.getItems().add(s.toString());
            });
        }
        //buttons fürs bearbeiten und zum zurück kommen.
        lsMain.getItems().add("--> |  Bearbeiten |");
        lsMain.getItems().add("--> |  Zurück     |");
        
    }
    
    
        
    public void checkMietenAblauf(){
        if(!App.getMieten().isEmpty()){
            for(mietenModel m : App.getMieten()){
                if(m.getEndDate().isBefore(App.getCurrDateTime())){
                    m.setAbgelaufen(true);
                    fillLsView();
                }
                else if(m.getEndDate().isAfter(App.getCurrDateTime())){
                    m.setAbgelaufen(false);
                    fillLsView();
                }
            }
        }
        else{}
    }
    
    
    
    
    
    private void toEdit() throws IOException{
        App.setRoot("editView");
    }

    @FXML
    private void btnNeueMiete(ActionEvent event) throws IOException {
        App.setRoot("mietenView");
    }

    @FXML
    private void btnTimeBack(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().minusHours(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("time back 1h");
    }

    @FXML
    private void btnDateBack(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().minusDays(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("date back 1 month");
    }

    @FXML
    private void btnDateForth(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().plusDays(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("date forth 1 month");
    }

    @FXML
    private void btnTimeForth(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().plusHours(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("time forth 1h");
    }

    @FXML
    private void menuItemFahrz(ActionEvent event) {
        menuItemSub1.setText("PKW");
        menuItemSub2.setText("LKW");
        menuItemSub3.setText("Van");
        menuItemSub4.setText("Anhänger");
        menuItemSub5.setText("Alle Fahrzeuge");
        superClassChoice = 1;
        subClassChoice = 5;
        menuSubClass.setDisable(false); 
        menuItemSub4.setDisable(false);
        menuItemSub4.setVisible(true);
        menuItemSub5.setDisable(false);
        menuItemSub5.setVisible(true);
        menuSuperClass.setText("Fahrzeuge");
        menuSubClass.setText("Bitte Auswählen");
        
        lsMain.getItems().clear();
        App.getFahrzeuge().forEach(f -> {
        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
        });
    }

    @FXML
    private void menuItemKunde(ActionEvent event) {
        menuItemSub1.setText("Privatkunde");
        menuItemSub2.setText("Geschäftskunde");
        menuItemSub3.setText("Alle Kunden");
        menuItemSub4.setDisable(true);
        menuItemSub4.setVisible(false);
        menuItemSub5.setDisable(true);
        menuItemSub5.setVisible(false);
        superClassChoice = 2;
        subClassChoice = 3;
        menuSubClass.setDisable(false);
        menuSuperClass.setText("Kunden");
        menuSubClass.setText("Bitte Auswählen");
        
        lsMain.getItems().clear();
        App.getKunden().forEach(f -> {
        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Tel.: " + f.telefonNummer + " K.Num.: " + f.kundenNummer);
        });
    }

    @FXML
    private void menuItemMiete(ActionEvent event) {
        menuItemSub1.setText("Laufende Mieten");
        menuItemSub2.setText("Fertige Mieten");
        menuItemSub3.setText("Alle Mieten");
        menuItemSub4.setDisable(true);
        menuItemSub4.setVisible(false);
        menuItemSub5.setDisable(true);
        menuItemSub5.setVisible(false);
        superClassChoice = 3;
        subClassChoice = 3;
        menuSubClass.setDisable(false);
        menuSuperClass.setText("Mieten");
        menuSubClass.setText("Bitte Auswählen");
        
        lsMain.getItems().clear();
        App.getMieten().forEach(m -> {
        lsMain.getItems().add("Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer());
        });
    }

    @FXML
    private void menuItemSub1(ActionEvent event) {
        
        subClassChoice = 1;
        fillLsView();
    }

    @FXML
    private void menuItemSub2(ActionEvent event) {
        
        subClassChoice = 2;
        fillLsView();
    }

    @FXML
    private void menuItemSub3(ActionEvent event) {
        
        subClassChoice = 3;
        fillLsView();
    }

    @FXML
    private void menuItemSub4(ActionEvent event) {
        
        subClassChoice = 4;
        fillLsView();
    }
    
    @FXML
    private void menuItemSub5(ActionEvent event) {
        
        subClassChoice = 5;
        fillLsView();
    }
    
    
    

    
}
