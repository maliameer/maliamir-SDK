package com.liblab.api;

import java.util.List;

import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.liblab.rest.RESTServices;

import com.liblab.api.model.Character;
import com.liblab.api.model.Quote;

public class CharacterManagerTest {

	private static final String CHARACTER_ID = "5cd99d4bde30eff6ebccfbbe";
	private static final String CHARACTER_NAME = "Gandalf";
	
	private CharacterManager characterManager;

	@Before
	public void setup() {
		characterManager = new CharacterManager();
	}

	@Test
	public void testCharacters() {
		int charactersCount = characterManager.getCharacters().size();
		assertEquals("Unexpected Characters Count", 933, charactersCount);
	}

	@Test
	public void testCharacterById() {
		Character character = characterManager.getCharacter(CHARACTER_ID);
		assertNotNull("Character cannot be NULL", character);
		assertEquals("Unexpected Character Name", "Adanel", character.getName());
	}

	@Test
	public void testCharacterQuotes() {		
		List<Quote> quotes = characterManager.getCharacterQuotes(CHARACTER_ID);
		assertEquals("Unexpected Quotes Count", 0, quotes.size());
	}

	@Test
	public void testCharactersWithCriteria() {		
		List<Character> characters = characterManager.getCharacters("name=" + URLEncoder.encode(CHARACTER_NAME), 
				"sort=name:" + RESTServices.DESC, 10, 1, 0);
		assertEquals("Unexpected Characters Count", 1, characters.size());
		assertEquals("Unexpected Character Name", CHARACTER_NAME, characters.get(0).getName());
	}

}