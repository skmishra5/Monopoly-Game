package cs414.a5.k.common;

import cs414.a5.k.client.MonopolyClient;
import cs414.a5.k.view.MonopolyUI;

public interface Monopoly extends java.rmi.Remote {
	
	public int register(ClientCallBack callbackObj)
			throws java.rmi.RemoteException;
	public void getMessage(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected)
            throws java.rmi.RemoteException;
	public void getBiddingMessage(int playerNum, int biddingPrice)
			throws java.rmi.RemoteException;
}