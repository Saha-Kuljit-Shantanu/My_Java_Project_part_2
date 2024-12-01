package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.NetworkUtil;
/*import util.NetworkUtil;*/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginPageController {


    @FXML
    private Button loginButton;
    @FXML
    private Label invalidLogin;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    private String name;
    private String pass;
    private boolean isConnected = false;
    private boolean isAlreadyConnected;

    Main main = new Main();
    public void setMain(Main main){
        this.main = main;
    }

    public void initialize(){
        MouseTouch.change(loginButton);
    }

    public void loginAction(ActionEvent actionEvent) throws IOException {

        name = userName.getText();
        pass = password.getText();
        main.con = false;
        main.clubs.clear();
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

        }
        for(Club c: main.clubs){
            if (pass.equals(c.getPassword()) && name.equalsIgnoreCase(c.getName()) && c.getIsAlreadyConnected()== false) {
                isConnected = true;
                c.setIsAlreadyConnected(true);
                Client client = new Client("127.0.0.1",44444,c);
                System.out.println("Connected: "+c.getName());
                main.players = client.getPlayers();
                main.setClub(c);
                main.setClient(client);
              //  client.setMain(main);
                FileWriter f = new FileWriter(main.clubfile,false);
                for(Club x: main.clubs)
                {
                    f.write(x.getName()+","+x.getPassword()+","+x.getBalance()+","+x.getIsAlreadyConnected()+"\n");
                }
                main.getClient().getNetworkUtil().write(c);
                f.close();
                main.showMenuPage();
                break;
            }
            if (pass.equals(c.getPassword()) && name.equalsIgnoreCase(c.getName()) && c.getIsAlreadyConnected()== true) {
                isConnected = true;
                invalidLogin.setText("You are already connected");
            }
        }



        if(isConnected == false) invalidLogin.setText("Invalid login");
    }


    public void nameTypeAction(KeyEvent keyEvent) {
        invalidLogin.setText("");
    }

    public void passTypeAction(KeyEvent keyEvent) {
        invalidLogin.setText("");
    }
}
