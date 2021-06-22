package ua.krucheniuk.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import ua.krucheniuk.dao.model.DaoFactory;


public class LibrarianServiceTest {
	
	LibrarianService librarianService = LibrarianService.getInstance();
	@Mock
	DaoFactory factory = DaoFactory.getInstance();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetInstance() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFindAllBooks() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBookOrders() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBookToReader() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetInfoAboutAllReaders() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCountDaysLeft() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCountDebt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetBookAmount() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testReaderReturnBook() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsBookOrdered() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteBook() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFindUserById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddNewBook() {
		fail("Not yet implemented"); // TODO
	}

}
