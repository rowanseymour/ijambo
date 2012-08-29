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

import com.ijuru.ijambo.Player;
import com.mongodb.DB;

/**
 * Data access for words
 */
public class PlayerDAO {

	private DB db;

	/**
	 * Constructs new DAO
	 * @param db the database
	 */
	public PlayerDAO(DB db) {
		this.db = db;
	}
	
	public Player getPlayer(String identifier) {
		return null;
	}
}
