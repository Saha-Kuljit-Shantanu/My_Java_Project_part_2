package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

public class SearchClubController {


    private ArrayList<Player> x = new ArrayList<>();

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


    public void maxSalaryClubPlayerButtonAction(ActionEvent actionEvent) throws IOException {
        double max = 0;
        for(Player p : main.players)
        {

            if ( p.getSalary() > max)
            {
                max = p.getSalary();
            }
        }
        for(Player p :main.players)
        {

            if ( p.getSalary() == max)
            {
                x.add(p);
            }

        }

        if(x.size() ==1)
        {
            main.showOnePlayerInfoPage(x.get(0), 8);
        }
        else{
            main.showMultiplePlayerInfoPage(x,8);
        }
    }

    public void maxAgeClubPlayerButtonAction(ActionEvent actionEvent) throws IOException {
        int max = 0;
        for(Player p : main.players)
        {

            if ( p.getAge() > max)
            {
                max = p.getAge() ;
            }

        }
        for(Player p :main.players)
        {
            if ( p.getAge() == max)
            {
                x.add(p);
            }
        }

        if(x.size() ==1)
        {
            main.showOnePlayerInfoPage(x.get(0), 9);
        }
        else{
            main.showMultiplePlayerInfoPage(x,9);
        }
    }

    public void maxHeightClubPlayerButtonAction(ActionEvent actionEvent) throws IOException {
        double max = 0;
        for(Player p : main.players)
        {
            if ( p.getHeight() > max)
            {
                max = p.getHeight();
            }
        }
        for(Player p : main.players)
        {

            if ( p.getHeight() == max)
            {
                x.add(p);
            }

        }
        if(x.size() ==1)
        {
            main.showOnePlayerInfoPage(x.get(0), 10);
        }
        else{
            main.showMultiplePlayerInfoPage(x,10);
        }
    }

    public void ClubTotalYearlySalaryButtonAction(ActionEvent actionEvent) throws IOException {
        main.showClubTotalYearlyIncomePage();
    }


    public void backAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }

    public void allPlayerButton(ActionEvent actionEvent) throws IOException {
        for(Player p : main.players)
        {
            x.add(p);
        }
        if(x.size() ==1)
        {
            main.showOnePlayerInfoPage(x.get(0), 17);
        }
        else{
            main.showMultiplePlayerInfoPage(x,17);
        }
    }
}
