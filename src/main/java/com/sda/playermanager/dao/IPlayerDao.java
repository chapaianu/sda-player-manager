package com.sda.playermanager.dao;

import java.util.List;

import com.sda.playermanager.domain.Player;

public interface IPlayerDao {

	public List<Player> getAllPlayers();
	
	public Player getPlayer(int id);
	
	public List<Player> getPlayersByName(String likeName);
	
	public void deletePlayer(int id);

}
