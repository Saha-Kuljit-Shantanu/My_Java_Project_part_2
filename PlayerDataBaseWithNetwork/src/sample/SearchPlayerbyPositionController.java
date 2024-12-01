package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPlayerbyPositionController {

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
    private Label notFound;
    @FXML
    private TextField textfield;
    private ArrayList<Player> x = new ArrayList<>();
    private String pos;
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

        pos = textfield.getText().trim().replaceAll("\\s+","").replaceAll("-","");
        for(Player p : main.players)
        {
            if ( pos.equalsIgnoreCase(p.getPos() ) )
            {
              x.add(p);

            }
        }
        if(x.size() == 0)
        {
            notFound.setText("No such Player with this position");
        }
        else if(x.size() == 1)
        {
            main.showOnePlayerInfoPage(x.get(0),3);
        }
        else
        {
            main.showMultiplePlayerInfoPage(x,3);
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
