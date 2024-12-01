package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MouseTouch {
    private static final String Current_Button = "-fx-background-color: #47c1c9;";
    private static final String Touch_Button = "-fx-background-color: #1969e8 ;-fx-text-fill: #ffffff ;";
    public static void change(Button b){
        b.setOnMouseEntered(e -> b.setStyle(Touch_Button));
        b.setOnMouseExited(e -> b.setStyle(Current_Button));
    }


}
