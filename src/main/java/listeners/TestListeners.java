package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
    private final Logger logger= LogManager.getLogger(TestListeners.class);



    public void onStart(ITestContext context) {
        logger.info("Test execution started: " + context.getName());
    }


    public void onFinish(ITestContext context) {
        logger.info("Test execution finished: " + context.getName());
        if (context.getFailedTests().size() > 0) {
            logger.error("There were test failures in the context: " + context.getName());
            context.getFailedTests().getAllResults().forEach(result -> {
                logger.error("Failed test: " + result.getName() + " - " + result.getThrowable());
            });
        } else {
            logger.info("All tests passed in the context: " + context.getName());
        }
    }

   public void onTestStart(ITestResult result) {
        logger.info("Test started: " + result.getName() + " in class: " + result.getTestClass().getName());
    }
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getName() + " in class: " + result.getTestClass().getName());
    }
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getName() + " in class: " + result.getTestClass().getName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getName() + " in class: " + result.getTestClass().getName());
    }

}
