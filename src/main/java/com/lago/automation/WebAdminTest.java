package com.lago.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by valentino cruz on 10/28/15.
 */
public class WebAdminTest {

    //DEFAULTS
    private static final String WEBADMINURL = "http://localhost:18081";

    //XPATHS - for element location
    private static final String PATHTOUSERNAME = "//input[@name='userName']";
    private static final String PATHTOPSWD = "//input[@name='password']";
    private static final String LOGINBUTTON = "//input[@name='loginButton']";

    private static final String RESTOREBUTTON = "//input[@name='restoreButton']";
    private static final String BACKUPSLINK = "//a[contains(@href,'DatastoreBackupRestorePage')]";
    private static final String DATARESTOREFORM = "//form[@id='datastoreRestoreForm']";
    private static final int RESTORE_WAIT = 60000; //1 minute

    //DATA
    private static final String ADMINISTRATOR = "administrator";
    private static final String PASSWORD = "password";
    private static final String DATA_BACKUP_5_2 = "FAKE_DB.tar";

    public static void main(String arg[]){
        String fileToRestore = arg[0];

        //START the webdriver
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get(WEBADMINURL);

        //LOGIN
        WebElement userNameFld = webDriver.findElement(By.xpath(PATHTOUSERNAME));
        userNameFld.sendKeys(ADMINISTRATOR);
        WebElement password = webDriver.findElement(By.xpath(PATHTOPSWD));
        password.sendKeys(PASSWORD);
        webDriver.findElement(By.xpath(LOGINBUTTON)).click();

        //RESTORE backup
        webDriver.findElement(By.xpath(BACKUPSLINK)).click();
        WebElement backupFile = webDriver.findElement(By.xpath(DATARESTOREFORM));
        backupFile.sendKeys(fileToRestore);
        webDriver.findElement(By.xpath(RESTOREBUTTON)).click();

        try {
            webDriver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            // That's fine.
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
