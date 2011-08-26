package com.rproutil;

/*
 * RPServer.java
 * Copyright (c) 2001-2010 Innovative Interfaces, Inc. All rights reserved.
 */


import java.util.*;

import net.schmizz.sshj.SSHClient;
import com.rproutil.helpers.*;

/*
 * RPServer Class
 */

public class RPServer {
	
	String serverURL;
	String serverLogin;
	String serverPassword;
	
	SSHClient serverConnection;
	ArrayList<RPAccount> serverAccounts;
	
	String workingDir = "$MUSE_HOME/home/";
	
	//constructors
	public RPServer (String sURL, String login, String password){
		serverURL = sURL;
		serverLogin = login;
		serverPassword = password;
		
		serverConnection = RPHelper.sshToRemoteServer(sURL, login, password);
		serverAccounts = createAccounts(this);
	}
	
	//public methods
	
	
	public String getWorkingDir() {
		return workingDir;
	}
	
	public ArrayList<RPAccount> getAccounts() {
		return serverAccounts;
	}
	
	public SSHClient getServerConn() {
		return serverConnection;
	}
	
	/*Need more work here...
	 * wrong password
	 * wrong url
	 * wrong login
	 * server down
	 * no permissions to add host key
	 * *** $100,000 question ... is it wise to ping the server so often
	 * when do i need to absolutly ping the server
	 * can i ping it only when i need to make a change 
	 * can i carry the connection between classes
	 * who ULTIMATELY owns the connection
	 */
	
	//review the best way to handles this exception
/////////////////////////////////////////////////////////////////////////////////
	
	
	/*
	 * createAccount Objects
	 */
	public ArrayList<RPAccount> createAccounts(RPServer Server){
		
		SSHClient ssh = Server.getServerConn();
		String workingDir = Server.getWorkingDir();
		String accountNamesString = RPHelper.getListingFromRemoteServer(ssh, workingDir);
		ArrayList<String> accountNamesList = RPHelper.parseOutAccounts(accountNamesString);
		
		ArrayList<RPAccount> accounts = new ArrayList<RPAccount>();
		
		for (String account:accountNamesList) {
			RPAccount rpaccount = new RPAccount (account, Server);
			accounts.add(rpaccount);
		}
		return accounts;
	}
	
	
	
}