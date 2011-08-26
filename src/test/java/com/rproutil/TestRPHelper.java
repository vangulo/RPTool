package com.rproutil;


import com.rproutil.helpers.RPHelper;

import net.schmizz.sshj.SSHClient;

import junit.framework.TestCase;

import com.rproutil.helpers.*;


public class TestRPHelper extends TestCase{
	
	String sURL = "researchpro18.iii.com";
	String sLogin = "iii";
	String sPassword = "99316res";
	String workingDir = "$MUSE_HOME/home/";
	
	String accountLocation = workingDir + "utep" + "/";
	String resourcesLocation = accountLocation + "sources/lib/";

//	public TestRPServerHelper(String name){
//		super(name);
//	}
	
	public void testGetAccountsFromRemoteServerAccounts() {
	//	RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		SSHClient ssh = RPHelper.sshToRemoteServer(sURL, sLogin, sPassword);
		assertNotSame("failed",RPHelper.getListingFromRemoteServer(ssh, workingDir));
		RPHelper.closeSshRemoteServer(ssh);
	}
	
	public void testParseOutAccounts() {
		//RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		SSHClient ssh = RPHelper.sshToRemoteServer(sURL, sLogin, sPassword);
		String list = RPHelper.getListingFromRemoteServer(ssh, workingDir);
		RPHelper.closeSshRemoteServer(ssh);
		assertEquals("agric", RPHelper.parseOutAccounts(list).get(0));
		
	}
	
	public void testParseOutJars() {
		//RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
		SSHClient ssh = RPHelper.sshToRemoteServer(sURL, sLogin, sPassword);
		String list = RPHelper.getListingFromRemoteServer(ssh, resourcesLocation);
		System.out.println(list);
		RPHelper.closeSshRemoteServer(ssh);
		assertEquals("ACMDL.jar", RPHelper.parseOutJars(list).get(0));
		
	}
	
	
	
	public void testGetAccounts() {
		RPServer s = new RPServer(sURL, sLogin, sPassword);
		String aName = s.getAccounts().get(0).getName();
		RPHelper.closeSshRemoteServer(s.getServerConn());
		assertEquals("agric", aName);
	}
	
//	public void testGetResources() {
//		RPServer s = new RPServer(sName, sURL, sLogin, sPassword);
//		RPAccount a = s.getAccounts().get(1);
//		RPResource r = a.getResources().get(0);
//		assertEquals("ABCCLIODLTH", r.getName());
//	}
	
	
	
	 public static void main(String[] args) {
	        junit.textui.TestRunner.run(TestRPHelper.class);
	    }

}
