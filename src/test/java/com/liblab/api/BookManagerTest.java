package com.liblab.api;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.liblab.rest.RESTServices;

import com.liblab.api.model.Book;
import com.liblab.api.model.Chapter;

public class BookManagerTest {

	private static final String BOOK_ID = "5cf58077b53e011a64671583";
	private static final String BOOK_NAME = "The Two Towers";
	
	private BookManager bookManager;

	@Before
	public void setup() {
		bookManager = new BookManager();
	}

	@Test
	public void testBooks() {
		int booksCount = bookManager.getBooks().size();
		assertEquals("Unexpected Books Count", 3, booksCount);
	}

	@Test
	public void testBookById() {
		Book book = bookManager.getBook(BOOK_ID);
		assertNotNull("Book cannot be NULL", book);
		assertEquals("Unexpected Book Name", BOOK_NAME, book.getName());
	}

	@Test
	public void testBookChapters() {		
		List<Chapter> chapters = bookManager.getBookChapters(BOOK_ID);
		assertEquals("Unexpected Chapters Count", 21, chapters.size());
	}

	@Test
	public void testBooksWithCriteria() {		
		List<Book> books = bookManager.getBooks("_id=" + BOOK_ID, 
				"sort=name:" + RESTServices.DESC, 10, 1, 0);
		assertEquals("Unexpected Books Count", 1, books.size());
		assertEquals("Unexpected Book Name", BOOK_NAME, books.get(0).getName());
	}

}