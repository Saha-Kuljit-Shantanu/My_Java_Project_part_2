package sample;

import java.io.Serializable;

public class Club implements Serializable {
    private String name;
    private String password;
    private double balance;
    private boolean isAlreadyConnected;

    Club(String name,String password,double balance,boolean isAlreadyConnected){
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.isAlreadyConnected = isAlreadyConnected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPassword(String password){ this.password = password;}

    public void setIsAlreadyConnected(boolean x){isAlreadyConnected = x;}

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAlreadyConnected(){return isAlreadyConnected;}


}
