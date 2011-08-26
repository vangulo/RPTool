package com.rproutil;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Session.Command;
import org.apache.commons.lang.StringUtils;
import com.rproutil.helpers.*;

public class RPAccount {
	
	String accountName;
	String accountLocation;
	RPServer accountServer;
	RPSearchXML accountSearchXML;
	ArrayList<RPResource> accountResources;
	String resourcesLocation;
	String searchXMLLocation;
	 
	 
	public RPAccount (String name, RPServer server) {
		accountName = name;
		accountLocation = server.getWorkingDir() + name + "/";
		resourcesLocation = accountLocation + "sources/lib/";
		accountServer = server;
		accountResources = createResources();
		searchXMLLocation = accountLocation + "/profiles";
	}
	
	public String getName(){
		return accountName;
	}
	
	public String getResourcesLocation() {
		return resourcesLocation;
	}
	
	public ArrayList<RPResource> getResources() {
		return accountResources;
	}
	
	 
//	private void createResources() {
	//navigate to resourcesLocation
	// probably going to have to share or hand over connection from server to account
	// once at resource location perform an 'ls' capture resources
	// filter and create resource object for each listing
	// put resource object in array
	///iiidb/software/tpp/muse/home/bloom/sources/lib
		
		// ok looks like im going to want to pass the session or channel or
		// whatever to this class to i can do more ssh executions
		
//	}
	

	/*
	 * createAccount Objects
	 */
	public ArrayList<RPResource> createResources(){
		String jarNamesString = RPHelper.getListingFromRemoteServer(accountServer.getServerConn(), resourcesLocation);
		ArrayList<String> jarNamesList = RPHelper.parseOutJars(jarNamesString);
	
		ArrayList<RPResource> resources = new ArrayList<RPResource>();
		
		for (String jar:jarNamesList) {
			String choppedJar = StringUtils.removeEnd(jar, ".jar" );
			RPResource rpresource = new RPResource (choppedJar, this);
			resources.add(rpresource);
		}
		return resources;
	}
	
	private void createSearchXML() {
	// navigate to SearchXML location.
	// create SearchXML object
		
	}
	 
	 
	
	 
	 
}