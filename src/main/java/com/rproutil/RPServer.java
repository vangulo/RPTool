package com.rproutil;

/*
 * RPServer.java
 * Copyright (c) 2001-2010 Innovative Interfaces, Inc. All rights reserved.
 */

import java.io.IOException;
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
	ArrayList<RPAccount> serverAccounts;
	 
	public RPServer (String name, String sURL, String login, String password) {
		serverName = name;
		serverURL = sURL;
		serverLogin = login;
		serverPassword = password;
		//serverAccounts = createAccounts();
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
	 */
	public String getAccountsFromRemoteServer(){
		SSHClient ssh = new SSHClient();
		String s = "failed";
		try {
			
			ssh.addHostKeyVerifier ( 
				    new HostKeyVerifier() { 
				        @Override 
				        public boolean verify ( String arg0, int arg1, PublicKey arg2 ) { 
				            return true;  // don't bother verifying 
				        } 
				    } 
				); 
			
			ssh.connect(serverURL);
			ssh.authPassword(serverLogin, serverPassword);
            final Session session = ssh.startSession();
            try {
            	final Command cmd = session.exec("ls $MUSE_HOME/home");
                s =  cmd.getOutputAsString();;
            } 
            catch(Exception e) {
            	System.out.println(e);
            }
            finally {
                session.close();
            }
			
		} catch(Exception e){
		      System.out.println(e);
		} finally {
			try {
				ssh.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		
		//connect to the sURL some how
		// 
		// enter login and password
		// get $MUSE
		// Navigate to the proper directory where the accounts are listed
		// do an "ls" of the account directory
		// when capturing the accounts into an array filter out the non accounts
		//---
		// for each account in the array create an account object (might be able to combine the last 2 steps)
		// instead of a string in the account create the account object but that in array
		// now you have list of account objects
		//--
		// going  to need something like expect some kind of SSH or net jar
		return s;
	} 
	
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
		//System.out.println(accounts);
		return accounts;
	}
	
}