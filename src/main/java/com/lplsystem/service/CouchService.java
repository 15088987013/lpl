package com.lplsystem.service;

import com.lplsystem.pojo.Couch;
import com.lplsystem.pojo.Player;

import java.util.List;

public interface CouchService {
    public abstract int saveCouch(Couch Couch);
    public abstract List<Couch> findAllCouch(String name , String phone);
    public abstract List<Couch> findAllCouch(int startIndex, int size);
    List<Couch> findAllCouch();
    public abstract int findCount();
    int deleteCouch(int cid);
    Couch findOneCouch(int cid);
    int updateCouch(Couch couch);
//    public abstract List<Couch> findAllCouch(String name , String phone,int startIndex, int size);
}
