package com.lplsystem.service.impl;

import com.lplsystem.dao.PlayerDao;
import com.lplsystem.dao.impl.PlayerDaoImpl;
import com.lplsystem.pojo.Player;
import com.lplsystem.service.PlayerService;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    private PlayerDao playerDao=new PlayerDaoImpl();
    @Override
    public int savePlayer(Player player) {
        return playerDao.savePlayer(player);
    }

    @Override
    public List<Player> findAllPlayer(String name, String phone) {
        return playerDao.selectAllPlayers(name,phone);
    }

    @Override
    public List<Player> findAllPlayer(int startIndex, int size) {
        return playerDao.selectAllPlayers(startIndex,size);
    }

    @Override
    public List<Player> findAllPlayer() {
        return playerDao.selectAllPlayers();
    }

    @Override
    public int findCount() {
        return playerDao.selectCount();
    }

    @Override
    public int deletePlayer(int pid) {
        return playerDao.deletePlayer(pid);
    }

    @Override
    public Player findOnePlayer(int pid) {
        return playerDao.selectOnePlayer(pid);
    }

    @Override
    public int updatePlayer(Player player) {
        return playerDao.updatePlayer(player);
    }

    @Override
    public List<Player> findAllPlayer(String name, String phone, int startIndex, int size) {
        return playerDao.selectAllPlayers(name,phone,startIndex,size);
    }

}
