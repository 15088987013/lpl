package com.lplsystem.service.impl;

import com.lplsystem.dao.TeamDao;
import com.lplsystem.dao.impl.TeamDaoImpl;
import com.lplsystem.pojo.Team;
import com.lplsystem.service.TeamService;

import java.util.List;

public class TeamServiceImpl implements TeamService {
    private TeamDao teamDao = new TeamDaoImpl();
    @Override
    public int saveTeam(Team team) {
        return teamDao.insertOneTeam(team);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamDao.selectAllTeams();
    }

    @Override
    public List<Team> findAllTeams(int startIndex, int size) {
        return teamDao.selectAllTeams(startIndex,size);
    }

    @Override
    public int findCount() {
        return teamDao.selectCount();
    }

    @Override
    public int deleteTeam(int tid) {
        return teamDao.deleteTeam(tid);
    }

    @Override
    public Team findOneTeam(int tid) {
        return teamDao.selectOneTeam(tid);
    }

    @Override
    public int updateTeam(Team team) {
        return teamDao.updateTeam(team);
    }

}
