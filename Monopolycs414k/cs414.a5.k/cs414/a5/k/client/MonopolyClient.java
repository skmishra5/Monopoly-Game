package cs414.a5.k.client;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import cs414.a5.k.common.ClientCallBack;
import cs414.a5.k.common.Monopoly;
import cs414.a5.k.view.MonopolyUI;

public class MonopolyClient implements ClientCallBack,Serializable{
	static Monopoly m;
	static MonopolyUI monoUI;
    public static void main(String[] args) {

           try {
        	   m = (Monopoly) 
                         Naming.lookup("rmi://" + args[0] + ":" + args[1]  + "/MonopolyService");
        	   
        	   ClientCallBack mc = new MonopolyClient();
        	   ClientCallBack mcStub = (ClientCallBack)UnicastRemoteObject.exportObject(mc, 0);
        	   m.register(mc);
        	   //startGame();
        	   
        	   System.out.println("Hello I am a player");
           }

           catch (MalformedURLException murle) {
                  System.out.println("MalformedURLException");
                  System.out.println(murle);
              } catch (RemoteException re) {
                  System.out.println("RemoteException"); 
                  System.out.println(re);
              } catch (NotBoundException nbe) {
                  System.out.println("NotBoundException");
                  System.out.println(nbe);
              } catch (java.lang.ArithmeticException ae) {
                   System.out.println("java.lang.ArithmeticException");
                   System.out.println(ae);
              }
    }
    
    public void uniquePlayerID(int uniID, String numPlayers, int timeLeft) throws RemoteException
    {
    	startGame(uniID, numPlayers, timeLeft);
    }
    
    public static void startGame(int uniID, String numPlayers, int timeLeft) throws RemoteException
    {
    	monoUI = new MonopolyUI(uniID, numPlayers, timeLeft);
 	   	monoUI.setVisible(true);
 	   	//ClientCallBack ccbm = new ClientCallBackImpl();
 	   	//ClientCallBack stub = (ClientCallBack) UnicastRemoteObject.exportObject(ccbm, 0);
 	    //ccbm.updateClients();
 	   
    }
    
    public void sendServerInfo(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected) throws RemoteException
    {
    	m.getMessage(playerNum, dVal1, dVal2, propertyAction, rollCount, tempCardSelected);  	
    }
    
    public void sendBiddingInformation(int playerNum, int biddingPrice) throws RemoteException
    {
    	m.getBiddingMessage(playerNum, biddingPrice);
    }
    
	public void updateClients(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected) 
	{
		monoUI.updateOtherPlayers(playerNum, dVal1, dVal2, propertyAction, rollCount, tempCardSelected);
		//System.out.println("Hello CS414");
	}
	
	public void updateBiddingInfo(int playerNum, int biddingPrice)
	{
		monoUI.updateBidAmount(playerNum, biddingPrice);
	}

}