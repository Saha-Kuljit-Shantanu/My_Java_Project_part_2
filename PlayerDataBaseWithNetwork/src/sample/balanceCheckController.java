package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class balanceCheckController {

    @FXML
    private Button backButton;
    @FXML
    private Label balanceField;
    @FXML
    private Label Welcome;
    Main main = new Main();

    public void init(){
        Welcome.setText("Welcome "+ main.getClub().getName());
        balanceField.setText("$  "+main.getClub().getBalance());
        MouseTouch.change(backButton);
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }
}
