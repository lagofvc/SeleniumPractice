package com.lago.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by lago on 9/9/15.
 */
public class SeleniumPractice {

    public static void main(String arg[]){
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("http://www.facebook.com");
        System.out.println(webDriver.getTitle());
    }
}
