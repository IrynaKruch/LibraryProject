package ua.krucheniuk.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthServiceTest {
	
    private AuthService authService=AuthService.getInstance();
    private String validName="David";
    private String nameWithSpace="Davi d";
    private String validEmail="Test@mail.com";
    private String invalidEmail="Test.com";
    private String validPassword="Password1";
    private String shortPassword="Pas1";


//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public final void testGetInstance() {
		Assert.assertTrue(AuthService.getInstance() instanceof AuthService);
	}

	@Test
	public final void testLogin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRegister() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckName_Valid() {
        Assert.assertTrue(authService.checkName(validName));
	}

	@Test
	public final void testCheckName_inValid() {
        Assert.assertFalse(authService.checkName(nameWithSpace));
	}

	
	@Test
	public final void testCheckLogin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCheckPassword_Valid() {
        Assert.assertTrue(authService.checkPassword(validPassword));
	}
	
	@Test
	public final void testCheckPassword_inValid() {
        Assert.assertFalse(authService.checkPassword(shortPassword));
	}

	@Test
	public final void testCheckEmail_Valid() {
        Assert.assertTrue(authService.checkEmail(validEmail));
	}

	
	@Test
	public final void testCheckEmail_inValid() {
        Assert.assertFalse(authService.checkEmail(invalidEmail));
	}
}
