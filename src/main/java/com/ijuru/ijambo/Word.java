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

	private Integer dictionaryId;
	private String word;
	private String meaning;
	
	/**
	 * Constructs a new word
	 * @param dictionaryId the dictionary ID
	 * @param word the word
	 * @param meaning the meaning
	 */
	public Word(Integer dictionaryId, String word, String meaning) {
		this.dictionaryId = dictionaryId;
		this.word = word;
		this.meaning = meaning;
	}

	/**
	 * Gets the dictionary ID
	 * @return the dictionary ID
	 */
	public Integer getDictionaryId() {
		return dictionaryId;
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
}
