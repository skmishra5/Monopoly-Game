package common;

public interface ClientCallBack extends java.rmi.Remote{
	public void updateClients(int playerNum, int dVal1, int dVal2, String propertyAction, int rollCount, int tempCardSelected)
			throws java.rmi.RemoteException;
	
}
