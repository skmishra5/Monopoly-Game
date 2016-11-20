package view;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.ClientCallBackImpl;
import client.MonopolyClient;
import common.ClientCallBack;
import common.Monopoly;


public class MonopolyImpl extends java.rmi.server.UnicastRemoteObject implements Monopoly{

	MonopolyUI monoUI =  new MonopolyUI();
	int m_playernum;
	int m_dVal1;
	int m_dVal2;
	private ClientCallBack callbackObj;
	ArrayList<ClientCallBack> clientList = new ArrayList<ClientCallBack>();
	public MonopolyImpl() throws java.rmi.RemoteException {		
		super();
		
		// TODO Auto-generated constructor stub
	}
	
	public int register(ClientCallBack callbackObj)  //throws RemoteException
	{
	    if (callbackObj == null) return -1;
	    this.callbackObj = (ClientCallBack) callbackObj;
	    clientList.add(callbackObj);
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
	

}
