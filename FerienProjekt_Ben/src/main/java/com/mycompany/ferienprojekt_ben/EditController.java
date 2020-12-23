/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
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
    private int index;
    @FXML
    private CheckBox checkBox;
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
        deactCheckBox();
    }   

    //Methode welche die Ausgewählte Variable mit dem Inhalt des TextFelds überschreibt.
    @FXML
    private void btnAnwenden(ActionEvent event) {
        //Es muss abgefragt werden welches Object und welche Variable ausgewählt sind, damit die richtige Variable überschrieben werden kann.
        
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
                p.setModel(txtItemEdit.getText());
            }
            else if(index == 2){
                p.setFarbe(txtItemEdit.getText());
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperartur(checkBox.isSelected());
            }
            else if(index == 5){
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));
            }
            else if(index == 6){
                p.setSitze(Integer.parseInt(txtItemEdit.getText()));
            }
            else if(index == 7){
                p.setPs(Integer.parseInt(txtItemEdit.getText()));
            }
            
            //Das veraltete Fahrzeug wird aus der ArrayList entfernt und das überarbeitete Objekt wird am selben Index hinzugefügt
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
            
        }
        else if(lkw != null){
            LkwModel p = (LkwModel) App.getFahrzeuge().get(indexOfItem -1);
            if(index == 0){
                p.setHersteller(txtItemEdit.getText());
            }
            else if(index == 1){
                p.setModel(txtItemEdit.getText());
            }
            else if(index == 2){
                p.setFarbe(txtItemEdit.getText());
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperartur(checkBox.isSelected());
            }
            else if(index == 5){
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));
            }
            else if(index == 6){
                p.setLadeRaum(Integer.parseInt(txtItemEdit.getText()));
            }
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
        }
        else if(van != null){
            VanModel p = (VanModel) App.getFahrzeuge().get(indexOfItem -1);
            if(index == 0){
                p.setHersteller(txtItemEdit.getText());
            }
            else if(index == 1){
                p.setModel(txtItemEdit.getText());
            }
            else if(index == 2){
                p.setFarbe(txtItemEdit.getText());
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperartur(checkBox.isSelected());
            }
            else if(index == 5){
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));
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
                p.setHersteller(txtItemEdit.getText());
            }
            else if(index == 1){
                p.setModel(txtItemEdit.getText());
            }
            else if(index == 2){
                p.setFarbe(txtItemEdit.getText());
            }
            else if(index == 3){
                p.setInBenutzung(checkBox.isSelected());
            }
            else if(index == 4){
                p.setInReperartur(checkBox.isSelected());
            }
            else if(index == 5){
                p.setStundenKosten(Integer.parseInt(txtItemEdit.getText()));
            }
            else if(index == 6){
                p.setBeladungsLimit(Integer.parseInt(txtItemEdit.getText()));
            }
            App.getFahrzeuge().remove(indexOfItem -1);
            App.getFahrzeuge().add(indexOfItem - 1, p);
        }
        else if(privKunde != null){
            PrivatkundeModel k = (PrivatkundeModel) App.getKunden().get(indexOfItem - 1);
            
            if(index == 0){
                k.setVorname(txtItemEdit.getText());
            }
            else if(index == 1){
                k.setNachname(txtItemEdit.getText());
            }
            else if(index == 2){
                k.setTelefonNummer(txtItemEdit.getText());
            }
            else if(index == 3){
                k.setAddresse(txtItemEdit.getText());
            }
            else if(index == 4){
                k.setNotfallTelefon(txtItemEdit.getText());
            }
            App.getKunden().remove(indexOfItem - 1);
            App.getKunden().add(indexOfItem - 1, k);
        }
        else if(geschKunde != null){
            GeschaeftskundeModel k = (GeschaeftskundeModel) App.getKunden().get(indexOfItem - 1);
            
            if(index == 0){
                k.setVorname(txtItemEdit.getText());
            }
            else if(index == 1){
                k.setNachname(txtItemEdit.getText());
            }
            else if(index == 2){
                k.setTelefonNummer(txtItemEdit.getText());
            }
            else if(index == 3){
                k.setAddresse(txtItemEdit.getText());
            }
            else if(index == 4){
                k.setArbeitsTelefon(txtItemEdit.getText());
            }
            else if(index == 5){
                k.setArbeitsAdresse(txtItemEdit.getText());
            }
            App.getKunden().remove(indexOfItem - 1);
            App.getKunden().add(indexOfItem - 1, k);
            
        }
        else{
            System.out.println("err, auf kein item geklickt");
        }
        //als Letztes wird noch die ListView geupdated.
        fillLsView();
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
            lsItems.getItems().add("Hersteller: "+App.getFahrzeuge().get(indexOfItem - 1).hersteller);
            lsItems.getItems().add("Modell: "+App.getFahrzeuge().get(indexOfItem - 1).model);
            lsItems.getItems().add("Farbe: "+App.getFahrzeuge().get(indexOfItem - 1).farbe);
            lsItems.getItems().add("Benutzt: "+Boolean.toString(App.getFahrzeuge().get(indexOfItem - 1).isInBenutzung()));
            lsItems.getItems().add("In Reperatur: "+Boolean.toString(App.getFahrzeuge().get(indexOfItem - 1).isInReperartur()));
            lsItems.getItems().add("Stundenkosten: "+Integer.toString(App.getFahrzeuge().get(indexOfItem - 1).stundenKosten));
            
            //Variablen speziell aus den Subklassen werden hier hinzugefügt, je nachdem welche Klasse das bearbeitbare item ist.
            if("PKW".equals(App.getFahrzeuge().get(indexOfItem - 1).typ)){
                //hier speichere ich das bearbeitbare Objekt als ihre echte Klasse zwischen.
                //Dies ist wichtig da ich die Subklassen spezifischen Variablen nicht benutzen kann, wenn das Objekt als ihre Superklasse gespeichert ist.
                pkw = (PkwModel) App.getFahrzeuge().get(indexOfItem - 1);
                lsItems.getItems().add("Sitze: "+Integer.toString(pkw.sitze));
                lsItems.getItems().add("PS: "+Integer.toString(pkw.ps));
            }
            else if("LKW".equals(App.getFahrzeuge().get(indexOfItem - 1).typ)){
                lkw = (LkwModel) App.getFahrzeuge().get(indexOfItem - 1);
                lsItems.getItems().add("Laderaum: " + Integer.toString(lkw.ladeRaum));
            }
            
            else if("Anhaenger".equals(App.getFahrzeuge().get(indexOfItem - 1).typ)){
                anhaenger = (AnhaengerModel) App.getFahrzeuge().get(indexOfItem - 1);
                lsItems.getItems().add("Laderaum: " + Integer.toString(anhaenger.beladungsLimit));
            }
            
            else if("Van".equals(App.getFahrzeuge().get(indexOfItem - 1).typ)){
                van = (VanModel) App.getFahrzeuge().get(indexOfItem - 1);
                lsItems.getItems().add("Laderaum: " + Boolean.toString(van.getoenteScheiben));
            }
            
            else{System.out.println("error, item without typ beschreibung");
                lsItems.getItems().add("err, return to home screen");}
        }
        
        
        
        else if("Kunden".equals(App.getChoiceForEditViewTyp())){
            //Es werden die Variablen welche aus der Superklasse KundenModel kommen in die ListView hinzugefügt.
            //Ich habe Variablen rausgelassen welche nicht zum ändern gedacht sind.
            System.out.println("kunden fill");
            lsItems.getItems().add("Vorname: "+App.getKunden().get(indexOfItem - 1).vorname);
            lsItems.getItems().add("Nachname: "+App.getKunden().get(indexOfItem - 1).nachname);
            lsItems.getItems().add("Tel Nummer: "+App.getKunden().get(indexOfItem - 1).telefonNummer);
            lsItems.getItems().add("Addresse: "+App.getKunden().get(indexOfItem - 1).addresse);
            
            //Variablen speziell aus den Subklassen werden hier hinzugefügt, je nachdem welche Klasse das bearbeitbare item ist.
            if("Privatkunde".equals(App.getKunden().get(indexOfItem - 1).kundenTyp)){
                //hier speichere ich das bearbeitbare Objekt als ihre echte Klasse zwischen.
                //Dies ist wichtig da ich die Subklassen spezifischen Variablen nicht benutzen kann, wenn das Objekt als ihre Superklasse gespeichert ist.
                privKunde = (PrivatkundeModel) App.getKunden().get(indexOfItem - 1);
                lsItems.getItems().add("Notfall Telefon: "+ privKunde.notfallTelefon);
                
            }
            else if("Geschäftskunde".equals(App.getKunden().get(indexOfItem - 1).kundenTyp)){
                geschKunde = (GeschaeftskundeModel) App.getKunden().get(indexOfItem - 1);
                lsItems.getItems().add("Arbeits Telefon: " + geschKunde.arbeitsTelefon);
                lsItems.getItems().add("Arbeits Addresse: " + geschKunde.arbeitsAdresse);
            }
            else{System.out.println("error, item without typ beschreibung");
                lsItems.getItems().add("err, return to home screen");}
        }
        
        
        else{System.out.println("err, no kunde or fahrzeug");}
        
        System.out.println(pkw);
        System.out.println(lkw);
        System.out.println(van);
        System.out.println(anhaenger);
        System.out.println(privKunde);
        System.out.println(geschKunde);
    }    
    
    
    public void actCheckBox(){
        txtItemEdit.setDisable(true);
        txtItemEdit.setVisible(false);
        checkBox.setDisable(false);
        checkBox.setVisible(true); 
        checkBox.setText(" ");
    }
    public void deactCheckBox(){
        txtItemEdit.setDisable(false);
        txtItemEdit.setVisible(true);
        checkBox.setDisable(true);
        checkBox.setVisible(false); 
    }
    
    
    @FXML
    private void getListItemIndex(MouseEvent event) {
        index = lsItems.getFocusModel().getFocusedIndex();
        System.out.println(index);
        deactCheckBox();
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
                actCheckBox();
                checkBox.setSelected(pkw.inBenutzung);                
            }
            else if(index == 4){
                txtItemEdit.clear();
                actCheckBox();
                checkBox.setSelected(pkw.inReperartur);   
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
                actCheckBox();
                checkBox.setSelected(lkw.inBenutzung);
            }
            else if(index == 4){
                txtItemEdit.clear();
                actCheckBox();
                checkBox.setSelected(lkw.inReperartur); 
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
                actCheckBox();
                checkBox.setSelected(van.inBenutzung); 
            }
            else if(index == 4){
                txtItemEdit.clear();
                actCheckBox();
                checkBox.setSelected(van.inReperartur); 
            }
            else if(index == 5){
                txtItemEdit.clear();
                txtItemEdit.setText(Integer.toString(van.stundenKosten));
            }
            else if(index == 6){
                txtItemEdit.clear();
                actCheckBox();
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
                actCheckBox();
                checkBox.setSelected(anhaenger.inBenutzung); 
            }
            else if(index == 4){
                txtItemEdit.clear();
                actCheckBox();
                checkBox.setSelected(anhaenger.inReperartur); 
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
                txtItemEdit.setText(geschKunde.arbeitsTelefon);
            }
             else if(index == 5){
                txtItemEdit.clear();
                txtItemEdit.setText(geschKunde.arbeitsAdresse);
            }
            
            
        }
        else{
            System.out.println("err, auf kein item geklickt");
        }
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
    
    
}
