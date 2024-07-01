package com.lplsystem.dao;

import com.lplsystem.pojo.Team;

import java.util.List;

public interface TeamDao {
    int insertOneTeam(Team team);
    List<Team> selectAllTeams();
    List<Team> selectAllTeams(int startIndex,int size);
    int selectCount();
    int deleteTeam(int tid);
    Team selectOneTeam(int tid);
    int updateTeam(Team team);
}
