package com.HireProUs.testcases.Process;

import java.io.IOException;
import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
import org.testng.annotations.Parameters;
public class TC_11_NegativeErrorMessageSoftAssertTest extends BaseClass {

	@Test()
	public void TC_11_NegativeErrorMessageSoftAssert() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"HireProUs Login Functionality" );
		try {
			
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
	@Test(dependsOnMethods = { "com.HireProUs.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName","rowNum"})
	public void TC_02_01_JobRequestSearchFilter(String SheetName,int rowNum ) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName,"HireProUs Login Functionality" );
		try {
			rc.JobRequestSearchFilter(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
