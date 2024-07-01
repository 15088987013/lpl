package com.lplsystem.service;

import com.lplsystem.pojo.Player;
import com.lplsystem.pojo.Team;

import java.util.List;

public interface PlayerService {
    public abstract int savePlayer(Player player);
    public abstract List<Player> findAllPlayer(String name , String phone);
    public abstract List<Player> findAllPlayer(int startIndex, int size);
    List<Player> findAllPlayer();
    public abstract int findCount();
    int deletePlayer(int pid);
    Player findOnePlayer(int pid);
    int updatePlayer(Player player);
    public abstract List<Player> findAllPlayer(String name , String phone,int startIndex, int size);
}
