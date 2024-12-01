package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class ClubTotalIncomeController {


    @FXML
    private Button backButton;
    @FXML
    private Button previousButton;
    @FXML
    private Label Welcome;
    @FXML
    private Label incomeField;

    Main main = new Main();

    public void setMain(Main main) {
        this.main = main;
    }

    public void init() {
        Welcome.setText("Welcome "+ main.getClub().getName());
        MouseTouch.change(backButton);
        MouseTouch.change(previousButton);
        double Salary = 0;
        for(Player p: main.players)
        {

            Salary += p.getSalary()*365/7;

        }
        incomeField.setText( String.format("$   %10.2f", Salary) );

    }



    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        main.showSearchClubPage();
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }


}
