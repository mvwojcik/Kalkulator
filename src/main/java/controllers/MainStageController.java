package controllers;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainStageController {
    Property<String> a = new SimpleStringProperty();
    Property<String> b = new SimpleStringProperty();
    Property<String> sign = new SimpleStringProperty();
    char znak = 'x';
    int a1;
    int b1;
    int temp;
    boolean afterEqual;
    boolean temp1;
    @FXML
    private Label resultLabel;

    @FXML
    private Label signLabel;
    @FXML
    private TextField inputTextField;

    /////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    public void initialize() {
        a.setValue("0");
        b.setValue("0");
        resultLabel.textProperty().bindBidirectional(a);
        inputTextField.textProperty().bindBidirectional(b);
        signLabel.textProperty().bindBidirectional(sign);
        afterEqual = true;
        temp1=false;
    }

    @FXML
    public void setNumeral(ActionEvent actionEvent) {
        if (afterEqual == true) {
            b.setValue("0");
            afterEqual = false;
        }
        String temp = ((Button) actionEvent.getSource()).getText();
        b.setValue(b.getValue() + temp);
        b1 = Integer.parseInt(b.getValue());
        temp1=false;
    }

    @FXML
    public void setDigit(ActionEvent actionEvent) {
        if(temp1 == true)
        {
            wyborDzialania(actionEvent);
        }
        else {
            wyborDzialania(actionEvent);
            a1 = b1;
            b.setValue("0");
            b1 = Integer.parseInt(b.getValue());
            a.setValue(String.valueOf(a1));
            temp1 = true;
        }
        }

    @FXML
    public void equalsOnAction() {
        wyborZnaku(znak);
        b1 = temp;
        b.setValue(String.valueOf(b1));
        a.setValue(b.getValue());
        a1=0;
        signLabel.setText("");
        afterEqual = true;
    }

    @FXML
    public void opposite() {
        int temp = Integer.parseInt(b.getValue());
        b.setValue(String.valueOf(temp*-1));
        b1=Integer.parseInt(b.getValue());
    }


    public void wyborDzialania(ActionEvent actionEvent) {
        String temp = ((Button) actionEvent.getSource()).getText();
        znak = temp.charAt(0);
        signLabel.setText(String.valueOf(znak));
    }

    public void wyborZnaku(char x) {
        char z = x;
        switch (x) {
            case '+':
                System.out.println("Dodawanie");
                Dodawanie();
                break;
            case '-':
                System.out.println("Odejmowanie");
                Odejmowanie();
                break;
            case '*':
                System.out.println("Mnozenie");
                Mnożenie();
                break;
            case '/':
                System.out.println("Dzielenie");
                Dzielenie();
                break;
            default:
                break;
        }
    }

    private void Dzielenie() {
        if(b1==0)
        {
            System.out.println("NIE MOZNA DZIELIC PRZEZ ZERO! JAKIS ERROR WRZUCIC");
            b1=1;
        }
        temp = a1 / b1;

    }

    private void Mnożenie() {
        temp = a1 * b1;
    }

    private void Odejmowanie() {
        temp = a1 - b1;
    }

    private void Dodawanie() {
        temp = a1 + b1;
    }
}