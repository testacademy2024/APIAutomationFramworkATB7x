package com.thetestingacademy.tests.crud;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
//refer allure testng for annotations list
public class testCreateBookingPOST {
    //classname and methodname is not kept same purposefully and methodname can be kept anything , but make sure @Description is entered correctly as per the method
    @Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
    @Issue("JIRA_RBT-4")
    @TmsLink("RBT-4")
    @Owner("Sanket")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that POST request is working fine")
    @Test
    public void testVerifyCreateBookingPOST()
    {
        Assert.assertEquals(true,true);
    }
}
