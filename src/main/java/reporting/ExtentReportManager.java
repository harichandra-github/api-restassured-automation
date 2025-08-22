package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utils.CommonFunctions;

public class ExtentReportManager {


    private static ExtentReports extent;
    private static final String timestamp= CommonFunctions.getTimeStamp();
    private static final String reportName =  timestamp +"_API_Automation_Report"+ ".html";
    private static final String reportFilePath = System.getProperty("user.dir") + "/reports/" +reportName;


    private static ExtentReports createInstance(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle(reportName);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName("API Automation Test Results");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            return createInstance();
        }
        return extent;
    }







}
