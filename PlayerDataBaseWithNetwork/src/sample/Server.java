package sample;

import util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Server {
    private String filename = "players.txt";

    public File file = new File(filename);

    private ArrayList<Player> allPlayers = new ArrayList<>();

    private ArrayList<Player> players = new ArrayList<>();

    private ServerSocket ss ;

    public HashMap<Club, NetworkUtil> clubMap ;

    public ArrayList<sellPlayer> soldPlayers = new ArrayList<>();

    public ArrayList<Buyable> buyable = new ArrayList<>();

    Server(){
        try {
            clubMap = new HashMap<>();

             ss = new ServerSocket(44444);
            while(true){
                Socket cs = ss.accept();

                serve(cs);


            }
        }
        catch (Exception e){
            System.out.println("Server failure");
            //System.out.println(e);
        }
    }

    public void serve(Socket cs) throws IOException, ClassNotFoundException {

        NetworkUtil networkutil = new NetworkUtil(cs);
        allPlayers.clear();
        players.clear();
        Club club = (Club) networkutil.read();
        String line;
        Scanner val = new Scanner(file);
        while(val.hasNext())
        {

            line = val.nextLine();
            String[] s= new String[8];
            s= line.split(",");
            Player p = new Player( s[0],s[1],Integer.parseInt(s[2]),Double.parseDouble(s[3]),s[4],s[5],Integer.parseInt(s[6]),Double.parseDouble(s[7]));
            allPlayers.add(p);

        }
     //   clubMap.put(cname,networkutil);
        // networkutil.write(cname);
        //System.out.println("Connected : " + cname);



        for(Player p: allPlayers){
            if(p.getClubname().equalsIgnoreCase(club.getName()))
                players.add(p);
        }

        networkutil.write(players);

        clubMap.put(club,networkutil);
        new ReadThreadServer(clubMap,networkutil,club,soldPlayers,allPlayers,buyable);
    }
    public static void main(String[] args) {
        Server server = new Server();
    }


}
