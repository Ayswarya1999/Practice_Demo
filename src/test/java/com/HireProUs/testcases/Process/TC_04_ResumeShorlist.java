package com.HireProUs.testcases.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.ReusableMethods.HireProUsConstants;

import org.testng.annotations.Parameters;

public class TC_04_ResumeShorlist extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.login.TC_01_LoginTest.TC_01_Login" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_04_00_ResumeShortlistTest(String SheetName, int rowNum) throws IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Environment.properties");
		prop.load(file);
		String curr_flow = prop.getProperty("CURRENT_FLOW");

		if (curr_flow.equals(HireProUsConstants.FLOW1)) {
			TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
			StartTest(TestCaseName, "HireProUs Login Functionality");
			try {
				rc.ResumeShortlist(SheetName, rowNum);
			} catch (Throwable t) {
				System.out.println(t.getLocalizedMessage());
				Error e1 = new Error(t.getMessage());
				e1.setStackTrace(t.getStackTrace());
				throw e1;
			}
		}
	}
}
