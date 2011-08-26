package com.rproutil;

public class RPResource {
	
	String resourceName;
	String resourceLocation;
	RPAccount resourceAccount;
	RPResourceXML resourceXML;
	String resourceVersion;
	String contentsList;

	 
	 
	public RPResource (String name, RPAccount account) {
		resourceName = name;
		resourceLocation = account.getResourcesLocation() + "sources/lib";
		resourceAccount = account;
//		resourceXML = new RPResourceXML(resourceAccount.getLocation());
	//	resourceVersion = setVersion(resourceLocation);
				 
	}
	
	public String getName() {
		return resourceName;
	}
	
	private void setResourceXML() {
		
	}
	 
	private void setVersion() {

	}
	
	private void setContentsList() {

		
	}
	 
	 
	
	 
	 
}