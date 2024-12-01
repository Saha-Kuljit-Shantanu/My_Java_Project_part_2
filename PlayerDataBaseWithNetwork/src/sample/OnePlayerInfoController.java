package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class OnePlayerInfoController {

    private Player p;

    private int value;

    private ArrayList<Player> list;

    private ArrayList<Buyable> buyable;

    @FXML
    private Button backButton;
    @FXML
    private Button previousButton;
    @FXML
    private Label namefield;
    @FXML
    private Label countryfield;
    @FXML
    private Label agefield;
    @FXML
    private Label heightfield;
    @FXML
    private Label clubfield;
    @FXML
    private Label posfield;
    @FXML
    private Label numberfield;
    @FXML
    private Label salaryfield;

    public void initialize(){
        MouseTouch.change(backButton);
        MouseTouch.change(previousButton);
    }

    public void setValue(int value){
        this.value = value;
    }

    public void setValues(Player p)
    {
        namefield.setText(p.getName());
        countryfield.setText(p.getCountry());
        agefield.setText(String.valueOf(p.getAge()));
        heightfield.setText(String.valueOf(p.getHeight()));
        clubfield.setText(p.getClubname());
        posfield.setText(p.getPos());
        numberfield.setText(String.valueOf(p.getNumber()));
        salaryfield.setText(String.valueOf(p.getSalary()));
    }

    public void setList(ArrayList<Player> p)
    {
        list = p;
    }

    public void setBuyable(ArrayList<Buyable>b){ buyable = b;}

    Main main = new Main();


    public void setMain(Main main) {
        this.main = main;
    }

    public void previousButtonAction(ActionEvent actionEvent) throws IOException {
        if (value == 1) main.showSearchPlayerbyNamePage();
        if (value == 2) main.showSearchPlayerbyCountryClubPage();
        if (value == 3) main.showSearchPlayerbyPositionPage();
        if (value == 4) main.showSearchPlayerbySalaryRangePage();
        if (value == 14) main.showAddPlayerPage();
        if (value == 15) main.showBuyPlayerPage(buyable);
        if (value == 16) main.showSellPlayerPage();
        if (value == 17) main.showSearchClubPage();
        if (value >7 && value<11) main.showSearchClubPage();
        if ((value >4 && value <8) || (value>10 && value<14) || value == 20){
            main.showMultiplePlayerInfoPage(list,value-3);
        }
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }


}
