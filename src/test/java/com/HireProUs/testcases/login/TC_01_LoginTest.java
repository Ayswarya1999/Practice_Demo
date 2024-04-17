package com.HireProUs.testcases.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.HireProUs.Engine.BaseClass;
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
		String curr_login = prop.getProperty("CURRENT_LOGIN");

		String UserName = "superadmin";
		String Password = "h1repr0sadm1n@123";
		String RoleName = "SUPER_ADMIN";
		try {
			System.out.println("curr_login: " + curr_login);
			if (curr_login.equals(HireProUsConstants.REC_MANAGER)) {
				RoleName = data.getCellData("Login", "Role", 2);
				UserName = data.getCellData("Login", "UserName", 2);
				Password = data.getCellData("Login", "Password", 2);
			} else if (curr_login.equals(HireProUsConstants.BU_HEAD)) {
				RoleName = data.getCellData("Login", "Role", 3);
				UserName = data.getCellData("Login", "UserName", 3);
				Password = data.getCellData("Login", "Password", 3);
			} else if (curr_login.equals(HireProUsConstants.INTERVIEWER)) {
				RoleName = data.getCellData("Login", "Role", 4);
				UserName = data.getCellData("Login", "UserName", 4);
				Password = data.getCellData("Login", "Password", 4);
			}

			login.CommonLogin(UserName, Password);
		} catch (Throwable t) {
			System.out.println(t.getLocalizedMessage());
			Error e1 = new Error(t.getMessage());
			e1.setStackTrace(t.getStackTrace());
			throw e1;

		}
	}

	
}
