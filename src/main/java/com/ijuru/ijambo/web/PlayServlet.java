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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ijuru.ijambo.Context;
import com.ijuru.ijambo.Player;
import com.ijuru.ijambo.Utils;
import com.ijuru.ijambo.Word;
import com.ijuru.ijambo.Word.Difficulty;

/**
 * Main game play servlet
 */
public class PlayServlet extends HttpServlet {
	
	protected static final Logger log = LogManager.getLogger(PlayServlet.class);

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message;	
		String playerIdentifier = request.getParameter("id");
		String incoming = request.getParameter("incoming");
		
		if (StringUtils.isEmpty(playerIdentifier)) {
			message = "Player identifier must be provided";
		}
		else if (StringUtils.isEmpty(incoming)) {
			message = "Incoming message must be provided";
		}
		else {
			// Remove keyword from incoming message
			if (incoming.toLowerCase().startsWith("ijambo "))
				incoming = incoming.substring(7).trim();
			
			// Parse difficulty as a parameter
			Difficulty difficulty = null;
			try {
				difficulty = Difficulty.valueOf(incoming.toUpperCase());
			}
			catch (Exception ex) {};
		
			Player player = Context.getPlayerDAO().getPlayer(playerIdentifier);
	
			Word word = Context.getWordDAO().getRandomWord(difficulty);
			String scramble = Utils.scrambleWord(word.getWord().toUpperCase());
			String diffDisplay = StringUtils.capitalize(word.getDifficulty().name().toLowerCase());

			message = "[" + diffDisplay + "] unscramble " + scramble + " to find '" + word.getMeaning() + "'";
			
			if (player == null) {
				message += ". Play again to get answer";
				
				player = new Player(playerIdentifier, word.getWord());
			}
			else {
				message += ". Previous answer: " + player.getPrevAnswer().toUpperCase();
				
				player.setPrevAnswer(word.getWord());
			}
			
			Context.getPlayerDAO().save(player);
		}
		
		response.getWriter().write(message);
	}
}
