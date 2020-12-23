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
    private static int choiceForEditViewIndex;
    private static String choiceForEditViewTyp;
    
    private static LocalDate cacheMieteStartDate;
    private static LocalDate cacheMieteEndDate;
    private static LocalTime cacheMieteStartTime;
    private static LocalTime cacheMieteEndTime;
    private static FahrzeugModel cacheMieteFahrzeug;
    private static KundenModel cacheMieteKunde;
    
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
        
        //es werden fürs testen eine menge fahrzeuge und Kunden hinzugefügt.
        int fahrzeugNum = fahrzeuge.size() + 1;
        LkwModel m1 = new LkwModel(170, "VW", "Truck", "Schwarz", true, false, false, 24, fahrzeugNum);
        fahrzeuge.add(m1);
        fahrzeugNum = fahrzeuge.size() + 1;
        PkwModel m2 = new PkwModel(5, 210, "Tesla", "Model S", "Schwarz", true, false, false, 30, fahrzeugNum);
        fahrzeuge.add(m2);
        fahrzeugNum = fahrzeuge.size() + 1;
        AnhaengerModel m3 = new AnhaengerModel(70, "Kaefer", "Anhaenger", "Weiss", true, false, false, 24, fahrzeugNum);
        fahrzeuge.add(m3);
        fahrzeugNum = fahrzeuge.size() + 1;
        VanModel m4 = new VanModel(true, "VW", "Van", "Schwarz", true, false, false, 24, fahrzeugNum);
        fahrzeuge.add(m4);
        
        
        
        int kundenNum = kunden.size() + 1;
        PrivatkundeModel k1 = new PrivatkundeModel("017642069", "Ben", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k1);
        kundenNum = kunden.size() + 1;
        PrivatkundeModel k2 = new PrivatkundeModel("017642069", "Paul", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k2);
        kundenNum = kunden.size() + 1;
        GeschaeftskundeModel k3 = new GeschaeftskundeModel("Bremen", "017642069", "Matz", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k3);
        kundenNum = kunden.size() + 1;
        GeschaeftskundeModel k4 = new GeschaeftskundeModel("Duisburg", "017642069", "Peer", "Bartel", kundenNum, "0167889945", "Hamburg");
        kunden.add(k4);
        
        
        
        launch();
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

    
    
    
    
    
}