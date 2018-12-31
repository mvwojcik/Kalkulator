package controllers;


import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    BooleanProperty checkIfDigitHasBeenChosen = new SimpleBooleanProperty(false);
    BooleanProperty isOnNumber = new SimpleBooleanProperty(false);
    StringProperty lastdigit = new SimpleStringProperty("");
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
        this.inputTextField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    void setDigit(ActionEvent event) {
        if (firstRun.getValue())
        {
            this.lastdigit = this.digit;
            this.digit.setValue(((Button) event.getSource()).getText());
            this.signLabel.textProperty().bindBidirectional(this.digit);
            if (isOnNumber.getValue()) {

                if (!checkIfDigitHasBeenChosen.getValue()) {
                    this.moveup();

                    checkIfDigitHasBeenChosen.setValue(true);

                }
                isOnNumber.setValue(false);
                this.equalsOnAction2();

            }
        }
        else {
            if (isOnNumber.getValue()) {

                if (!checkIfDigitHasBeenChosen.getValue()) {
                    this.moveup();

                    checkIfDigitHasBeenChosen.setValue(true);

                }
                isOnNumber.setValue(false);
                this.equalsOnAction2();
                this.lastdigit = this.digit;
                this.digit.setValue(((Button) event.getSource()).getText());
                this.signLabel.textProperty().bindBidirectional(this.digit);
            }
        }
        }


    @FXML
    void setNumeral(ActionEvent event) {
        secondNumber.setValue(Integer.valueOf(secondNumberS.getValue() + ((Button) event.getSource()).getText()));
        secondNumberS.setValue(String.valueOf(secondNumber.get()));

        System.out.println("First Number:" + firstNumberS.getValue());
        System.out.println("Second number: " + secondNumber.getValue());
        isOnNumber.setValue(true);
    }

    @FXML
    void equalsOnAction() {
        getResult(this.digit);
        this.firstNumberS.setValue(String.valueOf(this.result1.getValue()));
        this.firstNumber.setValue(this.result1.getValue());
        this.clearSecondNumber();
        firstRun.setValue(false);

    }

    void equalsOnAction2() {
        getResult(this.lastdigit);
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

    private void getResult(StringProperty digit) {
        char x = digit.getValue().charAt(0);
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
        if (secondNumber.getValue()!=0) {
            return firstNumber.getValue() / secondNumber.getValue();
        }
        return firstNumber.getValue();
        }

    @FXML
    void opposite() {
        this.secondNumber.setValue(this.secondNumber.getValue() * -1);
        this.secondNumberS.setValue(String.valueOf(secondNumber.getValue()));
    }

}