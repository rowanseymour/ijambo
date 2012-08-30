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
 * Player - anyone who has used the game
 */
public class Player {

	private String identifier;
	private String prevAnswer;
	
	public Player(String identifier, String prevAnswer) {
		this.identifier = identifier;
		this.prevAnswer = prevAnswer;
	}
	
	/**
	 * Gets the identifier
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the answer to the player's previous game
	 * @return the previous answer
	 */
	public String getPrevAnswer() {
		return prevAnswer;
	}

	/**
	 * @param prevAnswer the previous game answer
	 */
	public void setPrevAnswer(String prevAnswer) {
		this.prevAnswer = prevAnswer;
	}
}
