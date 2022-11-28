package com.liblab.api;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.liblab.rest.RESTServices;

import com.liblab.api.model.Chapter;

public class ChapterManagerTest {

	private static final String CHAPTER_ID= "6091b6d6d58360f988133ba1";
	
	private ChapterManager chapterManager;

	@Before
	public void setup() {
		chapterManager = new ChapterManager();
	}

	@Test
	public void testChapters() {
		int chaptersCount = chapterManager.getChapters().size();
		assertEquals("Unexpected Chapters Count", 62, chaptersCount);
	}

	@Test
	public void testChapterById() {
		Chapter chapter = chapterManager.getChapter(CHAPTER_ID);
		assertNotNull("Chapter cannot be NULL", chapter);
		assertEquals("Unexpected Chapter Name", "The Departure of Boromir", chapter.getChapterName());
	}
	
	@Test
	public void testChaptersWithCriteria() {		
		List<Chapter> chapters = chapterManager.getChapters("book=5cf5805fb53e011a64671582", 
				"sort=chapterName:" + RESTServices.ASC, 4, 1, 0);
		assertEquals("Unexpected Chapters Count", 4, chapters.size());
		assertEquals("Unexpected Chapter Name", "A Conspiracy Unmasked", chapters.get(0).getChapterName());
	}


}