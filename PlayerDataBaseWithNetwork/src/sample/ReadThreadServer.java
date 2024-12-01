package sample;

import util.NetworkUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadThreadServer implements Runnable {
        private Thread thr;
        private NetworkUtil networkUtil;
        public HashMap<Club, NetworkUtil> clubMap;
        private Club club;
        public ArrayList<sellPlayer>sellplayers = new ArrayList<>();
        public ArrayList<Buyable> buyable = new ArrayList<>();
        public ArrayList<Player>allPlayers = new ArrayList<>();
        public String filename = "players.txt";
        public File file = new File(filename);

        public ReadThreadServer(HashMap<Club, NetworkUtil> map, NetworkUtil networkUtil,Club club, ArrayList<sellPlayer>sellplayers,ArrayList<Player>allPlayers,ArrayList<Buyable>buyable) {
            this.clubMap = map;
            this.networkUtil = networkUtil;
            this.club = club;
            this.sellplayers = sellplayers;
            this.allPlayers = allPlayers;
            this.buyable = buyable;
            this.thr = new Thread(this);
            thr.start();

        }

        public void run() {
            try {
                while (true) {
                    Object o = networkUtil.read();
                    if(o instanceof Player){
                        allPlayers.add((Player) o);
                    }
                    if(o instanceof sellPlayer){

                        sellPlayer sp = (sellPlayer) o;
                        sellplayers.add(sp);
                      /*  for(Map.Entry<Club,NetworkUtil> map : clubMap.entrySet() ){
                            map.getValue().write(sellplayers);
                        }*/
                      /*  for(sellPlayer x : sellplayers ){
                            System.out.println(x.getPlayer().getName()+" "+ x.getPrice());
                        }*/
                       for(Map.Entry<Club,NetworkUtil> map : clubMap.entrySet() ){
                            if(!(map.getKey().getName().equalsIgnoreCase(sp.getPlayer().getClubname() ) ) ){
                              // buyable.add(new Buyable(sp.getPlayer(), sp.getPrice()));
                                map.getValue().write(sp);
                            }
                        }
                        Player j = new Player();
                        for(Player x : allPlayers ){
                            if(x.getName().equals(sp.getPlayer().getName())) {
                               // System.out.println(x.getName());
                                x.setClubname("No club");
                               // j=x;
                            }
                        }
                       // j.setClubname("No club");
                       // allPlayers.remove(j);
                        FileWriter f = new FileWriter(file,false);
                        for(Player p: allPlayers)
                        {
                            //System.out.println(p.getName());
                            //p.showInfo();
                            f.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClubname()+","+p.getPos()+","+p.getNumber()+","+p.getSalary()+"\n");
                        }
                        f.close();




                    }
                    if(o instanceof Club){
                        buyable.clear();
                        String str = ((Club)o).getName();
                        System.out.println(str);
                        for(sellPlayer p: sellplayers){
                            System.out.println(p.getPlayer().getName()+"    "+p.getPlayer().getClubname());
                            if(!str.equalsIgnoreCase(p.getPlayer().getClubname()))
                                buyable.add(new Buyable(p.getPlayer(),p.getPrice()));
                        }
                        //NetworkUtil n = clubMap.get((Club)o);
                        for(Map.Entry<Club,NetworkUtil> map : clubMap.entrySet() ){
                            if(((Club) o).getName().equals(map.getKey().getName())) {
                                map.getValue().write(buyable);
                            }
                        }
                        if(str.equalsIgnoreCase(club.getName())) networkUtil.write(buyable);
                       // n.write(buyable);
                    }

                    if(o instanceof Buyable) {
                        Buyable b = (Buyable) o;
                        sellPlayer sp = new sellPlayer(b.getPlayer(), b.getPrice());
                        System.out.println(sp.getPlayer().getNumber());
                        //System.out.println((Buyable) o);
                        sellPlayer s = null;
                        for (sellPlayer x : sellplayers) {
                            if (sp.getPlayer().getName().equals(x.getPlayer().getName())) {
                                s = x;
                            }
                        }
                        sellplayers.remove(s);

                        for(Map.Entry<Club,NetworkUtil> map : clubMap.entrySet() ){
                                map.getValue().write(b);
                        }
                    }
                    if(o instanceof Buyable2){
                        sellPlayer sp = new sellPlayer(((Buyable2) o).getPlayer(), ((Buyable2) o).getPrice());
                        ((Buyable2)o).getPlayer().showInfo();
                        sp.getPlayer().showInfo();
                        for(Player x : allPlayers ){

                            if(x.getName().equals(sp.getPlayer().getName())) {
                                x.setClubname(sp.getPlayer().getClubname());
                                x.setNumber(((Buyable2) o).getNum());
                                x.showInfo();
                            }
                        }
                        FileWriter f = new FileWriter(file,false);
                        for(Player p: allPlayers)
                        {
                            f.write(p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClubname()+","+p.getPos()+","+p.getNumber()+","+p.getSalary()+"\n");
                        }
                        f.close();
                        System.out.println("Done with writing file");

                    }
                    if(o instanceof String){
                        String str = (String) o;
                        if(o.equals("stop")){
                            networkUtil.write("stop");
                            clubMap.remove(club);
                            System.out.println("Removing: "+ club.getName());

                        }
                    }

                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
}
