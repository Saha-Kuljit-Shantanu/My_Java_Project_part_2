package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class buyPlayerController {

    @FXML
    private Button viewDetail;
    @FXML
    private Button backButton;
    @FXML
    private Button buyButton;
    @FXML
    private Label Welcome;
    @FXML
    private ListView<String> BuyableList;
    @FXML
    private Label errorLabel;
    private ArrayList<Buyable> buyable = new ArrayList<>();
    private ObservableList<String> list = FXCollections.observableArrayList();
    private Thread thr;
    private boolean isRunning = true;
    Main main = new Main();

    public void init(ArrayList<Buyable> buyable){
        Welcome.setText("Welcome "+ main.getClub().getName());
        list.clear();
        this.buyable = buyable;
        for(Buyable b: buyable){
            list.add(String.valueOf(b));
        }
      //  BuyableList.setStyle("-fx-font-size: 16 px; -fx-font-family: 'Comic Sans MS'; ");
        BuyableList.setStyle("-fx-font-size: 16 px; ");
        BuyableList.setItems(list);
        MouseTouch.change(viewDetail);
        MouseTouch.change(buyButton);
        MouseTouch.change(backButton);

    }
    public void initialize(){
        thr = new Thread(this::handleThread);
        thr.start();
    }
    public void setMain(Main main) {
        this.main = main;
    }

    public void handleThread(){
       while(isRunning){

           Platform.runLater(() ->{
            /*   try {
                   main.getClient().getNetworkUtil().write(main.getClub());
               } catch (IOException e) {
                   e.printStackTrace();
               }
               while(main.getClient().getFlag() == false) {
                  // System.out.println("running");

               }*/
               ArrayList<Buyable>b = main.getClient().getBuyable();
               //main.getClient().setFlag(false);
               init(b);
           });
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    public void viewDetailAction(ActionEvent actionEvent) throws IOException {
        if (BuyableList.getSelectionModel().getSelectedItem()==null) errorLabel.setText("Select first");
        else{
            for(Buyable b : buyable){
                if(BuyableList.getSelectionModel().getSelectedItem().equals(String.valueOf(b))){
                    main.showOnePlayerInfoPage(b.getPlayer(), buyable,15);
                }
            }
        }
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        isRunning = false;
        main.showMenuPage();
    }

    public void buyAction(ActionEvent actionEvent) throws IOException {
       // isRunning = true;
        if (BuyableList.getSelectionModel().getSelectedItem()==null) errorLabel.setText("Select first");

        else{
            for(Buyable b : buyable){
                if(BuyableList.getSelectionModel().getSelectedItem().equals(String.valueOf(b))){
                    if(main.getClub().getBalance() < b.getPrice()) errorLabel.setText("Not enough balance");
                    else{
                     //   b.setClubName(main.getClub().getName());
                        b.getPlayer().setClubname(main.getClub().getName());
                        main.getClub().setBalance(main.getClub().getBalance()-b.getPrice());
                        System.out.println(main.getClub().getBalance());
                        main.getClient().getNetworkUtil().write(b);
                        main.showSetNumberPage(buyable,b);
                        break;

                    }
                }
            }
        }

    }


    public void mouseClickAction(MouseEvent mouseEvent) {
        isRunning = false;
        errorLabel.setText("");
    }
}
