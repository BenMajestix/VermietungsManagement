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
    
    private boolean inObjectView; //ObjektView ist, wenn ein Objekt ausgewählt wurde, und dessen Details angezeigt werden
    private boolean inSearch; //inSearch, wenn gerade Suchergebnisse angezeigt werden
    
    //Die Auswahl aus dem Menu werden hier gespeichert, für einfacheres abgleichen
    private int superClassChoice; //Hier die SuperKlasse 
    private int subClassChoice; //Und Die SubKlasse, oder ob alle Objekte der SuperKlasse angezeigt werden sollen
    
    private FahrzeugModel chosenFahrzeug; //Wenn auf ein Objekt in der LSView geklickt wird, wird dies hier gespeichert, jenachdem was es ist, woanders
    private KundenModel chosenKunde;
    private mietenModel chosenMiete;
    
    private ArrayList<Integer> searchResultSuperClass; //es werden die Superklassen aller suchergebnisse hier in richtiger reihenfolge gespeichert, damit man die ergebnisse später identifizieren kann
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchResultSuperClass = new ArrayList(); 
        
        menuSuperClass.setText("Bitte Auswählen");
        menuSubClass.setText("Bitte Auswählen");
        menuItemFahrz.setText("Fahrzeuge");
        menuItemKunde.setText("Kunden");
        menuItemMiete.setText("Mieten");
        System.out.println("--Prompts Set");
        
        menuSubClass.setDisable(true);
        subClassChoice = 0; //Falls keine Choice ausgewählt wurde, kann es Fehler geben, da subClassChoice sonst null ist
        
        fillLsView();
        System.out.println("--ListView gefüllt");
        //Die Date und Time Labels werden mit der Zeit und Tag gesetzt
        lblCurrDate.setText(App.getCurrDateTime().getDayOfMonth() + ". " + App.getCurrDateTime().getMonth());
        lblCurrTime.setText(App.getCurrDateTime().getHour() + ":" + App.getCurrDateTime().getMinute() + " Uhr");
        System.out.println("--Datum und Zeit gesetzt");
        inObjectView = false;
        //Es wird gecheckt ob und welche Mieten noch laufen
        checkMietenAblauf();
        
    }    
    //Es werden die Labels mit der Zeit und dem Datum erneuert und dann werden die Mieten neu geladen
    //also Mieten werden auf abgelaufen oder laufend gesetzt
    public void reloadDateTime(){
        lblCurrDate.setText(App.getCurrDateTime().getDayOfMonth() + ". " + App.getCurrDateTime().getMonth());
        lblCurrTime.setText(App.getCurrDateTime().getHour() + ":" + App.getCurrDateTime().getMinute() + " Uhr");
        checkMietenAblauf();
    }
    
    //füllt die LIstView je nachdem welche Subklasse ausgewählt ist
    public void fillLsView(){
        //Es werden jenachdem welche Klasse gewählt ist, die Superklassenspezifischen Variablen in die ListView gesetzt
        //Dann wird geswitched welche subklass noch gewählt ist und es werden die Subklassespezifischen variablen hinzugefügt
        inSearch = false;
        switch(subClassChoice){
            //0 bedeutet, dass keine klasse gewählt ist
            case 0: lsMain.getItems().clear(); break;
            case 1: 
                System.out.println(superClassChoice);
                switch(superClassChoice){
                    case 1: 
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
                System.out.println(superClassChoice);
                switch(superClassChoice){
                    case 1:
                        lsMain.getItems().clear();
                        App.getFahrzeuge().stream().filter(f -> ("Van".equals(f.getTyp()))).forEachOrdered(f -> {
                            String ifErhealtlich;
                            if(f.erhealtlich){ifErhealtlich = "Erhältlich";}
                            else{ifErhealtlich = "Nicht Erhältlich";}
                            lsMain.getItems().add("Model: " + f.hersteller + " " + f.model +  " || " + ifErhealtlich + " || " +  " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
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
                System.out.println(superClassChoice);
                lsMain.getItems().clear();
                    App.getFahrzeuge().stream().filter(f -> ("Anhaenger".equals(f.getTyp()))).forEachOrdered(f -> {
                    lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                    });
                    menuSubClass.setText("Anhänger");
            break; 
            
            case 5: 
                System.out.println(superClassChoice);
                lsMain.getItems().clear();
                App.getFahrzeuge().forEach(f -> {
                lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
                });
                menuSubClass.setText("Alle Fahrzeuge"); 
            break; 
        }

    }
    
    //wird aktiviert, wenn es einen Input in die Suchleiste gab
    @FXML
    private void searchStart(KeyEvent event) {
        inObjectView = false;
        //Da eine Suche gestartet wurde wird der Suchmodus angestellt
        inSearch = true;
        
        //Hier werden alle hits als searchResults gespeichert, diese beinhalten einen String welcher in der ListView angezeigt wird und einen searchScore, 
        //welcher repräsentiert wie relevant das searchResult ist 
        ArrayList<SearchResultModel> hits = new ArrayList();
        
        lsMain.getItems().clear();
        //Da es neue Objekte in den SearchResults gibt, werden die alten results gelöscht
        searchResultSuperClass.clear();
        //Es wird das suchwort gespeichert
        String keyWord = searchbar.getText();
        //Ein String Array, um verschiedene suchwörter aus der suchleiste seperat zu speichern
        String[] keyWordArr;
        //Hier wird das Suchwort aus der suchleiste an jedem leerzeichen auseinandergeschnitten und jedes wort einzeln gespeichert
        keyWordArr = keyWord.split(" ", -1);
        
        System.out.println("--SearchFahrzeuge");
        for(FahrzeugModel f : App.getFahrzeuge()){
            //Es werden nacheinander alle Fahrzeuge durchgegangen
            System.out.println(f.model);
            //Diese Variable zeigt obt es überhaupt einen treffer gab
            boolean keyWordFound = false;
            //Hier wird gezeigt wieviele treffer es gab
            int trefferScore = 0;
            
            //Es werden alle suchbegriffe nacheinander durchgegangen
            for (int i = 0; i < keyWordArr.length; i++){
                for(var s : f.returnAllData()){
                    //Dann für jeden suchbegriff werden nacheinander alle VAriablen der Fahrzeuge durchgegangen
                    //Hier wird die Variable des Fahrzeugs in kleinschreibung gespeichert
                    String checkStringLow = s.toString().toLowerCase();
                    
                    //Ein genauer treffer wird mit 12 punkten gewertet
                    if(s.toString().equals(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 12;
                        //Exact Hit
                    }
                    //ein genauer Treffer wo keine groß/kleinschreibung beachtet wird
                    if(checkStringLow.equals(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore = trefferScore + 5;
                        //Exact Hit, with both Strings in LowerCase
                    }
                    //wenn die Variable des Fahrzeugs das suchwort beinhaltet gibt es einen normalen hit
                    //Ich habe dies in diese richtung gemacht, damit es auch ergebnisse gibt wenn das suchwort noch nicht fertig geschrieben ist
                    if(s.toString().contains(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 2;
                        //Hit
                    }
                    //wenn die Variable des Fahrzeugs das suchwort beinhaltet gibt es einen normalen hit, ohne Groß/kleinschreibung
                    //Ich habe dies in diese richtung gemacht, damit es auch ergebnisse gibt wenn das suchwort noch nicht fertig geschrieben ist
                    if(checkStringLow.contains(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore ++;
                        //Hit, with both Strings in LowerCase
                    }
                }
            }
            //Hier wird ein String des outputs gespeichert, welcher dann später in der Listview angezeigt wird
            //Dieser Output ist natürlich Klassenspezifisch
            String output = "Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer();
            if(keyWordFound){
                //Falls es einen treffer gab, wird ein SearchResult erstellt und zu der trefferliste hinzugefügt
                SearchResultModel hit = new SearchResultModel(output, trefferScore, 1);
                hits.add(hit);
                System.out.println(hit.searchScore);
            }
        }
        System.out.println("--SearchKunden");
        for(KundenModel k : App.getKunden()){
            //Es werden nacheinander alle kunden durchgegangen
            System.out.println(k.vorname);
            //Diese Variable zeigt obt es überhaupt einen treffer gab
            boolean keyWordFound = false;
            //Hier wird gezeigt wieviele treffer es gab
            int trefferScore = 0;
            //Es werden alle suchbegriffe nacheinander durchgegangen
            for (int i = 0; i < keyWordArr.length; i++){
                for(var s : k.returnAllData()){
                    //Dann für jeden suchbegriff werden nacheinander alle Variablen der Kunden durchgegangen
                    //Hier wird die Variable des Kunden in kleinschreibung gespeichert
                    String checkStringLow = s.toString().toLowerCase();
                    //Ein genauer treffer wird mit 12 punkten gewertet
                    if(s.toString().equals(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 12;
                        //Exact Hit
                    }
                    //ein genauer Treffer wo keine groß/kleinschreibung beachtet wird
                    if(checkStringLow.equals(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore = trefferScore + 5;
                        //Exact Hit, with both Strings in LowerCase
                    }
                    //wenn die Variable des Kunden das suchwort beinhaltet gibt es einen normalen hit
                    //Ich habe dies in diese richtung gemacht, damit es auch ergebnisse gibt wenn das suchwort noch nicht fertig geschrieben ist
                    if(s.toString().contains(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 2;
                        //Hit
                    }
                    //wenn die Variable des Kunden das suchwort beinhaltet gibt es einen normalen hit, ohne Groß/kleinschreibung
                    //Ich habe dies in diese richtung gemacht, damit es auch ergebnisse gibt wenn das suchwort noch nicht fertig geschrieben ist
                    if(checkStringLow.contains(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore ++;
                        //Hit, with both Strings in LowerCase
                    }
                }
            }
            //Hier wird ein String des outputs gespeichert, welcher dann später in der Listview angezeigt wird
            //Dieser Output ist natürlich Klassenspezifisch
            String output = "Name: " + k.vorname + " " + k.nachname + " " + " Tel.: " + k.telefonNummer + " K.Num.: " + k.kundenNummer;
            if(keyWordFound){
                //Falls es einen treffer gab, wird ein SearchResult erstellt und zu der trefferliste hinzugefügt
                SearchResultModel hit = new SearchResultModel(output, trefferScore, 2);
                hits.add(hit);
                System.out.println(hit.searchScore);
            }
        }
        System.out.println("--SearchMieten");
        for(mietenModel m : App.getMieten()){
            //Es werden nacheinander alle Mieten durchgegangen
            System.out.println(m.getKunde().nachname);
            //Diese Variable zeigt obt es überhaupt einen treffer gab
            boolean keyWordFound = false;
            //Hier wird gezeigt wieviele treffer es gab
            int trefferScore = 0;
            
            //Es werden alle suchbegriffe nacheinander durchgegangen
            for (int i = 0; i < keyWordArr.length; i++){
                for(var s : m.returnAllData()){
                    //Dann für jeden suchbegriff werden nacheinander alle VAriablen der Kunden durchgegangen
                    //Hier wird die Variable des Fahrzeugs in kleinschreibung gespeichert
                    String checkStringLow = s.toString().toLowerCase();
                    
                    //Ein genauer treffer wird mit 12 punkten gewertet
                    if(s.toString().equals(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 12;
                        //Exact Hit
                    }
                    //ein genauer Treffer wo keine groß/kleinschreibung beachtet wird
                    if(checkStringLow.equals(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore = trefferScore + 5;
                        //Exact Hit, with both Strings in LowerCase
                    }
                    //wenn die Variable des Mieten das suchwort beinhaltet gibt es einen normalen hit
                    //Ich habe dies in diese richtung gemacht, damit es auch ergebnisse gibt wenn das suchwort noch nicht fertig geschrieben ist
                    if(s.toString().contains(keyWordArr[i])){
                        keyWordFound = true;
                        trefferScore = trefferScore + 2;
                        //Hit
                    }
                    //wenn die Variable der Mieten das suchwort beinhaltet gibt es einen normalen hit, ohne Groß/kleinschreibung
                    //Ich habe dies in diese richtung gemacht, damit es auch ergebnisse gibt wenn das suchwort noch nicht fertig geschrieben ist
                    if(checkStringLow.contains(keyWordArr[i].toLowerCase())){
                        keyWordFound = true;
                        trefferScore ++;
                        //Hit, with both Strings in LowerCase
                    }
                    
                }
            }
            //Hier wird ein String des outputs gespeichert, welcher dann später in der Listview angezeigt wird
            //Dieser Output ist natürlich Klassenspezifischs
            String output = "Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer();
            if(keyWordFound){
                //Falls es einen treffer gab, wird ein SearchResult erstellt und zu der trefferliste hinzugefügt
                SearchResultModel hit = new SearchResultModel(output, trefferScore, 3);
                hits.add(hit);
                System.out.println(hit.searchScore);
            }
        }
        sortResults(hits, keyWord);
    }
    
    
    
    //Es wird eine ArrList mit SearchResults genommen und nach searchScore sortiert
    private void sortResults(ArrayList<SearchResultModel> hits, String keyWord){
        //Hier wird die Liste Sortiert
        Collections.sort(hits, new Comparator<SearchResultModel>() {
            //ES werden nach und nach alle Objekte anhand ihres searchScores verglichen und dann von dem Comparator sortiert
            @Override
            public int compare(SearchResultModel o1, SearchResultModel o2) {
                return Double.compare(o1.getSearchScore(), o2.getSearchScore());
            }
        });
        //Hier reverse ich die Objekte in der ArrList, damit das Objekt mit dem höchsten searchScore nicht ganz unten in der listView ist
        Collections.reverse(hits);
        //Jetzt werden nacheinander alle Objekte in der ListView angezeigt und jenachdem welche Superklasse dieses objekt hat, wird eine Int, welche die superklasse repräsentiert,
        //in einer ArrList gespeichert
        //Dies ist nützlich damit das Programm weiß welches Objekt an welchem Index in der ListView ist
        //Zusammen mit dem Integer am ende des strings jeden Objektes, welches den index des Objektes in der Jeweiligen ArrList darstellt,kann man genau wissen welches Objekt es ist
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
        System.out.println("--Search End");
    }
    
    
    
    
    @FXML
    private void btnFahrzeugAdd(ActionEvent event) throws IOException {
        App.setRoot("hinzufuegenView");
    }

    
    

    //diese methode wird aktiviert, wenn auf ein Item in der listview geklickt wird. 
    @FXML
    private void getListItemIndex(MouseEvent event) throws IOException{
        System.out.println("--Get Index");
        //um err zu vermeiden wird, falls der nutzer auf kein Item geklickt hat, nichts ausgeführt.
        if(lsMain.getFocusModel().getFocusedItem() == null){System.out.println("--Nichts ausgewählt");}
        else{
            if(inObjectView == true){
                System.out.println("--In Objekt View");
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
                System.out.println("--In Search");
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
                    System.out.println("--Object View Fahrzeug");
                }
                else if(superClass == 2){
                    chosenKunde = App.getKunden().get(indexForEdit-1);
                    fillListViewItem();
                    System.out.println("--Object View Kunden");
                }
                else if(superClass == 3){
                    chosenMiete = App.getMieten().get(indexForEdit - 1);
                    fillListViewItem();
                    System.out.println("--Object View Mieten");
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
                    System.out.println("--Object View Fahrzeug");
                }
                else if(superClassChoice == 2){
                    chosenKunde = App.getKunden().get(indexForEdit-1);
                    fillListViewItem();
                    System.out.println("--Object View Kunden");
                }
                else if(superClassChoice == 3){
                    chosenMiete = App.getMieten().get(indexForEdit - 1);
                    fillListViewItem();
                    System.out.println("--Object View Mieten");
                }
            }
        }
        
    }
    
    //Füllt die ListView mit einem bestimmten Objekt
    public void fillListViewItem(){
        System.out.println("--Into Details Objekt View");
        
        lsMain.getItems().clear();
        //Es wird geschaut welches Objekt ausgewählt ist, indem ich schaue welche Variable befüllt ist
        if(!(chosenFahrzeug == null)){
            //Falls ein Fahrzeug gewählt ist setzt er alle FahrzeugVariablen
            chosenFahrzeug.returnAllVar().forEach(s -> {
                lsMain.getItems().add(s.toString());
            });
            //Und je nach Typ, die Typspezifischen Variablen
            switch(chosenFahrzeug.typ){
                case "LKW" : LkwModel lkw = (LkwModel) chosenFahrzeug;
                    lsMain.getItems().add("Laderaum: " + Integer.toString(lkw.ladeRaum));
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
            //Falls ein Kunde gewählt ist setzt er alle KundenVariablen
            chosenKunde.returnAllVar().forEach(s -> {
                lsMain.getItems().add(s.toString());
            });
            //Und je nach Typ, die Typspezifischen Variablen
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
            //Falls eine Miete gewählt ist setzt er alle MietenVariablen
            chosenMiete.returnAllVar().forEach(s -> {
                lsMain.getItems().add(s.toString());
            });
        }
        //buttons fürs bearbeiten und zum zurück kommen.
        //Diese sitzen in der ListView hinter den Objekt Variablen
        lsMain.getItems().add("--> |  Bearbeiten |");
        lsMain.getItems().add("--> |  Zurück     |");
        
    }
    
    
    //Diese Methode checkt, ob und welche Mieten schon abgelaufen sind und welche noch laufen.
    //Es wird daraufhin auch die Fahrzeuge der Mieten auf Benutzt oder nicht gesetzt.
    public void checkMietenAblauf(){
        System.out.println("--Check Mieten Ablauf");
        if(!App.getMieten().isEmpty()){
            for(mietenModel m : App.getMieten()){
                if(m.getEndDate().isBefore(App.getCurrDateTime())){
                    m.setAbgelaufen(true);
                    //Diese Schleife sucht sich das Objekt, welches in der Miete benutzt wird und setzt den Benutzt status auf false,
                    //da nach ablauf der Miete das Fahrzeug wieder erhältlich ist
                    for(int i = 0; i <= App.getFahrzeuge().size()-1; i++){
                        if(App.getFahrzeuge().get(i).equals(m.getFahrzeug())){
                            App.getFahrzeuge().get(i).setInBenutzung(false);
                            App.getFahrzeuge().get(i).updateErhealtlich();
                        }
                    }
                    fillLsView();
                }
                else if(m.getEndDate().isAfter(App.getCurrDateTime())){
                    m.setAbgelaufen(false);
                    //Diese Schleife sucht sich das Objekt, welches in der Miete benutzt wird und setzt den Benutzt status auf true,
                    //da nach beginn der Miete das Fahrzeug nicht mehr erhältlich ist
                    for(int i = 0; i <= App.getFahrzeuge().size()-1; i++){
                        if(App.getFahrzeuge().get(i).equals(m.getFahrzeug())){
                            App.getFahrzeuge().get(i).setInBenutzung(true);
                            App.getFahrzeuge().get(i).updateErhealtlich();
                        }
                    }
                    fillLsView();
                }
            }
        }
        else{}
        System.out.println("--Done");
    }
    
    
    
    
    
    private void toEdit() throws IOException{
        App.setRoot("editView");
    }

    @FXML
    private void btnNeueMiete(ActionEvent event) throws IOException {
        App.setRoot("mietenView");
    }

    //Setzt die Zeit um eine Stunde zurück
    @FXML
    private void btnTimeBack(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().minusHours(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("--Zeit zurück 1h");
        
    }
    //Setzt das Datum um einen Tag zurück
    @FXML
    private void btnDateBack(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().minusDays(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("--Zeit zurück 1 Tag");
    }
    //Setzt das Datum um einen Tag vor
    @FXML
    private void btnDateForth(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().plusDays(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("--Zeit 1 Tag weiter");
    }
    //Setzt die Zeit um eine Stunde vor
    @FXML
    private void btnTimeForth(ActionEvent event) {
        LocalDateTime l = App.getCurrDateTime().plusHours(1);
        App.setCurrDateTime(l);
        reloadDateTime();
        System.out.println("--Zeit weiter 1h");
    }

    //Der Button in dem SuperklassenMenü, dem Oberen, für Fahrzeuge
    @FXML
    private void menuItemFahrz(ActionEvent event) {
        //Es werden die Texte des Sub menüs gesetzt
        menuItemSub1.setText("PKW");
        menuItemSub2.setText("LKW");
        menuItemSub3.setText("Van");
        menuItemSub4.setText("Anhänger");
        menuItemSub5.setText("Alle Fahrzeuge");
        //Da die Superklasse Fahrzeuge ausgewählt wurde, ist Superclass 1 ausgewählt
        //Und als Default wird subclass 5 angezeigt, also alle Fahrzeuge
        superClassChoice = 1;
        subClassChoice = 5;
        //Das SubMenu wird freigegeben, es war nicht frei um Fehler und verwirrung mit der UI zu vermeiden
        menuSubClass.setDisable(false); 
        menuItemSub4.setDisable(false);
        menuItemSub4.setVisible(true);
        menuItemSub5.setDisable(false);
        menuItemSub5.setVisible(true);
        
        menuSuperClass.setText("Fahrzeuge");
        menuSubClass.setText("Bitte Auswählen");
        //Da man jetzt im Default anzeige Modus ist, werden die beiden anderen Modi ausgeschaltet
        inSearch = false;
        inObjectView = false;
        
        //Die ListView wird gecleared und als Default werden alle Fahrzeuge engezeigt
        lsMain.getItems().clear();
        App.getFahrzeuge().forEach(f -> {
        lsMain.getItems().add("Model: " + f.hersteller + " " + f.model + " Erhältlich: " + f.erhealtlich + " Kosten/hr : " + f.stundenKosten + " F. Num.: " + f.getFahrzeugNummer());
        });
    }

    @FXML
    private void menuItemKunde(ActionEvent event) {
        //Es werden die Texte des Sub menüs gesetzt
        menuItemSub1.setText("Privatkunde");
        menuItemSub2.setText("Geschäftskunde");
        menuItemSub3.setText("Alle Kunden");
        //Das SubMenu wird freigegeben, es war nicht frei um Fehler und verwirrung mit der UI zu vermeiden
        menuItemSub4.setDisable(true);
        menuItemSub4.setVisible(false);
        menuItemSub5.setDisable(true);
        menuItemSub5.setVisible(false);
        menuSubClass.setDisable(false);
        //Da die Superklasse Kunden ausgewählt wurde, ist Superclass 2 ausgewählt
        //Und als Default wird subclass 3 angezeigt, also alle kunden
        superClassChoice = 2;
        subClassChoice = 3;
        menuSuperClass.setText("Kunden");
        menuSubClass.setText("Bitte Auswählen");
        
        //Da man jetzt im Default anzeige Modus ist, werden die beiden anderen Modi ausgeschaltet
        inSearch = false;
        inObjectView = false;
        
        //Die ListView wird gecleared und als Default werden alle Kunden engezeigt
        lsMain.getItems().clear();
        App.getKunden().forEach(f -> {
        lsMain.getItems().add("Name: " + f.vorname + " " + f.nachname + " " + " Tel.: " + f.telefonNummer + " K.Num.: " + f.kundenNummer);
        });
    }

    @FXML
    private void menuItemMiete(ActionEvent event) {
        //Es werden die Texte des Sub menüs gesetzt
        menuItemSub1.setText("Laufende Mieten");
        menuItemSub2.setText("Fertige Mieten");
        menuItemSub3.setText("Alle Mieten");
        //Das SubMenu wird freigegeben, es war nicht frei um Fehler und verwirrung mit der UI zu vermeiden
        menuItemSub4.setDisable(true);
        menuItemSub4.setVisible(false);
        menuItemSub5.setDisable(true);
        menuItemSub5.setVisible(false);
        //Da die Superklasse Mieten ausgewählt wurde, ist Superclass 3 ausgewählt
        //Und als Default wird subclass 3 angezeigt, also alle Mieten
        superClassChoice = 3;
        subClassChoice = 3;
        menuSubClass.setDisable(false);
        menuSuperClass.setText("Mieten");
        menuSubClass.setText("Bitte Auswählen");
        //Da man jetzt im Default anzeige Modus ist, werden die beiden anderen Modi ausgeschaltet
        inSearch = false;
        inObjectView = false;
        //Die ListView wird gecleared und als Default werden alle Mieten engezeigt
        lsMain.getItems().clear();
        App.getMieten().forEach(m -> {
        lsMain.getItems().add("Kunde: " + m.getKunde().nachname + " | Fahrzeug: " + m.getFahrzeug().hersteller + " | Start: " + m.getStartDate().getDayOfMonth() + ". " + m.getStartDate().getMonth() + " | Ende: " + m.getEndDate().getDayOfMonth() + ". " + m.getEndDate().getMonth() + " | N.: " + m.getMietenNummer());
        });
    }

    
    //Alle SubItem Methods
    //Setzt die subClassChoice welche zeigt welches menüitem man ausgewählt hat
    @FXML
    private void menuItemSub1(ActionEvent event) {
        inSearch = false;
        subClassChoice = 1;
        fillLsView();
        inObjectView = false;
    }

    @FXML
    private void menuItemSub2(ActionEvent event) {
        inSearch = false;
        subClassChoice = 2;
        fillLsView();
        inObjectView = false;
    }

    @FXML
    private void menuItemSub3(ActionEvent event) {
        inSearch = false;
        subClassChoice = 3;
        fillLsView();
        inObjectView = false;
    }

    @FXML
    private void menuItemSub4(ActionEvent event) {
        inSearch = false;
        subClassChoice = 4;
        fillLsView();
        inObjectView = false;
    }
    
    @FXML
    private void menuItemSub5(ActionEvent event) {
        inSearch = false;
        subClassChoice = 5;
        fillLsView();
        inObjectView = false;
    }
    
    
    

    
}
