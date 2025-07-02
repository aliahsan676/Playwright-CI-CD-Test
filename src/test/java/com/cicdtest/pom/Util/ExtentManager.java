package com.cicdtest.pom.Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        if (extentReports == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/test-logs/extent-report.html");
            sparkReporter.config().setReportName("VS Tools Automation Report");
            sparkReporter.config().setDocumentTitle("Test Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
        }
    }

    public static ExtentTest startTest(String testName) {
        ExtentTest extentTest = extentReports.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

}
