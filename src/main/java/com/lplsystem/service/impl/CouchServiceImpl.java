package com.lplsystem.service.impl;

import com.lplsystem.dao.CouchDao;
import com.lplsystem.dao.impl.CouchDaoImpl;
import com.lplsystem.pojo.Couch;
import com.lplsystem.service.CouchService;

import java.util.List;

public class CouchServiceImpl implements CouchService {
    private final CouchDao couchDao=new CouchDaoImpl();
    @Override
    public int saveCouch(Couch couch) {
        return couchDao.saveCouch(couch);
    }

    @Override
    public List<Couch> findAllCouch(String name, String phone) {
        return couchDao.selectAllCouch();
    }

    @Override
    public List<Couch> findAllCouch(int startIndex, int size) {
        return couchDao.selectAllCouch(startIndex,size);
    }

    @Override
    public List<Couch> findAllCouch() {
        return couchDao.selectAllCouch();
    }

    @Override
    public int findCount() {
        return couchDao.selectCount();
    }

    @Override
    public int deleteCouch(int cid) {
        return couchDao.deleteCouch(cid);
    }

    @Override
    public Couch findOneCouch(int cid) {
        return couchDao.selectOneCouch(cid);
    }

    @Override
    public int updateCouch(Couch couch) {
        return couchDao.updateCouch(couch);
    }
}
