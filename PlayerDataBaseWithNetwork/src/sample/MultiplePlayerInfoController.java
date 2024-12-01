package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiplePlayerInfoController {


    @FXML
    private Label ErrorLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button viewDetail;
    @FXML
    private TableView <Player> table;
    @FXML
    private TableColumn<Player,String> name;
    @FXML
    private TableColumn<Player,String> country;
    @FXML
    private TableColumn<Player,Integer> age;
    @FXML
    private TableColumn<Player,Double> height;
    @FXML
    private TableColumn<Player,String> club;
    @FXML
    private TableColumn<Player,String> pos;
    @FXML
    private TableColumn<Player,Integer> number;
    @FXML
    private TableColumn<Player,Double> salary;

    private int value;

    private ObservableList<Player>list = FXCollections.observableArrayList();

    private ArrayList<Player> players;

    Main main = new Main();

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){
        MouseTouch.change(backButton);
        MouseTouch.change(previousButton);
        MouseTouch.change(viewDetail);
    }

    public void setList(ArrayList<Player> p)
    {
        players= p;
        for(Player x: p)
        {
            list.add(x);
        }

        name.setCellValueFactory(new PropertyValueFactory<Player,String>("name"));
        country.setCellValueFactory(new PropertyValueFactory<Player,String>("country"));
        age.setCellValueFactory(new PropertyValueFactory<Player,Integer>("age"));
        height.setCellValueFactory(new PropertyValueFactory<Player,Double>("height"));
        club.setCellValueFactory(new PropertyValueFactory<Player,String>("clubname"));
        pos.setCellValueFactory(new PropertyValueFactory<Player,String>("pos"));
        number.setCellValueFactory(new PropertyValueFactory<Player,Integer>("number"));
        salary.setCellValueFactory(new PropertyValueFactory<Player,Double>("salary"));

        table.setItems(list);

    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        if (value == 2) main.showSearchPlayerbyCountryClubPage();
        if (value == 3) main.showSearchPlayerbyPositionPage();
        if (value == 4) main.showSearchPlayerbySalaryRangePage();
        if (value >7 && value<11) main.showSearchClubPage();
        if (value == 17) main.showSearchClubPage();
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }


    public void viewSingleButtonAction(ActionEvent actionEvent) throws IOException {
        Player p = table.getSelectionModel().getSelectedItem();

        if(p== null) ErrorLabel.setText("You have to select first");
        else main.showOnePlayerInfoPage(players, p, value);

    }

    public void mouseClickAction(MouseEvent mouseEvent) {
        ErrorLabel.setText("");
    }
}
