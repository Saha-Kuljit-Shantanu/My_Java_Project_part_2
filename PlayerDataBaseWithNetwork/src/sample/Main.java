package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application {

    private Stage stage;

    private String clubfilename = "Club.txt";
    private boolean madeChange= false;
    public boolean con = true;
    private Club club;
    private Client client;

    public File clubfile = new File(clubfilename);
    public ArrayList<Player> players = new ArrayList<>();

    public ArrayList<Club> clubs = new ArrayList<>();

    public Stage getStage() {
        return stage;
    }
    public void setMadeChange(boolean t) { madeChange=t;}
    public boolean getMadeChange(){ return madeChange;}
    public void setClub(Club club){this.club = club; }
    public Club getClub(){return club;}
    public void setClient(Client client){ this.client = client;}
    public Client getClient(){ return client;}


    @Override
    public void start(Stage primaryStage) throws Exception{
        /*clubs.clear();
        String line;
        Scanner val ;
        val = new Scanner(clubfile);


        while(val.hasNext())
        {

            line = val.nextLine();
            String[] s= new String[4];
            s= line.split(",");
            Club c = new Club( s[0],s[1],Double.parseDouble(s[2]),Boolean.parseBoolean(s[3]));
            clubs.add(c);

        }*/
        stage = primaryStage;
        stage.setResizable(false);
        stage.setOnCloseRequest(e ->
        {
            if(con == false){
                ArrayList<Club> clubs = new ArrayList<>();

                club.setIsAlreadyConnected(false);
                //main.getClub().setBalance(main.getClub().getBalance());

                String line;
                Scanner val = null;
                try {
                    val = new Scanner(clubfile);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }



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
                    if(x.getName().equals(club.getName())) clubs.set(clubs.indexOf(x),club);
                }
                FileWriter f = null;
                try {
                    f = new FileWriter(clubfile,false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                for(Club x: clubs)
                {
                    try {
                        f.write(x.getName()+","+x.getPassword()+","+x.getBalance()+","+x.getIsAlreadyConnected()+"\n");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                try {
                    f.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    client.getNetworkUtil().write("stop");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
           else{ System.exit(1);}
            // System.exit(1);
        });

       // showMenuPage();
        //con = true;
        showLoginPage();
    }

    public void showLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginPage.fxml"));
        Parent root = loader.load();

        LoginPageController ctrl = loader.getController();
        ctrl.setMain(this);
       // con = true;

        stage.setScene(new Scene(root,600,400));
        stage.show();
      //  stage.setOnCloseRequest(e-> System.exit(1));
    }

    public void showMenuPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();

        MainMenuController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSearchPlayerPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayerOptions.fxml"));
        Parent root = loader.load();


        SearchPlayerController ctrl = loader.getController();
        ctrl.setMain(this);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSearchClubPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchClubOptions.fxml"));
        Parent root = loader.load();


        SearchClubController ctrl = loader.getController();
        ctrl.setMain(this);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSearchPlayerbyNamePage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayerbyName.fxml"));
        Parent root = loader.load();


        SearchPlayerbyNameController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSearchPlayerbyCountryClubPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayerbyCountryClub.fxml"));
        Parent root = loader.load();


        SearchPlayerbyCountryClubController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSearchPlayerbyPositionPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayerbyPosition.fxml"));
        Parent root = loader.load();


        SearchPlayerbyPositionController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSearchPlayerbySalaryRangePage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SearchPlayerbySalaryRange.fxml"));
        Parent root = loader.load();


        SearchPlayerbySalaryRangeController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showCountryWisePlayerCountPage( Map<String,Integer> map) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CountryWisePlayerCount.fxml"));
        Parent root = loader.load();


        CountryWisePlayerCountController ctrl = loader.getController();
        ctrl.setMap(map);
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }



    public void showClubTotalYearlyIncomePage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClubTotalIncome.fxml"));
        Parent root = loader.load();


        ClubTotalIncomeController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showOnePlayerInfoPage(Player p,int value) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OnePlayerInfo.fxml"));
        Parent root = loader.load();


        OnePlayerInfoController ctrl = loader.getController();
        ctrl.setValues(p);
        ctrl.setValue(value);
        ctrl.setMain(this);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showOnePlayerInfoPage(Player p,ArrayList<Buyable> b, int value) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OnePlayerInfo.fxml"));
        Parent root = loader.load();


        OnePlayerInfoController ctrl = loader.getController();
        ctrl.setValues(p);
        ctrl.setValue(value);
        ctrl.setBuyable(b);
        ctrl.setMain(this);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showOnePlayerInfoPage(ArrayList<Player>list, Player p, int value) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OnePlayerInfo.fxml"));
        Parent root = loader.load();


        OnePlayerInfoController ctrl = loader.getController();
        ctrl.setValues(p);
        ctrl.setValue(value+3);
        ctrl.setList(list);
        ctrl.setMain(this);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showMultiplePlayerInfoPage(ArrayList<Player> p,int value) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MultiplePlayerInfo.fxml"));
        Parent root = loader.load();


        MultiplePlayerInfoController ctrl = loader.getController();
        ctrl.setList(p);
        ctrl.setValue(value);
        ctrl.setMain(this);


        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showAddPlayerPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addPlayer.fxml"));
        Parent root = loader.load();


        addPlayerController ctrl = loader.getController();
        ctrl.setMain(this);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public void showSellPlayerPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sellPlayer.fxml"));
        Parent root = loader.load();


        sellPlayerController ctrl = loader.getController();

        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showBuyPlayerPage(ArrayList<Buyable>b) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buyPlayer.fxml"));
        Parent root = loader.load();


        buyPlayerController ctrl = loader.getController();

        ctrl.setMain(this);
        ctrl.init(b);

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSetNumberPage(ArrayList<Buyable>buyables,Buyable b) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("setNumberPage.fxml"));
        Parent root = loader.load();


        setNumberPageController ctrl = loader.getController();

        ctrl.setBuyable(b);
        ctrl.setBuyables(buyables);
        ctrl.setMain(this);


        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showBalancePage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("balanceCheck.fxml"));
        Parent root = loader.load();


        balanceCheckController ctrl = loader.getController();
        ctrl.setMain(this);
        ctrl.init();

        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
