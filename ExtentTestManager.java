package com.Hireprous.Engine.listeners;

import java.util.HashMap;
import java.util.Map;

import com.Hireprous.Engine.BaseClass;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager extends BaseClass {
	// public static ExtentTest testlog;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	// static ExtentReporter extent = ExtentManager.getReporter();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	
	/*
	 * public static synchronized void endTest() { extent.endTest((ExtentTest)
	 * extentTestMap.get((int) (long) (Thread.currentThread().getId()))); }
	 */
	  
	 
	public static synchronized ExtentTest startTest(String testName) {
		return startTest(testName, "");
	}

	public static synchronized ExtentTest startTest(String testName, String desc) {

		test = extent.createTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

		return test;
	}

}
