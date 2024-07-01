package com.lplsystem.service;

import com.lplsystem.pojo.Team;

import java.util.List;

public interface TeamService {
    int saveTeam(Team team);
    List<Team> findAllTeams();
    List<Team> findAllTeams(int startIndex,int size);
    int findCount();
    int deleteTeam(int tid);
    Team findOneTeam(int tid);
    int updateTeam(Team team);
}
