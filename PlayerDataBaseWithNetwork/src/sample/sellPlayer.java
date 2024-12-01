package sample;

import java.io.Serializable;

public class sellPlayer implements Serializable {
    Player player;
    double price;
    final int category =1 ;
    sellPlayer(Player player,double price){
        this.player = player;
        this.price = price;
    }

    public Player getPlayer() {
        return player;
    }

    public double getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }
}
