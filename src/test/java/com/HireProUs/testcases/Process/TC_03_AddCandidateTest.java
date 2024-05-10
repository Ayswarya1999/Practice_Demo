package com.HireProUs.testcases.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.ReusableMethods.HireProUsConstants;

import org.testng.annotations.Parameters;

public class TC_03_AddCandidateTest extends BaseClass {

	@Test(dependsOnMethods = { "com.HireProUs.testcases.Process.TC_02_JobRequestTest.TC_02_00_CreateJobRequest" })
	@Parameters({ "SheetName", "rowNum" })
	public void TC_03_00_AddCandidateTest(String SheetName, int rowNum) throws IOException {
		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "HireProUs Login Functionality");
		try {
			rc.AddCandidateTest(SheetName, rowNum);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;
		}
	}
}
