package com.rproutil;
import java.util.ArrayList;

import net.schmizz.sshj.SSHClient;

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
		SSHClient ssh = s.sshToRemoteServer();
		assertNotSame("failed", s.getDirNamesFromRemoteServer(ssh));
	}
	
	public void testParseOutAccounts() {
		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		SSHClient ssh = s.sshToRemoteServer();
		String list = s.getDirNamesFromRemoteServer(ssh);
		assertEquals("anonymous", s.parseOutAccounts(list).get(0));
	}
	
	public void testGetAccounts() {
		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		String aName = s.getAccounts().get(0).getName();
		s.closeSshRemoteServer();
		assertEquals("anonymous", aName);
	}
	
//	public void testGetResources() {
//		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
//		RPAccount a = s.getAccounts().get(0);
//		String str = a.getJarNamesFromRemoteServer(s.serverConnection);
//		ArrayList<String> strList = a.parseOutJars(str);
//		assertEquals("ABCCLIODLTH", strList.get(0));
//	}
	
	
	
	 public static void main(String[] args) {
	        junit.textui.TestRunner.run(TestRPServer.class);
	    }

}
