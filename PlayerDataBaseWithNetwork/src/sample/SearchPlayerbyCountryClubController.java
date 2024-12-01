package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPlayerbyCountryClubController {

    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button backButton;
    @FXML
    private Label notFound;
    @FXML
    private TextField countryTextfield;
    @FXML
    private Label Welcome;
    private ArrayList<Player> x = new ArrayList<>();
    private String country;
    private String clubname;
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

    public void searchButtonAction(ActionEvent actionEvent) throws IOException {

        country = countryTextfield.getText().trim().replaceAll("\\s+"," ");
        clubname = "ANy";

        for(Player p : main.players)
        {
            if ( country.equalsIgnoreCase(p.getCountry() ) && (  clubname.equalsIgnoreCase("ANY") || clubname.equalsIgnoreCase(p.getClubname() ) ) )
            {

                x.add(p);
            }
        }
        if(x.size() == 0)
        {
            notFound.setText("No player found with the country or club.");
        }
        else if(x.size() == 1)
        {
            main.showOnePlayerInfoPage(x.get(0),2);
        }
        else
        {
            main.showMultiplePlayerInfoPage(x,2);
        }
    }

    public void resetButtonAction(ActionEvent actionEvent) {
        countryTextfield.setText("");
        notFound.setText("");
    }

    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerPage();
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }

    public void keyTypeAction1(KeyEvent keyEvent) {
        notFound.setText("");
    }

    public void keyTypeAction2(KeyEvent keyEvent) {
        notFound.setText("");
    }


}
