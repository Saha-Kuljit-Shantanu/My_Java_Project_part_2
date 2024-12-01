package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MainMenuController {

    @FXML
    private Button SearchPlayerButton;
    @FXML
    private Button SearchClubButton;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private Button sellPlayerButton;
    @FXML
    private Button buyPlayerButton;
    @FXML
    private Button checkBalanceButton;
    @FXML
    private Label Welcome;
    Main main = new Main();

    public void setMain(Main main) {
        this.main = main;
    }

    public void init(){
        Welcome.setText("Welcome "+ main.getClub().getName());
        MouseTouch.change(SearchPlayerButton);
        MouseTouch.change(SearchClubButton);
        MouseTouch.change(addPlayerButton);
        MouseTouch.change(buyPlayerButton);
        MouseTouch.change(sellPlayerButton);
        MouseTouch.change(checkBalanceButton);
        MouseTouch.change(LogoutButton);
    }

    public void SearchPlayer(ActionEvent actionEvent) throws IOException {
       main.showSearchPlayerPage();

    }

    public void SearchClub(ActionEvent actionEvent) throws IOException {
        main.showSearchClubPage();
    }

    public void addPlayer(ActionEvent actionEvent) throws IOException {
        main.showAddPlayerPage();
    }

    public void ExitAction(ActionEvent action ) throws IOException {
        ArrayList<Club> clubs = new ArrayList<>();

        main.getClub().setIsAlreadyConnected(false);
        //main.getClub().setBalance(main.getClub().getBalance());

        String line;
        Scanner val ;
        val = new Scanner(main.clubfile);


        while(val.hasNext())
        {

            line = val.nextLine();
            String[] s= new String[4];
            s= line.split(",");
            Club c = new Club( s[0],s[1],Double.parseDouble(s[2]),Boolean.parseBoolean(s[3]));
            clubs.add(c);

        }

        for(Club x: clubs)
        {
            if(x.getName().equals(main.getClub().getName())) clubs.set(clubs.indexOf(x),main.getClub());
        }
        FileWriter f = new FileWriter(main.clubfile,false);

        for(Club x: clubs)
        {
            f.write(x.getName()+","+x.getPassword()+","+x.getBalance()+","+x.getIsAlreadyConnected()+"\n");
        }
        f.close();
       /* main.clubs.clear();
        String line;
        Scanner val ;
        val = new Scanner(main.clubfile);


        while(val.hasNext())
        {

            line = val.nextLine();
            String[] s= new String[4];
            s= line.split(",");
            Club c = new Club( s[0],s[1],Double.parseDouble(s[2]),Boolean.parseBoolean(s[3]));
            main.clubs.add(c);

        }*/

       // System.exit(1);
        try {
            main.getClient().getNetworkUtil().write("stop");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public void sellPlayer(ActionEvent actionEvent) throws IOException {
        main.showSellPlayerPage();
        //new WriteThreadClient(main.getClient().getNetworkUtil(),main.getClient().getPlayers());
    }

    public void buyPlayer(ActionEvent actionEvent) throws IOException {

        //new WriteThreadClient(main.getClient().getNetworkUtil(),main.getClub());
    /*    main.getClient().getNetworkUtil().write(main.getClub());
        while(main.getClient().getFlag() == false){
           // System.out.println("running");

        }*/

        ArrayList<Buyable> list = main.getClient().getBuyable();
        main.getClient().setFlag(false);

        main.showBuyPlayerPage(list);

    }

    public void checkBalanceAction(ActionEvent actionEvent) throws IOException {
        main.showBalancePage();
    }
}
