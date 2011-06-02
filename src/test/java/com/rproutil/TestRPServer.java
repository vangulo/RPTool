package com.rproutil;


import junit.framework.TestCase;


public class TestRPServer extends TestCase{
	
	String sName = "researchpro1";
	String sURL = "researchpro1.iii.com";
	String sLogin = "iii";
	String sPassword = "40916res";

	
	
	public TestRPServer(String name){
		super(name);
	}
	
	public void testCreateAccounts() {
		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		assertNotSame("failed", s.createAccounts());
	}
	
	 public static void main(String[] args) {
	        junit.textui.TestRunner.run(TestRPServer.class);
	    }

}
