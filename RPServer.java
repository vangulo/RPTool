package com.rproutil;

public class RPServer {
	
	
	String serverName;
	String serverURL;
	String serverLogin;
	String serverPassword;
	RPAccounts[] serverAccounts;
	 
	public RPServer (String name, String sURL, String login, String password) {
		serverName = name;
		serverURL = sURL;
		serverLogin = login;
		serverPassword = password; 
	}
	
	public Arraylist getAccounts() {
		return serverAccounts;
	}
	
	 
	
	 private void createAccounts() {
	 	//connect to the sURL some how
		// 
		// enter login and password
		// get $MUSE
		// navagait to the proper directory where the accounts are listed
		// do an 'ls' of the account directory
		// when captureing the accounts into an array filter out the non accounts
		//---
		// for each account in the array create an account object (might be able to combine the last 2 steps)
		// instead of a string in the account create the account object but that in array
		// now you have list of account objects
		//--
		// gonna need something like expect some kind of ssh or net jar
	 }
	 
	 
	
	 
	 
}