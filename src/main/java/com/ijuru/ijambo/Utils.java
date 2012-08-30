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

package com.ijuru.ijambo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * General utility methods
 */
public class Utils {

	/**
	 * Scrambles the letters of a word. Shuffles the word twice and returns
	 * the result with the highest levenshtein distance from the original
	 * @param word the word
	 * @return the scrambled word
	 */
	public static String scrambleWord(String word) {
		List<String> chars1 = Arrays.asList(word.split(""));
		List<String> chars2 = new ArrayList<String>(chars1);
				
		Collections.shuffle(chars1);
		Collections.shuffle(chars2);
		
		String scramble1 = StringUtils.join(chars1, "");
		String scramble2 = StringUtils.join(chars2, "");
		
		int dist1 = StringUtils.getLevenshteinDistance(word, scramble1);
		int dist2 = StringUtils.getLevenshteinDistance(word, scramble2);
		
		return (dist1 > dist2) ? scramble1 : scramble2;
	}
}
