package com.sda.playermanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sda.playermanager.dao.IPlayerDao;
import com.sda.playermanager.dao.JdbcPlayerDao;
import com.sda.playermanager.domain.Player;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final Logger log = LogManager.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	if (log.isDebugEnabled()) {
    		log.debug("main(): a intrat in metoda:");
    	}
    	        
        IPlayerDao playerDao = new JdbcPlayerDao();
        // List<Player> allPlayers = playerDao.getAllPlayers();
        Player player = playerDao.getPlayer(2);
        
        log.info("Player 2: " + player);
        
        player = playerDao.getPlayer(10);
 
        log.info("Player 10: " + player);

        if (log.isDebugEnabled()) {
        	log.debug("main(): a iesit !!!:");
        }
    }
 
}
