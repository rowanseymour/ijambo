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
 * A player - anyone who has used the game
 */
public class Player {

	private String identifier;
	private Integer lastWord;
	
	public Player(String identifier, Integer lastWord) {
		this.identifier = identifier;
		this.lastWord = lastWord;
	}
	
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @return the lastWord
	 */
	public Integer getLastWord() {
		return lastWord;
	}

	/**
	 * @param lastWord the lastWord to set
	 */
	public void setLastWord(Integer lastWord) {
		this.lastWord = lastWord;
	}
}
