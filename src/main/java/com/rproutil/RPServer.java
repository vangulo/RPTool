package com.rproutil;

/*
 * RPServer.java
 * Copyright (c) 2001-2010 Innovative Interfaces, Inc. All rights reserved.
 */

import java.security.PublicKey;
import java.util.*;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;
import org.apache.commons.lang.StringUtils;

/*
 * RPServer Class
 */

public class RPServer {
	String serverName;
	String serverURL;
	String serverLogin;
	String serverPassword;
	SSHClient serverConnection;
	ArrayList<RPAccount> serverAccounts;
	
	String workingDir = "$MUSE_HOME/home/";
	
	//constructors
	public RPServer (String name, String sURL, String login, String password){
		serverName = name;
		serverURL = sURL;
		serverLogin = login;
		System.out.println(workingDir);
		serverConnection = sshToRemoteServer();
		serverAccounts = createAccounts();
	}
	
	//public methods
	
	public String getWorkingDir() {
		return workingDir;
	}
	
	public ArrayList<RPAccount> getAccounts() {
		return serverAccounts;
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
	
	/*
	 * sshToRemoteServer
	 */
	public SSHClient sshToRemoteServer(){
		SSHClient ssh = new SSHClient();
		try {
			// For RSA Key Host verificaiton
			ssh.addHostKeyVerifier ( new HostKeyVerifier() { 
				        				@Override 
				        				public boolean verify ( String arg0, int arg1, PublicKey arg2 ) { 
				        					return true;  // don't bother verifying 
				        				} 
				    				} 
									); 
			ssh.connect(serverURL);
			ssh.authPassword(serverLogin, serverPassword);
			
		} catch(Exception e){
			System.out.println(e);
		    //in the end you want an error to appear with the user interface.
		}
		return ssh;
	}
	
	/*
	 * getDirsFromRemoteServer
	 */
	public String getDirNamesFromRemoteServer(SSHClient ssh){
		String s = "failed";
		Session session;
        try {
        	session = ssh.startSession();
        	final Command cmd = session.exec("ls " + workingDir);
            s =  cmd.getOutputAsString();
            session.close();
            //ultimate disconnection occurs once all info is retrieved
            
            ssh.disconnect();
            
        } 
        catch(Exception e) {
        	System.out.println(e);
        }
		return s;
	}
	
	/*
	 * parseOutAccounts
	 */
	
	public ArrayList<String> parseOutAccounts(String s) {
		
		ArrayList<String> accounts = new ArrayList<String>();
		String[] listings = s.split("\n");
		
		for (String listing:listings) {
			if (listing.endsWith("/") && !listing.startsWith(".") && 
					!listing.contains("rpstats")){
				String choppedListing = StringUtils.chop(listing);
				accounts.add(choppedListing);
			}
		}
		System.out.println(accounts);
		return accounts;
	}
	
	/*
	 * createAccount Objects
	 */
	public ArrayList<RPAccount> createAccounts(){
		String accountNamesString = getDirNamesFromRemoteServer(serverConnection);
		ArrayList<String> accountNamesList = parseOutAccounts(accountNamesString);
		
		ArrayList<RPAccount> accounts = new ArrayList<RPAccount>();
		
		for (String account:accountNamesList) {
			RPAccount rpaccount = new RPAccount (account, this);
			accounts.add(rpaccount);
		}
		return accounts;
	}
	
	
	public void closeSshRemoteServer(){
		try {
			serverConnection.disconnect();
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	
}