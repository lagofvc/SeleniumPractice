package com.lago.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by valentino cruz on 10/28/15.
 */
public class SeleniumPractice {

    //DEFAULTS
    private static final String WEBADMINURL = "http://localhost:18081";

    //ELEMENT NAMES, XPATHS - for element location
    private static final String USERNAME = "userName";
    private static final String PATHTOPSWD = "password";
    private static final String LOGINBUTTON = "loginButton";
    private static final String RESTOREBUTTON = "restoreButton";
    private static final String DATARESTOREPAGE = "DatastoreBackupRestorePage";
    private static final String RESTOREPATH = "restorePath";

    private static final String XP_BACKUPSLINK = "//a[contains(@href,'" + DATARESTOREPAGE + "')]";

    //DATA
    private static final String ADMINISTRATOR = "administrator";
    private static final String PASSWORD = "password";
    private static final int RESTORE_WAIT = 60000; //1 minute

    public static void main(String arg[]){
        String fileToRestore = arg[0];

        //START the webdriver
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get(WEBADMINURL);

        //LOGIN
        webDriver.findElement(By.name(USERNAME)).sendKeys(ADMINISTRATOR);
        webDriver.findElement(By.name(PATHTOPSWD)).sendKeys(PASSWORD);
        webDriver.findElement(By.name(LOGINBUTTON)).click();

        //RESTORE backup
        webDriver.findElement(By.xpath(XP_BACKUPSLINK)).click();
        webDriver.findElement(By.name(RESTOREPATH)).sendKeys(fileToRestore);
        webDriver.findElement(By.name(RESTOREBUTTON)).click();
        try {
            webDriver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // no op
        }

        try{
            Thread.sleep(RESTORE_WAIT);
        }catch(Exception e){
            e.printStackTrace();
        }

        //CLOSE the webdriver
        webDriver.close();
    }
}