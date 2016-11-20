package cs414.a5.k.common;

import java.rmi.RemoteException;

public interface ClientCallBack extends java.rmi.Remote{
	public void uniquePlayerID(int uniID, String numPlayers, int timeLeft) 
			throws java.rmi.RemoteException;
	public void updateClients(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected)
			throws java.rmi.RemoteException;
	public void updateBiddingInfo(int playerNum, int biddingPrice)
			throws java.rmi.RemoteException;
}
