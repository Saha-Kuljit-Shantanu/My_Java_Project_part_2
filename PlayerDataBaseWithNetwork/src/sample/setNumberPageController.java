package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class setNumberPageController {

    @FXML
    private Button numberSet;
    @FXML
    private TextField numberfield;
    @FXML
    private Label sameNumberLabel;

    private Buyable buyable;
    private ArrayList<Buyable> buyables;

    Main main = new Main();

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){
        MouseTouch.change(numberSet);
    }

    public void numberSetOnAction(ActionEvent actionEvent) throws IOException {
        String str = numberfield.getText();
        boolean bool = true;
        boolean bool2 = false;
        try{
            if(numberfield.getText().equals("")) sameNumberLabel.setText("Enter a number");
            else{
                for(Player p: main.players){
                    if(Integer.parseInt(str) == p.getNumber()){
                        bool = false;
                        sameNumberLabel.setText("You have a player of this number");
                    }
                }
                if(bool == true) {
                    buyable.getPlayer().setNumber(Integer.parseInt(str));
                    main.players.add(buyable.getPlayer());


                    // main.getClient().getPlayers().add(b.getPlayer());
                    buyable.getPlayer().showInfo();
                    Buyable2 x = new Buyable2(buyable.getPlayer(), buyable.getPrice(),Integer.parseInt(str));
                    x.getPlayer().showInfo();
                    main.getClient().getNetworkUtil().write(x);
                    main.showBuyPlayerPage(buyables);
                }
            }
        }catch(NumberFormatException e){
            numberfield.setText("Number is to be integer");
        }

    }

    public void setBuyable(Buyable b) {
        this.buyable= b;
    }

    public void setBuyables(ArrayList<Buyable> buyables) {
        this.buyables = buyables;
    }
}
