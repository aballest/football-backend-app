package es.aballest.football.application.service;

import es.aballest.football.application.dto.TeamDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    List<TeamDto> getAllTeams();

    Optional<TeamDto> getTeamById(Long id);

    TeamDto createTeam(TeamDto team);

    TeamDto updateTeam(Long id, TeamDto team);

    void deleteTeam(Long id);
}