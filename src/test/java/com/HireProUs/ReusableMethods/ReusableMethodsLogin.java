package com.HireProUs.ReusableMethods;

import java.io.IOException;

import org.zaproxy.clientapi.core.ClientApiException;

import com.HireProUs.Engine.BaseClass;
import com.HireProUs.Engine.CommonMethod;

public class ReusableMethodsLogin extends BaseClass {

	public void Login() throws IOException, InterruptedException {
		testlog.info("Given User navigate to HireProUs Dashboard page");
		String Username = data.getCellData("Login", "UserName", 2);
		String Password = data.getCellData("Login", "Password", 2);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.sendKeys("Username", Username);
		System.out.println(Username);
		CommonMethod.findElementWithRelative("LoginButton", "Password", "above").sendKeys(Password);
		testlog.info("When User enters username and password");
		testlog.info("Sending Username: " + Username);
		testlog.info("Sending Password: " + Password);
		Thread.sleep(1000);
		CommonMethod.scrolldowntoElement("LoginButton");
		CommonMethod.RobustclickElementVisible("LoginButton", "HumanResourceTab");
		testlog.info("And User clicks on Sign IN button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.pass("Verfies Login Successful");
	}

	public void CommonLogin() throws IOException, InterruptedException, ClientApiException {
		
		String Username = data.getCellData("Login", "UserName", 4);
		String Password = data.getCellData("Login", "Password", 4);
		testlog.info("Given User navigate to HireProUs Dashboard page");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("Username", 0);
		CommonMethod.scrolldowntoElement("Username");
		CommonMethod.sendKeys("Username", Username);
		CommonMethod.findElementWithRelative("LoginButton", "Password", "above").sendKeys(Password);
		testlog.info("When User enters username and password");
		testlog.info("Sending Username " + Username);
		testlog.info("Sending Password " + Password);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("LoginButton", 0);
		CommonMethod.RobustclickElementVisible("LoginButton", "HumanResourceTab");
		testlog.info("And User clicks on Sign IN button");
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan("HumanResourceTab", 0);
		testlog.info("Then User will be redirected to Dashboard page");
		testlog.pass("Verfies Login Successful");

	}

}
