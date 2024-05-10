package com.HireProUs.testcases.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.ReusableMethods.HireProUsConstants;

import org.testng.annotations.Parameters;

public class TC_02_JobRequestTest extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName", "rowNum", "ErrorMessage" })
	public void TC_02_00_CreateJobRequest(String SheetName, int rowNum, String ErrorMessage) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "HireProUs Login Functionality");
		try {
			rc.CreateJobRequest(SheetName, rowNum, ErrorMessage);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_02_JobRequestTest.TC_02_00_CreateJobRequest" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_02_01_JobRequestSearchFilter(String SheetName, int rowNum) throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "HireProUs Login Functionality");
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
