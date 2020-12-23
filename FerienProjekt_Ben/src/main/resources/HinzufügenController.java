/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author benbartel
 */
public class HinzufügenController implements Initializable {

    @FXML
    private Label lblInstructions;
    @FXML
    private ChoiceBox<String> choiceErstellen;
    @FXML
    private TextField txtInput;
    @FXML
    private ListView<String> lvNeuesObjekt;
    @FXML
    private Button weiterBtn;
    @FXML
    private Button anwendenBtn;

    private int selectedChoice;
    private boolean var0Input;
    private String  input0;
    private boolean var1Input;
    private String  input1;
    private boolean var2Input;
    private String  input2;
    private boolean var3Input;
    private String  input3;
    private boolean var4Input;
    private String  input4;
    private boolean var5Input;
    private String  input5;
    private boolean var6Input;
    private String  input6;
    private boolean var7Input;
    private String  input7;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceErstellen.getItems().add("PKW");
        choiceErstellen.getItems().add("LKW");
        choiceErstellen.getItems().add("Van");
        choiceErstellen.getItems().add("Anhaenger");
        choiceErstellen.getItems().add("GeschaeftsKunde");
        choiceErstellen.getItems().add("PrivatKunde");
    }    

    private void fillListV(){
        
    }
    
    public String getChoice(){
        String choice = choiceErstellen.getValue();
        return choice;
    }
    
    @FXML
    private void btnWeiter(ActionEvent event) {
    }

    @FXML
    private void btnHome(ActionEvent event) {
    }

    private void changeTxtInput(){
        if(isVar0Input() == true){
            if(isVar1Input() == true){
                if(isVar2Input() == true){
                    if(isVar3Input() == true){
                        if(isVar4Input() == true){
                            if(isVar5Input() == true){
                                if(isVar6Input() == true){
                                    if(isVar7Input() == true){
                                        erstellen();
                                    }
                                    else{
                                        input7 = txtInput.getText();
                                        txtInput.clear();
                                        var7Input = true;
                                    }
                                }
                                else{
                                    input6 = txtInput.getText();
                                    txtInput.clear();
                                    var6Input = true;
                                }
                            }
                            else{
                                input5 = txtInput.getText();
                                txtInput.clear();
                                var5Input = true;
                            }
                        }
                        else{
                            input4 = txtInput.getText();
                            txtInput.clear();
                            var4Input = true;
                        }
                    }
                    else{
                        input3 = txtInput.getText();
                        txtInput.clear();
                        var3Input = true;
                    }
                }
                else{
                    input2 = txtInput.getText();
                    txtInput.clear();
                    var2Input = true;
                }
            }
            else{
                input1 = txtInput.getText();
                txtInput.clear();
                var1Input = true;
            }
        }
        else{
            input0 = txtInput.getText();
            txtInput.clear();
            var0Input = true;
        }
    }
    
    public void erstellen(){
        System.out.println(input0);
        System.out.println(input1);
        System.out.println(input2);
        System.out.println(input3);
        System.out.println(input4);
        System.out.println(input5);
        System.out.println(input6);
        System.out.println(input7);
    }
    
    @FXML
    private void btnAnwenden(ActionEvent event) {
        if(null != getChoice())switch (getChoice()) {
            case "PKW":
                selectedChoice = 1;
                break;
            case "LKW":
                selectedChoice = 2;
                break;
            case "Van":
                selectedChoice = 3;
                break;
            case "Anhaenger":
                selectedChoice = 4;
                break;
            case "GeschaeftsKunde":
                selectedChoice = 5;
                break;
            case "PrivatKunde":
                selectedChoice = 6;
                break;
            default:
                break;
        }
        
    }

    public boolean isVar0Input() {
        return var0Input;
    }

    public void setVar0Input(boolean var0Input) {
        this.var0Input = var0Input;
    }

    public boolean isVar1Input() {
        return var1Input;
    }

    public void setVar1Input(boolean var1Input) {
        this.var1Input = var1Input;
    }

    public boolean isVar2Input() {
        return var2Input;
    }

    public void setVar2Input(boolean var2Input) {
        this.var2Input = var2Input;
    }

    public boolean isVar3Input() {
        return var3Input;
    }

    public void setVar3Input(boolean var3Input) {
        this.var3Input = var3Input;
    }

    public boolean isVar4Input() {
        return var4Input;
    }

    public void setVar4Input(boolean var4Input) {
        this.var4Input = var4Input;
    }

    public boolean isVar5Input() {
        return var5Input;
    }

    public void setVar5Input(boolean var5Input) {
        this.var5Input = var5Input;
    }

    public boolean isVar6Input() {
        return var6Input;
    }

    public void setVar6Input(boolean var6Input) {
        this.var6Input = var6Input;
    }

    public boolean isVar7Input() {
        return var7Input;
    }

    public void setVar7Input(boolean var7Input) {
        this.var7Input = var7Input;
    }
    
    
}
