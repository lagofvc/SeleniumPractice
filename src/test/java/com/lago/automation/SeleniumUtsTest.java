package com.lago.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

/**
 * Created by lago on 9/9/15.
 */
public class SeleniumUtsTest {

    private WebDriver webDriver;

    @Before
    public void init(){
        webDriver = new FirefoxDriver();
        webDriver.get("http://localhost:18081");
    }

    @After
    public void tearDown() {
        webDriver.close();
    }

    @Test
    public void testUTSWebAdminPage() {
        String title = webDriver.getTitle();
        assertEquals(title, "login");
    }

    @Test
    public void testAdministratorLogin() {
        webDriver.findElement(By.name("userName")).sendKeys("administrator");
        webDriver.findElement(By.name("password")).sendKeys("password");
        webDriver.findElement(By.name("loginButton")).click();
        assertEquals(webDriver.getTitle(), "Status");
    }

    @Test
    public void testRestoreBackup() {
        testAdministratorLogin();
        webDriver.findElement(By.xpath("//a[@href='" + WebAdminConstants.BACKUP_LINK_TOKEN + "']")).click();
        assertEquals(webDriver.getTitle(), "Backups/Restore");
    }

}