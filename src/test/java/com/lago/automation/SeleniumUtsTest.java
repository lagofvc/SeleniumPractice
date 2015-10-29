package com.lago.automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Valentino on 10/29/15.
 */
public class SeleniumUtsTest {

    private static final String BACKUP_5_0 = "/Users/valentino/ProgTools/uts_backups/5.0_BACKUP/2015_10_28T01_28_40_057Z.tar";
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
    public void testRestore_5_0_Backup()throws Exception {
        webAdmin.login();
        webAdmin.restoreBackup(BACKUP_5_0);
        assertEquals(webAdmin.getTitle(), "Status");
    }

}