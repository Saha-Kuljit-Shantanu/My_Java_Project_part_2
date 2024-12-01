package sample;

import java.io.Serializable;

public class Buyable2 implements Serializable {
    Player player;
    double price;
    int num;
    final int category =3 ;
    Buyable2(Player player,double price,int num){
        this.player = player;
        this.price = price;
        this.num = num;
    }


    public Player getPlayer() {
        return player;
    }

    public double getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public int getCategory() {
        return category;
    }




}

