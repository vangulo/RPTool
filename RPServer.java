package com.rproutil;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import com.jcraft.jsch.*;

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
		serverAccounts = createAccounts();
	}
	
	public ArrayList<RPAccount> getAccounts() {
		return serverAccounts;
	}
	
	 
	
	private ArrayList<RPAccount> createAccounts() {
		
		ArrayList<RPAccount> accountsArray = null;
		
		try{
			JSch jsch = new JSch();
			Session session=jsch.getSession(serverLogin, serverURL, 22);
			session.setPassword(serverPassword);
			session.connect();
		    Channel channel=session.openChannel("exec");
		    ((ChannelExec)channel).setCommand(command);
		      // get I/O streams for remote scp
		    OutputStream out=channel.getOutputStream();
		    InputStream in=channel.getInputStream();
		    
		    channel.connect();
		}
		catch(Exception e){
		      System.out.println(e);
		}
	
		
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
		return accountsArray;
	}
	 
	 
	
	 
	 
}