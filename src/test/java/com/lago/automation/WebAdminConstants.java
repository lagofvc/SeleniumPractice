package com.lago.automation;

/**
 * Created by valentino on 10/29/15.
 */
public class WebAdminConstants {

    //DEFAULTS
    public static final String WEBADMINURL = "http://localhost:18081";

    //ELEMENT NAMES, XPATHS, TITLES - for element location and UI navigation
    public static final String USERNAME = "userName";
    public static final String PATHTOPSWD = "password";
    public static final String LOGINBUTTON = "loginButton";
    public static final String RESTOREBUTTON = "restoreButton";
    public static final String DATARESTOREPAGE = "DatastoreBackupRestorePage";
    public static final String RESTOREPATH = "restorePath";
    public static final String STATUS = "Status";
    public static final String BACKUPS = "Backups";
    public static final String XP_BACKUPSLINK = "//a[contains(@href,'" + DATARESTOREPAGE + "')]";

    //DATA
    public static final String ADMINISTRATOR = "administrator";
    public static final String PASSWORD = "password";
}

