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

package com.ijuru.ijambo.web;

import com.ijuru.ijambo.Utils;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

	public void test_scrambleWord() {
		String[] words = { "hello", "salama", "inka", "it" };
		
		for (String word : words) {
			String scramble = Utils.scrambleWord(word);
			
			assertNotNull(scramble);
			assertEquals(word.length(), scramble.length());
		}
	}
}
