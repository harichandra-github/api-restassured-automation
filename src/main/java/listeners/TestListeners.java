package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentReportManager;

public class TestListeners implements ITestListener {
    private final Logger logger= LogManager.getLogger(TestListeners.class);
    private static final ExtentReports extent= ExtentReportManager.getInstance();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(ITestContext context) {
        logger.info("Test execution started: {}", context.getName());
    }


    public void onFinish(ITestContext context) {
        logger.info("Test execution finished: {}", context.getName());
        if (context.getFailedTests().size() > 0) {
            logger.error("There were test failures in the context: {}", context.getName());
            context.getFailedTests().getAllResults().forEach(result -> {
                logger.error("Failed test: {} - {}", result.getName(), result.getThrowable());
            });
        } else {
            logger.info("All tests passed in the context: " + context.getName());
        }
        extent.flush();
    }

   public void onTestStart(ITestResult result) {
       logger.info("Test started: {} in class: {}", result.getName(), result.getTestClass().getName());
       ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
               result.getMethod().getDescription());
         TestListeners.extentTest.set(extentTest);
         extentTest.info("Test started: "+ result.getMethod().getMethodName());

    }
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {} in class: {}", result.getName(), result.getTestClass().getName());
        extentTest.get().pass("Test passed");
    }
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {} in class: {}", result.getName(), result.getTestClass().getName());
        logger.error("Failure reason: {}", String.valueOf(result.getThrowable()));
        extentTest.get().fail("Test failed: ");
        extentTest.get().fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {} in class: {}", result.getName(), result.getTestClass().getName());
        extentTest.get().skip("Test skipped: " + result.getSkipCausedBy());
    }



    //Helper functions for Custom Logging

    /** Logs an informational message */
    public static void info(String message) {
        if (extentTest.get() != null) {
            extentTest.get().info(message);
        }
    }

    /** Logs a passing step */
    public static void pass(String message) {
        if (extentTest.get() != null) {
            extentTest.get().pass(message);
        }
    }

    /** Logs a failing step */
    public static void fail(String message) {
        if (extentTest.get() != null) {
            extentTest.get().fail(message);
        }
    }

    /** Logs a warning message */
    public static void warn(String message) {
        if (extentTest.get() != null) {
            extentTest.get().warning(message);
        }
    }

}
