package com.sda.playermanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sda.playermanager.domain.Player;

public class JdbcPlayerDao implements IPlayerDao {

	private static final Logger log = LogManager.getLogger(JdbcPlayerDao.class);

	private static final String dbUrl = "jdbc:mysql://localhost:3306/players?&serverTimezone=EST5EDT";
	private static final String dbUser = "appuser";
	private static final String dbPass = "n3wP4$$";

	private Connection con = null;

	private Connection getConnection() {
		if (log.isDebugEnabled()) {
			log.debug("getConnection(): IN");
		}
		
		if (null == con) {
			try {
				con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			} catch (SQLException e) {
				log.error("Exception while parsing players: " + e.getMessage(), e);
			}
		}
		
		if (log.isDebugEnabled()) {
			log.debug("getConnection(): OUT:" + con);
		}
		return con;
	}

	public void tearDown() {
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				log.error("Exception while closing the connection: " + e.getMessage(), e);
			}
		}		
	}

	public List<Player> getAllPlayers() {
		if (log.isDebugEnabled()) {
			log.debug("getAllPlayers(): IN:");
		}

		List<Player> result = new ArrayList<>();
		
		Connection con = getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from players.players");
			while (rs.next()) {
				Player player = getPlayerFromRS(rs);
				result.add(player);
				
				log.info("Player: " + player);
			}
		} catch (SQLException e) {
			log.error("Exception while parsing players: " + e.getMessage(), e);
		}

		if (log.isDebugEnabled()) {
			log.debug("parseAllPlayers(): OUT:");
		}

		return result;
	}

	public Player getPlayer(int id) {
		if (log.isDebugEnabled()) {
			log.debug("getPlayer(): IN:");
		}

		Player result = null;
		
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from players.players where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = getPlayerFromRS(rs);
				
				log.info("Player: " + result);
			}
		} catch (SQLException e) {
			log.error("Exception while parsing players: " + e.getMessage(), e);
		}

		if (log.isDebugEnabled()) {
			log.debug("getPlayer(): OUT:");
		}

		return result;
	}

	public List<Player> getPlayersByName(String likeName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletePlayer(int id) {
		// TODO Auto-generated method stub

	}

	private Player getPlayerFromRS(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		String accountName = rs.getString("account_name");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		Timestamp birthDate = rs.getTimestamp("birth_date");

		Player player = new Player(
				id,
				accountName,
				firstName,
				lastName,
				null == birthDate ? null : new Date(birthDate.getTime()));
		return player;
	}

	
}
