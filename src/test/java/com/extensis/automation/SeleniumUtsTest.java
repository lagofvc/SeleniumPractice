package com.extensis.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Valentino on 10/29/15.
 */
public class SeleniumUtsTest {

    private static final String BACKUP_5_0 = "/Users/valentino/ProgTools/uts_backups/5.0_BACKUP/2015_10_28T01_28_40_057Z.tar";
    private static final String BACKUP_5_2 = "/Users/valentino/ProgTools/uts_backups/5.2_BACKUP/2015_08_09T10_00_00_043Z.tar";
    private static final String BACKUP_6_0 = "/Users/valentino/ProgTools/uts_backups/6.0_BACKUP/2015_10_28T17_59_20_555Z.tar";
    private WebAdminController webAdmin;

    @Before
    public void init(){
        webAdmin = new WebAdminController();
        webAdmin.loadWebAdmin();
    }

    @After
    public void tearDown() {
        webAdmin.closeBrowser();
    }

    @Test
    public void testRestore_Supported_Backups()throws Exception {
        webAdmin.login();
        webAdmin.restoreBackup(BACKUP_5_0);
        assertEquals(webAdmin.getTitle(), "Status");

        webAdmin.restoreBackup(BACKUP_5_2);
        assertEquals(webAdmin.getTitle(), "Status");

        webAdmin.restoreBackup(BACKUP_6_0);
        assertEquals(webAdmin.getTitle(), "Status");
    }

}