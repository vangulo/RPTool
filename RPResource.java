package com.rproutil;

public class RPResource {
	
	String resourceName;
	String resourceLocation;
	RPAccount resourceAccount;
	RPResourceXML resourceXML;
	String resourceVersion;
	String contentsList;

	 
	 
	public RPResource (String name, String location, RPAccount account) {
		resourceName = name;
		resourceLocation = location;
		resourceAccount = account;
		resourceXML = new RPResourceXML(resourceAccount.getLocation());
		resourceVersion = setVersion(resourceLocation);
				 
	}
	
	private void setResourceXML() {
		
	}
	 
	private void setVersion() {

	}
	
	private void setContentsList() {

		
	}
	 
	 
	
	 
	 
}