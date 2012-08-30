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

import java.util.HashMap;
import java.util.Map;

import com.ijuru.ijambo.Word;
import com.ijuru.ijambo.Word.Difficulty;
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
	 * Gets the number of words
	 * @return the number
	 */
	public long getWordCount() {
		return db.getCollection("words").count();
	}
	
	/**
	 * Gets the number of words with different difficulty levels
	 * @return map of difficulty levels and counts
	 */
	public Map<Difficulty, Integer> getDifficultyCounts() {
		DBCollection words = db.getCollection("words");
		Map<Difficulty, Integer> counts = new HashMap<Difficulty, Integer>();
		
		for (Difficulty difficulty : Difficulty.values()) {
			BasicDBObject query = new BasicDBObject();
			query.put("difficulty", difficulty.ordinal());
			counts.put(difficulty, words.find(query).count());
		}
		
		return counts;
	}
	
	/**
	 * Gets a random word
	 * @param difficulty the difficulty (may be null)
	 * @return the word
	 */
	public Word getRandomWord(Difficulty difficulty) {
		DBCollection words = db.getCollection("words");
		BasicDBObject obj;
		
		if (difficulty != null) {
			// Get count of words of this difficulty
			BasicDBObject query = new BasicDBObject();
			query.put("difficulty", difficulty.ordinal());
			int count = words.find(query).count();
		
			// Pick random one
			int randOffset = (int)(Math.random() * count);
			obj = (BasicDBObject)words.find(query).limit(-1).skip(randOffset).next();
		}
		else {
			int randOffset = (int)(Math.random() * words.find().count());
			obj = (BasicDBObject)words.find().limit(-1).skip(randOffset).next();
		}

		return new Word(obj.getString("word"), obj.getString("meaning"), Difficulty.fromInt(obj.getInt("difficulty")));
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
        obj.put("word", word.getWord());
        obj.put("meaning", word.getMeaning());
        obj.put("difficulty", word.getDifficulty().ordinal());
        
		words.insert(obj);
	}
}
