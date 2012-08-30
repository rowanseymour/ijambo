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

/**
 * A word which can be guessed
 */
public class Word {
	
	public enum Difficulty { 
		EASY, MEDIUM, HARD;
		
		public static Difficulty fromInt(int val) {
			return (val >= 0 && val <= 2) ? values()[val] : null;
		}
	}

	private String word;
	private String meaning;
	private Difficulty difficulty;
	
	/**
	 * Constructs a new word
	 * @param word the word
	 * @param meaning the meaning
	 * @param difficulty the difficulty
	 */
	public Word(String word, String meaning, Difficulty difficulty) {
		this.word = word;
		this.meaning = meaning;
		this.difficulty = difficulty;
	}

	/**
	 * Gets the word
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Gets the meaning
	 * @return the meaning
	 */
	public String getMeaning() {
		return meaning;
	}
	
	/**
	 * Gets the difficulty
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}
}
