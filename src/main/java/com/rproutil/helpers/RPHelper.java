package com.rproutil.helpers;

import java.security.PublicKey;
import java.util.ArrayList;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;

import org.apache.commons.lang.StringUtils;


public class RPHelper {
	
	
/*
 * sshToRemoteServer
 */
	public static SSHClient sshToRemoteServer(String serverURL, String serverLogin, String serverPassword){
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
 * Close sshToRemoteServer
 */
	public static void closeSshRemoteServer(SSHClient ssh){
		try {
			ssh.disconnect();
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
/*
 * getDirsFromRemoteServer
 * turn this into helper?
 */
	public static String getListingFromRemoteServer(SSHClient ssh, String location){
		String s = "failed";
		Session session;
        try {
        	session = ssh.startSession();
        	final Command cmd = session.exec("ls " + location);
            s =  cmd.getOutputAsString();
            session.close();
            //ultimate disconnection occurs once all info is retrieved
            
           // ssh.disconnect();
            
        } 
        catch(Exception e) {
        	System.out.println(e);
        }
		return s;
	}
	
/*
 * parseOutAccounts
 * turn this into helper
 */
	
	public static ArrayList<String> parseOutAccounts(String s) {
		
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
	
/*
 * parseOutJars
 * turn this into helper
*/
	public static ArrayList<String> parseOutJars(String s) {
		
		ArrayList<String> jars = new ArrayList<String>();
		String[] listings = s.split("\n");
		
		for (String listing:listings) {
			if (!listing.endsWith("\\d") && !listing.startsWith(".") && 
					listing.endsWith(".jar")){
				jars.add(listing);
				//System.out.println(listing);
			}
		}
		return jars;
	}
	


	
	



}
