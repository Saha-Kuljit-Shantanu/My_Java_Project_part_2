package sample;

import util.NetworkUtil;

import java.util.ArrayList;


public class Client {

    private ArrayList<Player> list;

    private Club club;

    private ArrayList<sellPlayer> sellList= new ArrayList<>();

    private ArrayList<Buyable> buyable = new ArrayList<>();

    private NetworkUtil networkUtil;

    private boolean flag = false;

    Main main = new Main();

    Client(String host,int port,Club club) {
        try {
           /* NetworkUtil */networkUtil = new NetworkUtil(host,port);
            networkUtil.write(club);
            this.club = club;
            Object o = networkUtil.read();
            if(o instanceof ArrayList){
                list = (ArrayList<Player>) o;
            }
            new ReadThreadClient(networkUtil,this);
           // System.out.println("a");
           // new WriteThreadClient(networkUtil,list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Player> getPlayers(){
        return list;
    }

    public void setBuyable(ArrayList<Buyable> buyable) {
        this.buyable = buyable;
       // System.out.println( "name :"+ club.getName() +" "+ buyable.size());
    }

    public ArrayList<Buyable> getBuyable(){ return buyable;}

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public NetworkUtil getNetworkUtil(){return networkUtil;}

    public Club getClub() {
        return club;
    }

   /* public void setMain(Main main){this.main = main;}

    public Main getMain() {
        return main;
    }*/

    /* public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 44444;
        Client client = new Client(host,port);
    }*/
}
