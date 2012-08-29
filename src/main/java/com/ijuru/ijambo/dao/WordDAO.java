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

import com.ijuru.ijambo.Word;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * Data access for words
 */
public class WordDAO {

	private DB db;

	/**
	 * Constructs new DAO
	 * @param db the database
	 */
	public WordDAO(DB db) {
		this.db = db;
	}
	
	/**
	 * Gets a random word
	 * @return the word
	 */
	public Word getRandomWord() {
		DBCollection words = db.getCollection("words");
		
		int randOffset = (int)(Math.random() * words.count());
		BasicDBObject obj = (BasicDBObject)words.find().limit(-1).skip(randOffset).next();
		
		return new Word(obj.getInt("dictionaryId"), obj.getString("word"), obj.getString("meaning"));
	}
	
	/**
	 * Removes all words
	 */
	public void removeAll() {
		DBCollection words = db.getCollection("words");
		words.drop();
	}
	
	/**
	 * Saves a word
	 * @param word the word
	 */
	public void save(Word word) {
		DBCollection words = db.getCollection("words");
		
		BasicDBObject obj = new BasicDBObject();
        obj.put("dictionaryId", word.getDictionaryId());
        obj.put("word", word.getWord());
        obj.put("meaning", word.getMeaning());
        
		words.insert(obj);
	}
}
