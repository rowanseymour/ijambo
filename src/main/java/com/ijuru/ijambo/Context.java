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

import com.ijuru.ijambo.dao.PlayerDAO;
import com.ijuru.ijambo.dao.WordDAO;

/**
 * Application context
 */
public class Context {

	private static WordDAO wordDAO;
	private static PlayerDAO playerDAO;

	/**
	 * Gets the word DAO
	 * s@return the word DAO
	 */
	public static WordDAO getWordDAO() {
		return wordDAO;
	}
	
	/**
	 * Gets the player DAO
	 * @return the player DAO
	 */
	public static PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	/**
	 * Sets the word DAO
	 * @param wordDAO the word DAO
	 */
	public static void setWordDAO(WordDAO wordDAO) {
		Context.wordDAO = wordDAO;
	}

	/**
	 * Sets the player DAO
	 * @param playerDAO the player DAO
	 */
	public static void setPlayerDAO(PlayerDAO playerDAO) {
		Context.playerDAO = playerDAO;
	}
}
