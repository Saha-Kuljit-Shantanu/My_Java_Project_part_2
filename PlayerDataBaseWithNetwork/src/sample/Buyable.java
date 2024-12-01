package sample;

import java.io.Serializable;

public class Buyable implements Serializable {
    Player player;
    double price;
    final int category =2 ;
    Buyable(Player player,double price){
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

    public String toString(){
        return "Buy "+ player.getName()+ " from club "+ player.getClubname()+ " at price : $"+ price;
    }


}
