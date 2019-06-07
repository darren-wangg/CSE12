package com.cse.ds;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
  *
  */ 
public class TestStudent {

	static Student obj = null;

	/**
 	  *
 	  */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj = new Student("name", "1", "major", "minor", 4.1,
				  "college", "email");
	}

	/**
 	  *
 	  */ 
	@Test
	public void testHashCode() {
		Student stud = new Student("name", "1", "major", "minor", 4.1,
					    "college", "email");
		Assert.assertEquals(obj.hashCode(), stud.hashCode());
	}

	/**
	  *
	  */
	@Test (expected = NullPointerException.class)
	public void testHashCodeNull() throws Exception {
		Student stud = null;
		stud.hashCode();
	}

	/**
 	  *
 	  */ 
	@Test
	public void testEquals() {
		Student stud1 = new Student("name", "1", "major", "minor", 4.1,
					    "college", "email");
		Assert.assertTrue(obj.equals(stud1) && stud1.equals(obj));
	}

	/**
	  *
	  */
	@Test
	public void testNotEquals() {
		Object notStud = new Object();
		Assert.assertFalse(obj.equals(notStud) && notStud.equals(obj));
	}

	/**
	  *
	  */
	@Test
	public void testToStringNull() {
		Student stud = new Student();
		Assert.assertEquals(stud.toString(), "name:null|PID:null|" +
				    "major:null|minor:null|CGPA:null|" +
				    "college:null|email:null");
	}

	/**
 	  *
 	  */
	@Test
	public void testToString() {
		Student stud1 = new Student("name", "1", "major", "minor", 4.1,
					    "college", "email");
		Assert.assertEquals(stud1.toString(), "name:name|PID:1|" +
				    "major:major|minor:minor|CGPA:4.100000|" +
				    "college:college|email:email");
	}
 
	/**
 	  *
 	  */ 
	@Test
	public void testGetPIDNull() {
		Student stud1 = new Student("name", null, "major", "minor",
					    4.1, "college", "email");
		try {
			stud1.getPID();
		}  catch (StackOverflowError e) {
			Assert.fail();
		}
	}

	/**
	  *
	  */
	@Test
	public void testGetPID() {
		try {
			obj.getPID();
		} catch (StackOverflowError e) {
			Assert.fail();
		}
	}

	/**
 	  *
 	  */ 
	@Test
	public void testGetNameNull() {
		Student stud1 = new Student(null, "1", "major", "minor",
					    4.1, "college", "email");
		try {
			stud1.getName();
		}  catch (StackOverflowError e) {
			Assert.fail();
		}
	}

	/**
	  *
	  */
	@Test
	public void testGetName() {
		try {
			obj.getName();
		} catch (StackOverflowError e) {
			Assert.fail();
		}
	}

	/**
 	  *
 	  */ 
	@Test
	public void testGetEmailNull() {
		Student stud1 = new Student("name", "1", "major", "minor",
					    4.1, "college", null);
		try {
			stud1.getEmail();
		}  catch (StackOverflowError e) {
			Assert.fail();
		}
	}

	/**
	  *
	  */
	@Test
	public void testGetEmail() {
		try {
			obj.getEmail();
		} catch (StackOverflowError e) {
			Assert.fail();
		}
	}
}
