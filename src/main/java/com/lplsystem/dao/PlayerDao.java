package com.lplsystem.dao;

import com.lplsystem.pojo.Player;
import com.lplsystem.pojo.Team;

import java.util.List;

public interface PlayerDao {
    int savePlayer(Player player);
    List<Player> selectAllPlayers(String name, String phone);
    List<Player> selectAllPlayers(int startIndex,int size);
    List<Player> selectAllPlayers();
    int selectCount();
    int deletePlayer(int pid);
    Player selectOnePlayer(int pid);
    int updatePlayer(Player player);
    List<Player> selectAllPlayers(String name, String phone, int startIndex, int size);
}
