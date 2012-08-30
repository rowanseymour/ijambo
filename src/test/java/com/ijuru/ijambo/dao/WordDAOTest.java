/**
 * Copyright 2012 Rowan Seymour
 * 
 * This file is part of Ijambo.
 *
 * Ijambo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Ijambo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Ijambo. If not, see <http://www.gnu.org/licenses/>.
 */

package com.ijuru.ijambo.dao;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ijuru.ijambo.Context;
import com.ijuru.ijambo.Word.Difficulty;

import static org.junit.Assert.*;

/**
 * Test case for WordDAO
 */
public class WordDAOTest {

	@BeforeClass
	public static void setUp() throws Exception {
		Context.startApplication();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Context.destroyApplication();
	}
	
	@Test
	public void getWordCount() {
		assertEquals(5, Context.getWordDAO().getWordCount());
	}

	@Test
	public void getDifficultyCounts() {
		Map<Difficulty, Integer> counts = Context.getWordDAO().getDifficultyCounts();
		assertEquals(new Integer(2), counts.get(Difficulty.EASY));
		assertEquals(new Integer(2), counts.get(Difficulty.MEDIUM));
		assertEquals(new Integer(1), counts.get(Difficulty.HARD));
	}
	
	@Test
	public void getRandomWord() {
		assertNotNull(Context.getWordDAO().getRandomWord(null));
		assertEquals(Difficulty.EASY, Context.getWordDAO().getRandomWord(Difficulty.EASY).getDifficulty());
		assertEquals(Difficulty.MEDIUM, Context.getWordDAO().getRandomWord(Difficulty.MEDIUM).getDifficulty());
		assertEquals(Difficulty.HARD, Context.getWordDAO().getRandomWord(Difficulty.HARD).getDifficulty());
	}
}
