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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import au.com.bytecode.opencsv.CSVReader;

import com.ijuru.ijambo.dao.PlayerDAO;
import com.ijuru.ijambo.dao.WordDAO;
import com.ijuru.ijambo.web.Listener;

/**
 * Application context
 */
public class Context {

	private static final String WORD_FILE = "/words.csv";
	
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
	 * Loads the wordlist from the embedded CSV
	 * @throws IOException if the file could not be read
	 */
	public static void loadWordList() throws IOException {
		InputStream words = Listener.class.getResourceAsStream(WORD_FILE);
		CSVReader reader = new CSVReader(new InputStreamReader(words));
	    String[] nextLine;
	    
	    // Clear existing words
	    wordDAO.removeAll();
	   
	    // Load each word
	    while ((nextLine = reader.readNext()) != null) {
	    	int dictionaryId = Integer.parseInt(nextLine[0]);
	    	Word word = new Word(dictionaryId, nextLine[1], nextLine[2]);
	    	wordDAO.save(word);
	    }
	    
	    reader.close();
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
