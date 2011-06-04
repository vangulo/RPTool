package com.rproutil;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;

public class RPAccount {
	
	String accountName;
	String accountLocation;
	RPServer accountServer;
	RPSearchXML accountSearchXML;
	RPResource[] accountResources;
	String resourcesLocation;
	String searchXMLocation;
	 
	 
	public RPAccount (String name, RPServer server) {
		accountName = name;
		accountLocation = server.getWorkingDir() + name + "/";
		accountServer = server;
	}
	
	public String getName(){
		return accountName;
	}
	 
	private void createResources() {
	//navigate to resourcesLocation
	// probably going to have to share or hand over connection from server to account
	// once at resource location perform an 'ls' capture resources
	// filter and create resource object for each listing
	// put resource object in array
	///iiidb/software/tpp/muse/home/bloom/sources/lib
		
		// ok looks like im going to want to pass the session or channel or
		// whatever to this class to i can do more ssh executions
		
	}
	
	/*
	 * getDirsFromRemoteServer
	 * /iiidb/software/tpp/muse/home/bloom/sources/lib
	 */
	public String getJarNamesFromRemoteServer(SSHClient ssh){
		String s = "failed";
		Session session;
        try {
        	session = ssh.startSession();
        	final Command cmd = session.exec("ls " + accountLocation + "sources/lib");
            s =  cmd.getOutputAsString();
            session.close();
            //ultimate disconnection occurs once all info is retrieved
        } 
        catch(Exception e) {
        	System.out.println(e);
        }
		return s;
	}
	
	public ArrayList<String> parseOutJars(String s) {
		
		ArrayList<String> jars = new ArrayList<String>();
		String[] listings = s.split("\n");
		
		for (String listing:listings) {
			if (!listing.endsWith("\\d") && !listing.startsWith(".") && 
					!listing.endsWith(".jar")){
				jars.add(listing);
			}
		}
		System.out.println(listings);
		return jars;
	}
	
	private void createSearchXML() {
	// navigate to SearchXML location.
	// create SearchXML object
		
	}
	 
	 
	
	 
	 
}