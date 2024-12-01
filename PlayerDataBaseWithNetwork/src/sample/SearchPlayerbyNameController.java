package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class SearchPlayerbyNameController {

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
    private TextField textfield;
    @FXML
    private Label Welcome;
    private String name;
    private Player x;

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
        boolean NameExist = false;
        name = textfield.getText().trim().replaceAll("\\s+"," ");

        for(Player p : main.players)
        {
            if ( name.equalsIgnoreCase(p.getName() ) )
            {
                x=p;
                NameExist = true;
            }
        }
        if(NameExist == false)
        {
            notFound.setText("Player not found. Please try again.");
        }
        else{
            main.showOnePlayerInfoPage(x,1);
        }
    }

    public void resetButtonAction(ActionEvent actionEvent) {
        textfield.setText("");
        notFound.setText("");
    }

    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerPage();
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }


    public void keyTypeAction(KeyEvent keyEvent) {
        notFound.setText("");
    }
}
