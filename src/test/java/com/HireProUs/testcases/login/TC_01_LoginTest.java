package com.HireProUs.testcases.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.Engine.CommonMethod;
import com.HireProUs.ReusableMethods.HireProUsConstants;

public class TC_01_LoginTest extends BaseClass {

	@Test
	public void TC_01_Login() throws IOException {

		TestCaseName = Thread.currentThread().getStackTrace()[1].getMethodName();
		StartTest(TestCaseName, "HireProUs Login Functionality");
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Environment.properties");
		prop.load(file);
		
		try {
			login.Login();
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;

		}
	}

	
}
















