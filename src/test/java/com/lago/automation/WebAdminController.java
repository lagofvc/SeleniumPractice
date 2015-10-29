package com.lago.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Main controller for executing tests against the WebAdmin via
 * Selenium WebDriver.
 *
 * Created by valentino cruz on 10/28/15.
 */
public class WebAdminController {

    public static final int DEFAULT_TIMEOUT_IN_MILLIS = 72000;
    public static final int DEFAULT_WAIT_INTERVAL = 5000;
    private WebDriver webDriver;

    /**
     * starts the webdriver (firefox) using default webAdmin URL
     * localhost:18081
     * @return
     */
    public WebDriver loadWebAdmin() {
        return loadWebAdmin(WebAdminConstants.WEBADMINURL);
    }

    public WebDriver loadWebAdmin(String url) {
        webDriver = new FirefoxDriver();
        webDriver.get(url);
        return webDriver;
    }

    public void login() {
        webDriver.findElement(By.name(WebAdminConstants.USERNAME)).sendKeys(WebAdminConstants.ADMINISTRATOR);
        webDriver.findElement(By.name(WebAdminConstants.PATHTOPSWD)).sendKeys(WebAdminConstants.PASSWORD);
        webDriver.findElement(By.name(WebAdminConstants.LOGINBUTTON)).click();
    }

    public void restoreBackup(String fileToRestore) throws Exception {
        webDriver.findElement(By.xpath(WebAdminConstants.XP_BACKUPSLINK)).click();
        webDriver.findElement(By.name(WebAdminConstants.RESTOREPATH)).sendKeys(fileToRestore);
        webDriver.findElement(By.name(WebAdminConstants.RESTOREBUTTON)).click();
        try {
            webDriver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // no op
        }
        waitUntilTitleChange(WebAdminConstants.BACKUPS);
    }

    public String getTitle(){
        return webDriver.getTitle();
    }

    public void closeBrowser(){
        webDriver.close();
    }

    //-------------------- HELPER (private) METHODS --------------------//
    /**
     * Waits for a maximum of 20 minutes (default UTS timeout)
     * until the page title changes.   Checks every 5 seconds.
     * @param originalTitle
     */
    private void waitUntilTitleChange(String originalTitle) throws Exception {
        boolean titleUnchanged = true;
        boolean timeOutPending = true;
        long beginTime = System.currentTimeMillis();
        while (titleUnchanged && timeOutPending){

            //sleep 5 seconds
            Thread.sleep(DEFAULT_WAIT_INTERVAL);

            long currentTime = System.currentTimeMillis();
            timeOutPending = (currentTime - beginTime) > DEFAULT_TIMEOUT_IN_MILLIS;
            titleUnchanged = originalTitle.equals(getTitle());
        }
    }
}