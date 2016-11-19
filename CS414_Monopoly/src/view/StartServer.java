package view;

import java.rmi.Naming;

import common.Monopoly;

public class StartServer {

	private String url;
    public StartServer(String url) {
    this.url = url;
          try {
        	  Monopoly m = new MonopolyImpl();
        	  Naming.rebind(url, m);
        	  System.out.println("Hello Gamers!");
           	} catch (Exception e) {
                   System.out.println("Trouble: " + e);
           }
    }

//run the program using 
//java CalculatorServer <host> <port>

    public static void main(String args[]) {
    	String url = new String("rmi://" + args[0] + ":" + args[1] + "/MonopolyService");
    	new StartServer(url);
    }

}
