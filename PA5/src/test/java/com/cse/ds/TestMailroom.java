package com.cse.ds;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

//17 test case
@FixMethodOrder(MethodSorters.JVM)
public class TestMailroom {

    static Mailroom mailroom;
    Deliverable mail = new MyMail(1, "3811 Nobel Drive, La Jolla, CA, USA, 92037", "2231 Lebon Drive, La Jolla, CA, USA, 92632", "Hello World!");

    @Before
    public void populate(){
        mailroom = new Mailroom();
    }


    @Test // original
    public void testRegister() {
        mailroom.registerItem(mail);
        Assert.assertTrue(mail == mailroom.checkEarliest());
    }

    @Test (expected = NullPointerException.class) // original
    public void testRegisterNull() {
        mailroom.registerItem(null);
    }

    @Test
    public void testDeliverEarliest() {
        Deliverable mail2 = new MyMail(2,"12345","12345","");
	Deliverable mail3 = new MyMail(3,"23456","23456","");
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
        mailroom.deliverEarliest();
	Assert.assertEquals(mail2,mailroom.checkEarliest());
	mailroom.deliverEarliest();
	Assert.assertEquals(mail3,mailroom.checkEarliest());
    }

    @Test
    public void testDeliverEarliestRemoval() {
        mailroom.registerItem(mail);
	mailroom.deliverEarliest();
	Assert.assertEquals(null,mailroom.checkEarliest());
    }

    @Test
    public void testDeliverEarliestStringRemoval() {
        mailroom.registerItem(mail);
	mailroom.deliverEarliest("92632");
	Assert.assertEquals(null,mailroom.deliverEarliest("92632"));
    }

    @Test
    public void testDeliverEarliestString() {
        Deliverable mail2 = new MyMail(2,"12345","12345","hello");
	Deliverable mail3 = new MyMail(3,"12345","12345","hi");
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(mail2,mailroom.deliverEarliest("12345"));
	Assert.assertEquals(mail3,mailroom.checkEarliest("12345"));
	Assert.assertEquals(mail3,mailroom.deliverEarliest("12345"));
	Assert.assertEquals(mail,mailroom.checkEarliest());
    }

    @Test
    public void testCheckEarliest() {
        Deliverable mail2 = new MyMail(2,"12345","12345","");
        Deliverable mail3 = new MyMail(3,"23456","23456","");
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(mail,mailroom.checkEarliest());
    }

    @Test
    public void testCheckEarliestString() {
        Deliverable mail2 = new MyMail(2,"12345","12345","hi");
        Deliverable mail3 = new MyMail(3,"12345","12345","hello");
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(mail2,mailroom.checkEarliest());
    }

    @Test
    public void testDeliverAll() {
        ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
        Deliverable mail2 = new MyMail(2,"12345","12345","hi");
        Deliverable mail3 = new MyMail(3,"23456","23456","hello");
	removed.add(mail);
	removed.add(mail2);
	removed.add(mail3);
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(removed,mailroom.deliverAll());
    }

    @Test
    public void testDeliverAllString() {
        ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
        Deliverable mail2 = new MyMail(2,"12345","12345","");
        Deliverable mail3 = new MyMail(3,"12345","12345","");
	removed.add(mail2);
	removed.add(mail3);
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(removed,mailroom.deliverAll("12345"));
    }

    @Test
    public void testDeliverByWeight() {
        ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
        Deliverable mail2 = new MyMail(2,"12345","12345","");
        Deliverable mail3 = new MyMail(3,"23456","23456","");
	removed.add(mail);
	removed.add(mail2);
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(removed,mailroom.deliverByWeight(2));
    }

    @Test
    public void testDeliverByWeightString() {
        ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
        Deliverable mail2 = new MyMail(2,"12345","12345","hi");
        Deliverable mail3 = new MyMail(3,"12345","12345","hello");
	removed.add(mail2);
	removed.add(mail3);
	mailroom.registerItem(mail);
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	Assert.assertEquals(removed,mailroom.deliverByWeight(2,"12345"));
	Assert.assertEquals(removed.get(0),mail2);
	Assert.assertEquals(removed.get(1),mail3);
    }

    @Test
    public void testDeliverAllMethodsEmpty() {
        ArrayList<Deliverable> removed = new ArrayList<Deliverable>();
	Assert.assertEquals(removed,mailroom.deliverAll());
	Assert.assertEquals(removed,mailroom.deliverAll("11111"));
    }

    @Test
    public void testMergeBins() {
        Deliverable mail2 = new MyMail(0,"12345","12345","");
        Deliverable mail3 = new MyMail(1,"12354","12354","");
	mailroom.registerItem(mail2);
	mailroom.registerItem(mail3);
	mailroom.mergeBins("123");
	System.out.println(mailroom.deliverEarliest("123--").getTimestamp());
	System.out.println(mail2.getTimestamp());
    }

}
