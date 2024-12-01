package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class addPlayerController {


    @FXML
    private Button backButton;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Label nameError;
    @FXML
    private Label countryError;
    @FXML
    private Label ageError;
    @FXML
    private Label heightError;

    @FXML
    private Label posError;
    @FXML
    private Label numberError;
    @FXML
    private Label salaryError;
    @FXML
    private ChoiceBox <Integer> agePanel;
    @FXML
    private ChoiceBox positionPanel ;
    @FXML
    private TextField namefield;
    @FXML
    private TextField countryfield;

    @FXML
    private TextField heightfield;


    @FXML
    private TextField numberfield;
    @FXML
    private TextField salaryfield;

    private ObservableList<Integer> ageList = FXCollections.observableArrayList();
    private ObservableList<String> positionList = FXCollections.observableArrayList("Goalkeeper","Midfielder","Forward","Defender");
    private String name;
    private String country;
    private int age;
    private double height;
    private String club;
    private String pos;
    private int number;
    private double salary;

    Main main = new Main();

    public void setMain(Main main) {
        this.main = main;
    }

    public void initialize(){

        for (int i=15;i<=60;i++){
            ageList.add(i);
        }
        agePanel.getItems().addAll(ageList);
        positionPanel.getItems().addAll(positionList);
        MouseTouch.change(addPlayerButton);
        MouseTouch.change(backButton);


    }

    public void addPlayerButtonAction(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String filename = "players.txt";
        File file = new File(filename);
        String line;
        ArrayList<Player> allPlayers = new ArrayList<>();
        Scanner val = new Scanner(file);
        while(val.hasNext())
        {
            line = val.nextLine();
            String[] s= new String[8];
            s= line.split(",");
            Player p = new Player( s[0],s[1],Integer.parseInt(s[2]),Double.parseDouble(s[3]),s[4],s[5],Integer.parseInt(s[6]),Double.parseDouble(s[7]));
            allPlayers.add(p);

        }
        boolean nameExist = false;
        boolean numberExist = false;
        nameError.setText("");
        countryError.setText("");
        ageError.setText("");
        heightError.setText("");
        posError.setText("");
        numberError.setText("");
        salaryError.setText("");
        name = namefield.getText().trim();
        country = countryfield.getText().trim();
        try {
            age = (int) agePanel.getSelectionModel().getSelectedItem();
        }
        catch(NullPointerException e){
            ageError.setText("Select an age");

        }
        try{
            String str = heightfield.getText().trim();
            if( str.equals("") )heightError.setText("Enter the height");
            else {
                height = Double.parseDouble(str);
                if (height == 0) heightError.setText("Height cannot be 0");
            }

        } catch (NumberFormatException e){
            heightError.setText("Height has to be a double value");
        }
        club = main.getClub().getName();
        pos = (String) positionPanel.getSelectionModel().getSelectedItem();


        try {
            String str = numberfield.getText().trim();
            if( str.equals("") )  numberError.setText("Enter the salary");
            else {
                number = Integer.parseInt(str);
                if (number == 0) numberError.setText("Number cannot be 0");

                else {
                    for (Player p : main.players) {
                        if (p.getClubname().equalsIgnoreCase(club)) {
                            if (number == p.getNumber()) {
                                numberExist = true;
                                break;
                            }
                        }
                    }
                }

            }

        }catch (NumberFormatException e){
            numberError.setText("Player number has to be an integer value");
        }
        try{
            String str= salaryfield.getText().trim().replaceAll(",","");
            if( str.equals("") ) salaryError.setText("Enter the salary");
            else {
                salary = Double.parseDouble(str);
                if (salary == 0) salaryError.setText("Salary cannot be 0");
            }
        } catch (NumberFormatException e){
            salaryError.setText("Salary has to be a double value");
        }

        for(Player p: allPlayers)
        {

            if(name.equalsIgnoreCase(p.getName() ))
            {
                nameExist = true;
                break;
            }
        }



        if(nameExist == true) nameError.setText("A player of the same name already exists ");
        if(numberExist == true) numberError.setText("A player of this number already exists in this club ");

        if(name.equals("")) nameError.setText("Enter the name");
        if(country.equals("")) countryError.setText("Enter the country");
        if(pos == null)  posError.setText("Select a position");


        if( nameError.getText().equals("") && countryError.getText().equals("") && ageError.getText().equals("") &&
            heightError.getText().equals("") && posError.getText().equals("") &&
                numberError.getText().equals("") && salaryError.getText().equals("") )
        {
            Player x = new Player(name,country,age,height,club,pos,number,salary);
            allPlayers.add(x);
            main.players.add(x);
            //main.getClient().getPlayers().add(p);
            main.setMadeChange(true);

            FileWriter f = new FileWriter(file,false);
            for(Player p: allPlayers)
            {
                f.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClubname()+","+p.getPos()+","+p.getNumber()+","+p.getSalary()+"\n");
            }
            f.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("View Player Information");
            alert.setHeaderText("Your player has been added");
            alert.setContentText("Do you want to display the player Information now?");

            ButtonType yes = new ButtonType("Yes");
            ButtonType notNow = new ButtonType("Not now");

            alert.getButtonTypes().setAll(yes,notNow,ButtonType.CLOSE);

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == yes) {
                main.showOnePlayerInfoPage(x,14);
            }
            else{
                main.showAddPlayerPage();
            }
          //  new WriteThreadClient(main.getClient().getNetworkUtil(),x);
            main.getClient().getNetworkUtil().write(x);
        }




    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        main.showMenuPage();
    }


}
