package sample;

import javafx.application.Platform;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;

public class ReadThreadClient implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private ArrayList<sellPlayer>list ;
    private ArrayList<Buyable> buyable= new ArrayList<>();
    private Client client;


    public ReadThreadClient(NetworkUtil networkUtil, Client client) {
        this.networkUtil = networkUtil;
        this.client = client;
        this.thr = new Thread(this);
        thr.start();

    }

    @Override
    public void run() {
        try{
            while(true){
                Object o = networkUtil.read();
              /*  if(o instanceof ArrayList){
                    list.clear();
                    list = (ArrayList<sellPlayer>) o;
                    System.out.println(list.size());
                }*/
               /* if(o instanceof Boolean){
                    if((Boolean)o == true){
                        networkUtil.write(client.getClub());
                        System.out.println("Received by "+ client.getClub().getName() );
                    }
                }*/
                if(o instanceof sellPlayer){
                    sellPlayer sp = (sellPlayer) o;
                    buyable.add(new Buyable(sp.getPlayer(), sp.getPrice()));
                    client.setBuyable(buyable);
                  //  client.getMain().showBuyPlayerPage(buyable);

                }
                if(o instanceof ArrayList){
                    buyable.clear();
                    buyable= (ArrayList<Buyable>) o;
                    client.setBuyable(buyable);
                    for(Buyable b: buyable){
                        System.out.println(b);
                    }
                   // client.setFlag(true);
                   // System.out.println(buyable.size());
                }
                if(o instanceof Buyable){
                    Buyable b = (Buyable) o;
                    Buyable temp = null;
                    for(Buyable p: buyable){
                        if(b.getPlayer().getName().equalsIgnoreCase(p.getPlayer().getName())){
                            temp = p;
                        }
                    }
                    buyable.remove(temp);
                    client.setBuyable(buyable);
                  //  client.getMain().showBuyPlayerPage(buyable);
                }
                if(o instanceof String){
                    String str = (String) o;
                    if(str.equals("stop")){
                       // networkUtil.write(client.getClub());
                        System.exit(1);
                    }
                }
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
