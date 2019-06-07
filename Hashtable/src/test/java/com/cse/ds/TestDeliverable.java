package com.cse.ds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class TestDeliverable {

    static Deliverable mail;
    static Deliverable pkg;

    @Before // original
    public void createDeliverables() {

        String message = "Hello World!";

        String mailFrom = "3811 Nobel Drive,\n La Jolla, CA, USA, 92037";
        String mailTo = "2231 Lebon Drive,\n La Jolla, CA, USA, 92632";
        String packageFrom = "92037,\n 3811 Nobel Drive,\n La Jolla, CA, USA";
        String packageTo = "92632,\n 2231 Lebon Drive,\n La Jolla, CA, USA";

        mail = new MyMail(1,mailFrom,mailTo,message);

        pkg = new MyPackage<String>(2,packageFrom,packageTo,message,5);

    }

    @Test // original
    public void testMailZip() {
        Assert.assertTrue(mail.getZipCode().equals("92632"));
    }

    @Test // original
    public void testPackageZip() {
        Assert.assertTrue(pkg.getZipCode().equals("92632"));
    }

    @Test
    public void testMailMessage() {
        MyMail mail = new MyMail(1,"12345","12345","Hello World!");
        Assert.assertEquals(mail.getMessage(),"Hello World!");
    }

}
