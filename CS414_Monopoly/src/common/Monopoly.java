package common;

import client.MonopolyClient;
import view.MonopolyUI;

public interface Monopoly extends java.rmi.Remote {
	
	public int register(ClientCallBack callbackObj)
			throws java.rmi.RemoteException;
	public void getMessage(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected)
            throws java.rmi.RemoteException;
}