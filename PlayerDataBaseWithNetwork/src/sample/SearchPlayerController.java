package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class SearchPlayerController {

    @FXML
    private Button a;
    @FXML
    private Button b;
    @FXML
    private Button c;
    @FXML
    private Button d;
    @FXML
    private Button e;
    @FXML
    private Button f;

    Main main = new Main();
    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){
        MouseTouch.change(a);
        MouseTouch.change(b);
        MouseTouch.change(c);
        MouseTouch.change(d);
        MouseTouch.change(e);
        MouseTouch.change(f);
    }

    public void SearchbyNameAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerbyNamePage();
    }

    public void SearchbyCountryClubAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerbyCountryClubPage();
    }

    public void SearchbyPositionAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerbyPositionPage();
    }

    public void SearchbySalaryRangeAction(ActionEvent actionEvent) throws IOException {
        main.showSearchPlayerbySalaryRangePage();
    }

    public void CountryWisePlayerCount(ActionEvent actionEvent) throws IOException {
        Map<String,Integer> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(Player p: main.players)
        {
            if (map.containsKey(p.getCountry()))
            {
                map.put(p.getCountry(), map.get(p.getCountry()) + 1);
            }
            else
            {
                map.put(p.getCountry(), 1);
            }
        }
        main.showCountryWisePlayerCountPage(map);
    }

    public void backAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }



}
