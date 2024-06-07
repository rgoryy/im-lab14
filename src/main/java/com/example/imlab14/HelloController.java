package com.example.imlab14;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Label arrivalProcessText;

    @FXML
    private Label bankText;

    @FXML
    private TextField timeInput;

    @FXML
    private Label timeText;

    @FXML
    void startButtonClicked(ActionEvent event) {
        setAgentsModel();

        if (setModelTimeLimit()) {
            Model.setTimeLabel(timeText);
        }
    }

    @FXML
    void stopButtonClicked(ActionEvent event) {
        // todo
    }

    boolean setModelTimeLimit() {
        try {
            Model.setTimeLimit(Double.parseDouble(timeInput.getText()));
        } catch (NumberFormatException e) {
            timeInput.setText("Неверный формат данных");
            return false;
        }
        return true;
    }


    void setAgentsModel() {
        List<Operator> serviceOperators = new ArrayList<>();
        serviceOperators.add(new Operator());
        serviceOperators.add(new Operator());

        Service service = new Service(serviceOperators);
        Bank bank = new Bank(service, arrivalProcessText, bankText);
        ArrivalProcess arrivalProcess = new ArrivalProcess(bank);

        List<Agent> systemAgents = new ArrayList<>();
        systemAgents.add(bank);
        systemAgents.add(arrivalProcess);

        Model.setAgents(systemAgents);
        Model.run();
    }
}
