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
	
	public void testGetAccountsFromRemoteServerAccounts() {
		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		assertNotSame("failed", s.getAccountsFromRemoteServer());
	}
	
	public void testParseOutAccounts() {
		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		String list = s.getAccountsFromRemoteServer();
		assertEquals("anonymous", s.parseOutAccounts(list).get(0));
	}
	
	
	
	 public static void main(String[] args) {
	        junit.textui.TestRunner.run(TestRPServer.class);
	    }

}
