package cs414.a5.k.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import cs414.a5.k.client.ClientCallBackImpl;
import cs414.a5.k.client.MonopolyClient;
import cs414.a5.k.common.ClientCallBack;
import cs414.a5.k.common.Monopoly;
import cs414.a5.k.model.Player;


public class MonopolyImpl extends java.rmi.server.UnicastRemoteObject implements Monopoly{

	MonopolyUI monoUI =  new MonopolyUI();
	int m_playernum;
	int m_dVal1;
	int m_dVal2;
	private ClientCallBack callbackObj;
	ArrayList<ClientCallBack> clientList = new ArrayList<ClientCallBack>();
	String m_numPlayers = "";
	int m_uniID = 0;
	private int counter;
	Timer timer;
	int remainingTime;
	
	public MonopolyImpl(String numPlayers, String time) throws java.rmi.RemoteException {		
		super();
		this.m_numPlayers = numPlayers;
		startTimer(time);
		// TODO Auto-generated constructor stub
	}
	
	public void startTimer(String time) {
		counter = Integer.parseInt(time);
		ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				counter--;
				remainingTime = counter / 60;
			}

		};
		timer = new Timer(1000, taskPerformer);
		timer.start();
	}
	
	public int register(ClientCallBack callbackObj)  //throws RemoteException
	{
		m_uniID++;
	    if (callbackObj == null) return -1;
	    this.callbackObj = (ClientCallBack) callbackObj;
	    clientList.add(callbackObj);
	    try {
			callbackObj.uniquePlayerID(m_uniID, m_numPlayers, counter);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return 1;
	}
	
	public void getMessage(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected) throws java.rmi.RemoteException
	{
		System.out.println(playerNum + ":" + dVal1 + ":" + dVal2);
		if (!(callbackObj instanceof ClientCallBack)) 
			System.out.println("ClientObject is not instance of CallbackClientIntf at server side");
		
		for(int i = 0; i < clientList.size(); i++)
		{
			if(i == playerNum - 1)
			{
				System.out.println("Not updating the client from where the message came!");
			}
			else
			{
				clientList.get(i).updateClients(playerNum, dVal1, dVal2, propertyAction, rollCount, tempCardSelected);
			}
		}
	}
	
	public void getBiddingMessage(int playerNum, int biddingPrice) throws java.rmi.RemoteException
	{
		if (!(callbackObj instanceof ClientCallBack)) 
			System.out.println("ClientObject is not instance of CallbackClientIntf at server side");
		
		for(int i = 0; i < clientList.size(); i++)
		{
			if(i == playerNum - 1)
			{
				System.out.println("Not updating the client from where the bidding message came!");
			}
			else
			{
				clientList.get(i).updateBiddingInfo(playerNum, biddingPrice);
			}
		}
	}
	

}
