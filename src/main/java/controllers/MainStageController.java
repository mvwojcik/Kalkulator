package controllers;


import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainStageController {
    IntegerProperty firstNumber = new SimpleIntegerProperty(0);
    IntegerProperty secondNumber = new SimpleIntegerProperty(0);
    StringProperty digit = new SimpleStringProperty("");
    IntegerProperty result1 = new SimpleIntegerProperty(0);
    StringProperty firstNumberS = new SimpleStringProperty("0");
    StringProperty secondNumberS = new SimpleStringProperty("0");
    BooleanProperty firstRun = new SimpleBooleanProperty(true);
    BooleanProperty check = new SimpleBooleanProperty(false);
    BooleanProperty isOnNumber = new SimpleBooleanProperty(false);
    @FXML
    private Label resultLabel;
    @FXML
    private Label signLabel;
    @FXML
    private TextField inputTextField;

    @FXML
    private void initialize() {
        this.resultLabel.setVisible(true);
        secondNumberS.bindBidirectional(this.inputTextField.textProperty());
        this.firstNumberS.bindBidirectional(this.resultLabel.textProperty());
    }

    @FXML
    void setDigit(ActionEvent event) {
        this.digit.setValue(((Button) event.getSource()).getText());
        this.signLabel.textProperty().bindBidirectional(this.digit);
        if (!check.getValue()) {
            this.moveup();

            check.setValue(true);
        }
        isOnNumber.setValue(true);
    }


    @FXML
    void setNumeral(ActionEvent event) {
        secondNumber.setValue(Integer.valueOf(secondNumberS.getValue() + ((Button) event.getSource()).getText()));
        secondNumberS.setValue(String.valueOf(secondNumber.get()));

        System.out.println("First Number:" + firstNumberS.getValue());
        System.out.println("Second number: " + secondNumber.getValue());
        isOnNumber.setValue(false);
    }

    @FXML
    void equalsOnAction() {
        getResult();
        this.firstNumberS.setValue(String.valueOf(this.result1.getValue()));
        this.firstNumber.setValue(this.result1.getValue());
        this.clearSecondNumber();
        firstRun.setValue(false);

    }

    private void clearSecondNumber() {
        secondNumber.setValue(0);
        secondNumberS.setValue(String.valueOf(secondNumber.get()));
    }


    void moveup() {
        this.firstNumberS.setValue(this.secondNumberS.getValue());
        this.secondNumberS.setValue("0");
        this.secondNumber.setValue(0);
        firstNumber.setValue(Integer.valueOf(firstNumberS.getValue()));
    }

    private void getResult() {
        char x = this.signLabel.getText().charAt(0);
        switch (x) {
            case '+':
                this.result1.setValue(add());
                break;
            case '-':
                this.result1.setValue(substract());
                break;
            case '*':
                this.result1.setValue(multiply());
                break;
            case '/':
                this.result1.setValue(divide());
                break;
        }
    }

    public int add() {
        int a = firstNumber.getValue();
        int b = secondNumber.getValue();
        return a + b;
    }

    public int substract() {
        int a = firstNumber.getValue();
        int b = secondNumber.getValue();
        return a - b;
    }

    public int multiply() {
        return firstNumber.getValue() * secondNumber.getValue();
    }

    public int divide() {
        return firstNumber.getValue() / secondNumber.getValue();
    }

    @FXML
    void opposite(ActionEvent event) {
        this.secondNumber.setValue(this.secondNumber.getValue() * -1);
        this.secondNumberS.setValue(String.valueOf(secondNumber.getValue()));
    }

}