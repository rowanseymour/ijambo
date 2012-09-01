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
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import au.com.bytecode.opencsv.CSVReader;

import com.ijuru.ijambo.Word.Difficulty;
import com.ijuru.ijambo.dao.PlayerDAO;
import com.ijuru.ijambo.dao.WordDAO;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

/**
 * Application context
 */
public class Context {

	protected static final Logger log = LogManager.getLogger(Context.class);
	
	private static final String WORD_FILE = "/words.csv";
	
	private static Mongo m;
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
	 * Initializes the application
	 * @throws IOException if the word list could not be read
	 * @throws ParseException if JSON env variable could not be read
	 */
	public static void startApplication() throws IOException, UnknownHostException, ParseException {		
		
		DB db = connectDatabase();
		db.getCollection("players").ensureIndex("identifier");
		
		wordDAO = new WordDAO(db);
		playerDAO = new PlayerDAO(db);
		
		// Loads the word list
		loadWordList();
		
		Map<Difficulty, Integer> counts = wordDAO.getDifficultyCounts();
		log.info("Ijambo started. Loaded " + wordDAO.getWordCount() + 
				" words (EASY: " + counts.get(Difficulty.EASY) + 
				" MEDIUM: " + counts.get(Difficulty.MEDIUM) + 
				" HARD: " + counts.get(Difficulty.HARD) + ")");
	}
	
	/**
	 * Connects to the database
	 * @return the Mongo DB
	 * @throws ParseException
	 * @throws UnknownHostException
	 */
	public static DB connectDatabase() throws ParseException, UnknownHostException {
		
		// Check for AppFog environment
		String appFogEnv = System.getenv("VCAP_SERVICES");

		if (appFogEnv != null) {	
			// Connect to MongoDB as AppFog service
			JSONParser parser = new JSONParser();
			JSONObject svcs = (JSONObject)parser.parse(appFogEnv);
			JSONArray mongoSettings = (JSONArray)svcs.get("mongodb-1.8");
			JSONObject mongoSettings0 = (JSONObject)mongoSettings.get(0);
			JSONObject mongoCreds0 = (JSONObject)mongoSettings0.get("credentials");
			//String db = (String)mongoCreds0.get("db");
			String connectionURL = (String)mongoCreds0.get("url");
			
			log.info("Running as AppFog instance with connection URL: " + connectionURL);
			
			DB db = new MongoURI(connectionURL).connectDB();
			
			// https://jira.mongodb.org/browse/JAVA-436
			db.authenticate((String)mongoCreds0.get("username"), ((String)mongoCreds0.get("password")).toCharArray());
			
			return db;
		}
		
		// Create default MongoDB instance
		m = new Mongo();
		return m.getDB("ijambo");
	}
	
	/**
	 * Destroys the application
	 */
	public static void destroyApplication() {
		if (m != null)
			m.close();
	}
	
	/**
	 * Loads the wordlist from the embedded CSV
	 * @throws IOException if the file could not be read
	 */
	public static void loadWordList() throws IOException {
		InputStream words = Context.class.getResourceAsStream(WORD_FILE);
		CSVReader reader = new CSVReader(new InputStreamReader(words));
	    String[] nextLine;
	    
	    // Clear existing words
	    wordDAO.removeAll();
	   
	    // Load each word
	    while ((nextLine = reader.readNext()) != null) {
	    	Word.Difficulty difficulty = Word.Difficulty.fromInt(Integer.parseInt(nextLine[3]) - 1);
	    	Word word = new Word(nextLine[1], nextLine[2], difficulty);
	    	wordDAO.save(word);
	    }
	    
	    reader.close();
	}
}
