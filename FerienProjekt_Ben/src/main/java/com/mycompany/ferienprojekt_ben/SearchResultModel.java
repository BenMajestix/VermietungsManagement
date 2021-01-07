/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ferienprojekt_ben;

/**
 *
 * @author benbartel
 */
public class SearchResultModel {
    String output;
    int searchScore;
    int superClass;

    //Ein Model für ein Ergebnis aus der Suche, diese werden anhand ihres searchScores geordnet nach Relevanz
    //Und dann in der ListView angezeigt
    public SearchResultModel(String output, int searchScore, int superClass) {
        this.output = output;
        this.searchScore = searchScore;
        this.superClass = superClass;
    }

    

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getSearchScore() {
        return searchScore;
    }

    public void setSearchScore(int searchScore) {
        this.searchScore = searchScore;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }
    
    
}
