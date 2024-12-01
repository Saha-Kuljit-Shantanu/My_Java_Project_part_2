package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class sellPlayerController {

    @FXML
    private Button viewDetail;

    @FXML
    private Button backButton;

    @FXML
    private Button sellButton;

    @FXML
    private TextField priceField;

    @FXML
    private ChoiceBox playerField;

    @FXML
    private Label errorFound;

    @FXML
    private Label Welcome;

    private ObservableList<String> playerList = FXCollections.observableArrayList();
    Main main = new Main();
    public void setMain(Main main){
        this.main = main;
    }

    public void init(){
        Welcome.setText("Welcome "+ main.getClub().getName());
       for(Player p: main.getClient().getPlayers()){
           playerList.add(p.getName());
       }
       playerField.getItems().addAll(playerList) ;
        MouseTouch.change(viewDetail);
        MouseTouch.change(sellButton);
        MouseTouch.change(backButton);
    }

    public void sellAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        try{
            String str = (String) playerField.getSelectionModel().getSelectedItem();
            double price = Double.parseDouble(priceField.getText());
            Player player = new Player();
            for(Player p: main.getClient().getPlayers()){
                if(p.getName().equals(str)){
                    player=p;

                }
            }

            playerField.getItems().remove(str);
            main.getClient().getPlayers().remove(player);
            sellPlayer sp = new sellPlayer(player,price);
           // main.allPlayers.remove(player); also remove from file. lessen balance too;
            main.getClub().setBalance( main.getClub().getBalance()+price );
            System.out.println(main.getClub().getBalance());
            for(Player p: main.players){
                if(p.getName().equals(str)){
                    player=p;
                }
            }
            main.players.remove(player);
            priceField.setText("");

            main.getClient().getNetworkUtil().write(sp);


         //   new WriteThreadClient(main.getClient().getNetworkUtil(),sp);
        }
        catch(NumberFormatException e){
            errorFound.setText("Price value has to be double");
        }
        catch(NullPointerException e){
            errorFound.setText("Information missing");
        }
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }

    public void viewDetailAction(ActionEvent actionEvent) throws IOException {
        String str = (String) playerField.getSelectionModel().getSelectedItem();
        if(playerField.getSelectionModel().getSelectedItem() == null) errorFound.setText("Select first");
        else{
            for(Player p: main.getClient().getPlayers()){
                if(str.equals(p.getName())){
                    main.showOnePlayerInfoPage(p,16);
                }
            }
        }
    }
}
