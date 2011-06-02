package com.rproutil;

public class RPAccount {
	
	String accountName;
	String accountLocation;
	RPserver accountServer;
	RPSearchXML accountSearchXML;
	RPresources[] accountResources;
	String resourcesLocation;
	String searchXMLocation
	 
	 
	public RPAccount (String name, String location, RPServer server) {
		accountName = name;
		accountLocation = location;
		accountServer = server;
		resourcesLocation = location + "resources/lib" // not sure find out
		 

	}
	 
	private void createResources() {
	//navigate to resourcesLocation
	// probably going to have to share or hand over connection from server to account
	// once at resource location perform an 'ls' capture resources
	// filter and create resource object for each listing
	// put resource object in array
	}
	
	private void createSearchXML() {
	// navigate to SearchXML location.
	// create SearchXML object
		
	}
	 
	 
	
	 
	 
}