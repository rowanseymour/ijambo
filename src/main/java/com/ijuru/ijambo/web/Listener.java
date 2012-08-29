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

package com.ijuru.ijambo.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.ijuru.ijambo.Context;
import com.ijuru.ijambo.Word;
import com.ijuru.ijambo.dao.PlayerDAO;
import com.ijuru.ijambo.dao.WordDAO;
import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * Listens for webapp stop/start
 */
public class Listener implements ServletContextListener {

	protected static final Logger log = LogManager.getLogger(Listener.class);
	
	private static final String DB_NAME = "ijambo-sms";
	private static final String WORD_FILE = "/words.csv";
	
	private Mongo m;
	
	/**
	 * Web application is being deployed or started
	 */
	public void contextInitialized(ServletContextEvent event) {	
		log.info("Initializing Ijambo SMS");
		
		try {
			m = new Mongo();
			
			log.info("Connected to MongoDB instance");
			
			DB db = m.getDB(DB_NAME);
			
			Context.setWordDAO(new WordDAO(db));
			Context.setPlayerDAO(new PlayerDAO(db));
			
			loadWordList();		
		}
		catch (UnknownHostException ex) {
			log.info("Unable to connect to MongoDB instance", ex);
		} 
		catch (IOException ex) {
			log.info("Unable to load word list", ex);
		}
	}
	
	/**
	 * Loads the wordlist from the embedded CSV
	 * @param db the mongo DB
	 * @throws IOException if the file could not be read
	 */
	public void loadWordList() throws IOException {
		InputStream words = Listener.class.getResourceAsStream(WORD_FILE);
		CSVReader reader = new CSVReader(new InputStreamReader(words));
	    String[] nextLine;
	    
	    // Clear existing words
	    Context.getWordDAO().removeAll();
	   
	    // Load each word
	    while ((nextLine = reader.readNext()) != null) {
	    	int dictionaryId = Integer.parseInt(nextLine[0]);
	    	Word word = new Word(dictionaryId, nextLine[1], nextLine[2]);
	    	Context.getWordDAO().save(word);
	    }
	    
	    reader.close();
	}
	
	/**
	 * Web application is being undeployed or stopped
	 */
	public void contextDestroyed(ServletContextEvent event) {
		log.info("Destroying Ijambo SMS");
	}
}
