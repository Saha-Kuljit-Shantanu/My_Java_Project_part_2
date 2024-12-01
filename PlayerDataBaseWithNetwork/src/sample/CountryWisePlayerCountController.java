package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Map;

public class CountryWisePlayerCountController {

    private Map<String,Integer> map;
    @FXML
    private Button backButton;
    @FXML
    private Button previousButton;
    @FXML
    private TableView<Map.Entry<String,Integer>> table;
    @FXML
    private TableColumn<Map.Entry<String,Integer>, String> Country;
    @FXML
    private TableColumn<Map.Entry<String,Integer>, Integer>  playerCount;
    @FXML
    private Label Welcome;




    Main main = new Main();
    public void setMain(Main main) {
        this.main = main;
    }

    public void init(){
        Welcome.setText("Welcome "+ main.getClub().getName());
        MouseTouch.change(backButton);
        MouseTouch.change(previousButton);
    }

    public void setMap( Map<String,Integer> map) {
        this.map = map;
        setList();
    }

    public void setList()
    {
        String s = "        "; // 8 spaceBar characters
        Country = new TableColumn(s+"List of Countries"+s);
        Country.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, String> x) {
                return new SimpleStringProperty(x.getValue().getKey());
            }
        });

        playerCount = new TableColumn(s+"Count of Players"+s);
        playerCount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<String, Integer>, Integer> x) {
                return new SimpleIntegerProperty(x.getValue().getValue()).asObject();
            }
        });

        ObservableList<Map.Entry<String,Integer>> list= FXCollections.observableArrayList(map.entrySet());
        table.setItems(list);
        table.getColumns().setAll(Country,playerCount);



    }

    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerPage();
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }
}
