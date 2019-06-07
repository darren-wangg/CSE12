package com.cse.ds;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Matchers;

import java.util.*;

/**
  *
  */ 
public class TestController {
	
	static Controller obj = null;

	/**
	  *
	  */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		obj = new Controller();
	}

	/**
 	  *
 	  */
	@Test (expected = IllegalArgumentException.class)
	public void testGetNullStudent() throws Exception {
		obj.getStudent(null);
	}

	/**
 	  *
 	  */
	@Test (expected = IllegalArgumentException.class)
	public void testGetNullPIDStudent() throws Exception {
		// create mock Student object, force broken .getPID to return
		// null
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);

		obj.getStudent(stud);
	}

	/**
  	  *
  	  */ 
	@Test
	public void testGetStudentFound() {
		// create mock Student object, force broken .getPID to return
		// "1"
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");
		Mockito.when(stud.getName()).thenReturn("name");
		
		// create mock implementation of IService interface
		// force test case: .getStudent(student) != null
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.getStudent(Matchers.any(Student.class))).thenReturn(stud);
		
		obj.setService(serv);
		Response res = obj.getStudent(stud);

		// expected: code = 200, string = "OK", PID = "1"
		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 200);
		Assert.assertEquals(res.getResponseString().toString(),"OK");
		Assert.assertEquals(((Student)res.getContent()).getPID(),"1");
		Assert.assertEquals(((Student)res.getContent()).getName(),"name");
	}

	/**
 	  *
 	  */ 	
	@Test
	public void testGetStudentNotFound() {
		// create mock Student object, force broken .getPID to return
		// "1"
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// create mock implementation of IService interface
		// force test case: .getStudent(student) == null
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.getStudent(Matchers.any(Student.class))).thenReturn(null);
		
		obj.setService(serv);
		Response res = obj.getStudent(stud);

		// expected: code = 404, string = "NOT_FOUND"
		Assert.assertNotNull(res);
		Assert.assertEquals((int)res.getResponseCode(), 404);
		Assert.assertEquals(res.getResponseString().toString(),"NOT_FOUND");
	}

	/**
 	  *
 	  */
	@Test (expected = IllegalArgumentException.class)
	public void testAddStudentNull() throws Exception {
		obj.addStudent(null);
	}

	/**
	  *
	  */
	@Test (expected = IllegalArgumentException.class)
	public void testAddStudentNullPIDandName() throws Exception {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		Mockito.when(stud.getName()).thenReturn(null);
		obj.addStudent(stud);
	}

	/**
	  *
	  */
	@Test (expected = IllegalArgumentException.class)
	public void testAddStudentNullPIDandEmail() throws Exception {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		Mockito.when(stud.getEmail()).thenReturn(null);
		obj.addStudent(stud);
	}

	/**
 	  *
 	  */
	@Test
	public void testAddStudent() {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// force test case: .addStudent(student) == true
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.addStudent(Matchers.any(Student.class))).thenReturn(true);

		obj.setService(serv);
		Response res = obj.addStudent(stud);

		// expected: code = 201, string = "Created"
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 201);
		Assert.assertEquals((String) res.getResponseString(), "CREATED");
	}

	/**
 	  *
 	  */
	@Test
	public void testAddStudentModified() {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// force test case: .addStudent(student) == false
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.addStudent(Matchers.any(Student.class))).thenReturn(false);

		obj.setService(serv);
		Response res = obj.addStudent(stud);

		// expected: code = 202, string = "Accepted"
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 202);
		Assert.assertEquals((String) res.getResponseString(), "ACCEPTED");
	}

	/**
	  *
	  */
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveStudentNull() throws Exception {
		obj.removeStudent(null);
	}

	/**
	  *
	  */
	@Test (expected = IllegalArgumentException.class)
	public void testRemoveStudentNullPID() throws Exception {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		obj.removeStudent(stud);
	}

	/**
	  *
	  */
	@Test
	public void testRemoveStudentOK() {
		// create mock Student object, force broken .getPID to return
		// "1"
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// create mock implementation of IService interface
		// force test case: .removeStudent(student) == true
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.removeStudent(Matchers.any(Student.class))).thenReturn(true);

		obj.setService(serv);
		Response res = obj.removeStudent(stud);

		// expected: code = 200, string = "OK"
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 200);
		Assert.assertEquals((String) res.getResponseString(), "OK");
	}

	/**
	  *
	  */
	@Test
	public void testRemoveStudentError() {
		// create mock Student object, force broken .getPID to return
		// "1"
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// create mock implementation of IService interface
		// force test case: .removeStudent(student) == false
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.removeStudent(Matchers.any(Student.class))).thenReturn(false);

		obj.setService(serv);
		Response res = obj.removeStudent(stud);

		// expected: code = 500, string = "INTERNAL_ERROR"
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 500);
		Assert.assertEquals((String) res.getResponseString(), "INTERNAL_ERROR");
	}

	/**
	  *
	  */
	@Test (expected = IllegalArgumentException.class)
	public void testUpdateStudentNull() throws Exception {
		obj.updateStudent(null);
	}

	/**
	  *
	  */
	@Test (expected = IllegalArgumentException.class)
	public void testUpdateStudentNullPID() throws Exception {
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn(null);
		obj.updateStudent(stud);
	}

	/**
	  *
	  */
	@Test
	public void testUpdateStudentOK() {
		// create mock Student object, force broken .getPID to return
		// "1"
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// create mock implementation of IService interface
		// force test case: .updateStudent(student) == true
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.updateStudent(Matchers.any(Student.class))).thenReturn(true);

		obj.setService(serv);
		Response res = obj.updateStudent(stud);

		// expected: code = 200, string = "OK"
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 200);
		Assert.assertEquals((String) res.getResponseString(), "OK");
	}

	/**
	  *
	  */
	@Test
	public void testUpdateStudentNotFound() {
		// create mock Student object, force broken .getPID to return
		// "1"
		Student stud = Mockito.mock(Student.class);
		Mockito.when(stud.getPID()).thenReturn("1");

		// create mock implementation of IService interface
		// force test case: .updateStudent(student) == false
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.updateStudent(Matchers.any(Student.class))).thenReturn(false);

		obj.setService(serv);
		Response res = obj.updateStudent(stud);

		// expected: code = 404, string = "NOT_FOUND"
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 404);
		Assert.assertEquals((String) res.getResponseString(), "NOT_FOUND");
	}

	/**
	  *
	  */
	@Test
	public void testGetAllStudentNotFound() {
		// create mock implementation of IService interface
		// force test case: .studList.size() == 0
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.getAllStudent()).thenReturn(new ArrayList<Student>());

		obj.setService(serv);
		Response res = obj.getAllStudent();

		// expected: code = 404, string = "NOT_FOUND", size = 0 (empty)
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 404);
		Assert.assertEquals((String) res.getResponseString(), "NOT_FOUND");
		Assert.assertEquals(res.getListContent().size(), 0);
	}

	/**
	  *
	  */
	@Test
	public void testGetAllStudentOK() {
		// example Student objects
		Student stud1 = new Student("name", "1", "major", "minor", 4.1,
				    	    "college", "email");
		Student stud2 = new Student("name", "2", "major", "minor", 4.1,
				    	    "college", "email");
		Student stud3 = new Student("name", "3", "major", "minor", 4.1,
				    	    "college", "email");

		// test an example arraylist of Student objects, .size() == 3
		ArrayList<Student> studs = new ArrayList<Student>();
		studs.add(stud1);
		studs.add(stud2);
		studs.add(stud3);

		// create mock implementation of IService interface
		// force test case: .studList.size() != 0 (>0)
		IService serv = Mockito.mock(IService.class);
		Mockito.when(serv.getAllStudent()).thenReturn(studs);

		obj.setService(serv);
		Response res = obj.getAllStudent();

		// expected: code = 200, string = "OK", size = 3
		Assert.assertNotNull(res);
		Assert.assertEquals((int) res.getResponseCode(), 200);
		Assert.assertEquals((String) res.getResponseString(), "OK");
		Assert.assertEquals(res.getListContent().size(), 3);
	}

}
