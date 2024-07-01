package com.lplsystem.dao;

import com.lplsystem.pojo.Couch;
import com.lplsystem.pojo.Player;

import java.util.List;

public interface CouchDao {
    int saveCouch(Couch couch);
    List<Couch> selectAllCouch(String name, String phone);
    List<Couch> selectAllCouch(int startIndex,int size);
    List<Couch> selectAllCouch();
    int selectCount();
    int deleteCouch(int cid);
    Couch selectOneCouch(int cid);
    int updateCouch(Couch couch);
    List<Player> selectAllCouch(String name, String phone, int startIndex, int size);
}
