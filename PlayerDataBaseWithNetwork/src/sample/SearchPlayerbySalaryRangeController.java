package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPlayerbySalaryRangeController {

    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button backButton;
    @FXML
    private Label Welcome;
    @FXML
    private Label ErrorLabel;
    @FXML
    private TextField upperLimit;
    @FXML
    private TextField lowerLimit;

    private ArrayList<Player> x = new ArrayList<>();
    private int high;
    private int low;

    Main main = new Main();

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(){
        Welcome.setText("Welcome "+ main.getClub().getName());
        MouseTouch.change(searchButton);
        MouseTouch.change(resetButton);
        MouseTouch.change(previousButton);
        MouseTouch.change(backButton);
    }

    public void searchButtonAction(ActionEvent actionEvent) {
        try {
            high = Integer.parseInt(upperLimit.getText().trim().replaceAll("\\s+","").replaceAll(",","") );
            low = Integer.parseInt(lowerLimit.getText().trim().replaceAll("\\s+","").replaceAll(",",""));
            if(high < low) ErrorLabel.setText("Upper Limit cannot be lower than Lower Limit");
            else {
                for (Player p : main.players) {
                    if (p.getSalary() >= low && p.getSalary() <= high) {
                        x.add(p);

                    }
                }
                if (x.size() == 0) {
                    ErrorLabel.setText("No such Player with this weekly salary range");
                } else if (x.size() == 1) {
                    main.showOnePlayerInfoPage(x.get(0), 4);
                } else {
                    main.showMultiplePlayerInfoPage(x,4);
                }
            }
        }
        catch (Exception e){
            ErrorLabel.setText("Invalid input. Enter Numbers");
        }

    }

    public void resetButtonAction(ActionEvent actionEvent) {
        ErrorLabel.setText("");
        upperLimit.setText("");
        lowerLimit.setText("");
    }

    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerPage();
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }

    public void keyTypeAction1(KeyEvent keyEvent) {
        ErrorLabel.setText("");
    }

    public void keyTypeAction2(KeyEvent keyEvent) {
        ErrorLabel.setText("");
    }
}
