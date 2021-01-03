package com.mycompany.ferienprojekt_ben;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<FahrzeugModel> fahrzeuge;
    private static ArrayList<KundenModel> kunden;
    private static ArrayList<mietenModel> mieten;
    
    //Der Index und die Klasse des gewählten Objekts aus der homeView Listview, zum auslesen des editController
    private static int choiceForEditViewIndex;
    private static String choiceForEditViewTyp;
    
    //Current Date and Time
    private static LocalDateTime currDateTime;
    
    //Zwischenspeicher für alle Felder in der MietenView
    private static LocalDate cacheMieteStartDate;
    private static LocalDate cacheMieteEndDate;
    private static LocalTime cacheMieteStartTime;
    private static LocalTime cacheMieteEndTime;
    private static FahrzeugModel cacheMieteFahrzeug;
    private static KundenModel cacheMieteKunde;
    
    //Ob der Benutzer von der MietenView zur HinzufuegenView umgeleitet wurde
    //Wird auch benutzt, damit man aus der HinzuView auch zurück zur MietenView geleitet wird
    private static boolean hinzuDirFromMiete;
    
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("homeView"), 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        kunden = new ArrayList();
        fahrzeuge = new ArrayList();
        mieten = new ArrayList();
        
        //es werden fürs testen eine menge fahrzeuge und Kunden erstellt und den arrayLists hinzugefügt.
        fahrzeugeErstellen();
        System.out.println("--Fahrzeuge Erstellt");
        kundenErstellen();
        System.out.println("--Kunden Erstellt");
        mietenErstellen();
        System.out.println("--Mieten Erstellt");
        
        currDateTime = LocalDateTime.now();
        System.out.println("--Aktuelles Datum gesetzt");
        System.out.println(" ");
        System.out.println("--Launch");
        System.out.println(" ");
        
        launch();
    }

    public static void fahrzeugeErstellen(){
        int fahrzeugNum = fahrzeuge.size() + 1;
        LkwModel f1 = new LkwModel(170, "VW", "Truck", "Schwarz", true, false, false, 24, fahrzeugNum);
        fahrzeuge.add(f1);
        fahrzeugNum = fahrzeuge.size() + 1;
        PkwModel f2 = new PkwModel(5, 210, "Tesla", "Model S", "Schwarz", true, false, false, 30, fahrzeugNum);
        fahrzeuge.add(f2);
        fahrzeugNum = fahrzeuge.size() + 1;
        AnhaengerModel f3 = new AnhaengerModel(70, "Kaefer", "Anhaenger", "Weiss", true, false, false, 24, fahrzeugNum);
        fahrzeuge.add(f3);
        fahrzeugNum = fahrzeuge.size() + 1;
        VanModel f4 = new VanModel(true, "VW", "Van", "Schwarz", true, false, false, 24, fahrzeugNum);
        fahrzeuge.add(f4);
    }
    public static void kundenErstellen(){
        int kundenNum = kunden.size() + 1;
        PrivatkundeModel k1 = new PrivatkundeModel("017642069", "Ben", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k1);
        kundenNum = kunden.size() + 1;
        PrivatkundeModel k2 = new PrivatkundeModel("017642069", "Sonja", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k2);
        kundenNum = kunden.size() + 1;
        GeschaeftskundeModel k3 = new GeschaeftskundeModel("Bremen", "017642069", "Matz", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k3);
        kundenNum = kunden.size() + 1;
        GeschaeftskundeModel k4 = new GeschaeftskundeModel("Duisburg", "017642069", "Peer", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k4);
    }
    public static void mietenErstellen(){
        LocalDateTime t1 = LocalDateTime.of(2020, 12, 24, 15, 30);
        LocalDateTime t2 = LocalDateTime.of(2020, 12, 27, 17, 45);
        LocalDateTime t3 = LocalDateTime.of(2020, 12, 4, 12, 00);
        LocalDateTime t4 = LocalDateTime.of(2021, 01, 15, 10, 00);
        mietenModel m1 = new mietenModel(t1, t2, App.getFahrzeuge().get(1), App.getKunden().get(2));
        m1.setMietenNummer(mieten.size()+1);
        mieten.add(m1);
        mietenModel m2 = new mietenModel(t3, t4, App.getFahrzeuge().get(3), App.getKunden().get(2));
        m2.setMietenNummer(mieten.size()+1);
        
        mieten.add(m2);
    }
    
    public static ArrayList<mietenModel> getMieten() {
        return mieten;
    }

    public static void setMieten(ArrayList<mietenModel> mieten) {
        App.mieten = mieten;
    }
    
    public static ArrayList<FahrzeugModel> getFahrzeuge() {
        return fahrzeuge;
    }

    public static ArrayList<KundenModel> getKunden() {
        return kunden;
    }

    public static int getChoiceEditView() {
        return choiceForEditViewIndex;
    }

    public static void setChoiceForEditViewIndex(int choiceForEditViewIndex) {
        App.choiceForEditViewIndex = choiceForEditViewIndex;
    }

    public static String getChoiceForEditViewTyp() {
        return choiceForEditViewTyp;
    }

    public static void setChoiceForEditViewTyp(String choiceForEditViewTyp) {
        App.choiceForEditViewTyp = choiceForEditViewTyp;
    }

    public static LocalDate getCacheMieteStartDate() {
        return cacheMieteStartDate;
    }

    public static void setCacheMieteStartDate(LocalDate cacheMieteStartDate) {
        App.cacheMieteStartDate = cacheMieteStartDate;
    }

    public static LocalDate getCacheMieteEndDate() {
        return cacheMieteEndDate;
    }

    public static void setCacheMieteEndDate(LocalDate cacheMieteEndDate) {
        App.cacheMieteEndDate = cacheMieteEndDate;
    }

    public static FahrzeugModel getCacheMieteFahrzeug() {
        return cacheMieteFahrzeug;
    }

    public static void setCacheMieteFahrzeug(FahrzeugModel cacheMieteFahrzeug) {
        App.cacheMieteFahrzeug = cacheMieteFahrzeug;
    }

    public static KundenModel getCacheMieteKunde() {
        return cacheMieteKunde;
    }

    public static void setCacheMieteKunde(KundenModel cacheMieteKunde) {
        App.cacheMieteKunde = cacheMieteKunde;
    }

    public static LocalTime getCacheMieteStartTime() {
        return cacheMieteStartTime;
    }

    public static void setCacheMieteStartTime(LocalTime cacheMieteStartTime) {
        App.cacheMieteStartTime = cacheMieteStartTime;
    }

    public static LocalTime getCacheMieteEndTime() {
        return cacheMieteEndTime;
    }

    public static void setCacheMieteEndTime(LocalTime cacheMieteEndTime) {
        App.cacheMieteEndTime = cacheMieteEndTime;
    }

    public static boolean isHinzuDirFromMiete() {
        return hinzuDirFromMiete;
    }

    public static void setHinzuDirFromMiete(boolean hinzuDirFromMiete) {
        App.hinzuDirFromMiete = hinzuDirFromMiete;
    }

    public static LocalDateTime getCurrDateTime() {
        return currDateTime;
    }

    public static void setCurrDateTime(LocalDateTime currDateTime) {
        App.currDateTime = currDateTime;
    }

    
    
    
    
    
}