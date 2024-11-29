package es.aballest.football.application.service.impl;

import es.aballest.football.application.dto.TeamDto;
import es.aballest.football.application.dto.TeamMapper;
import es.aballest.football.application.service.TeamService;
import es.aballest.football.domain.model.Team;
import es.aballest.football.infrastructure.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public List<TeamDto> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream()
                .map(teamMapper::teamToTeamDTO)
                .toList();
    }

    @Override
    public Optional<TeamDto> getTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.map(teamMapper::teamToTeamDTO);
    }

    @Override
    @Transactional
    public TeamDto createTeam(TeamDto teamDTO) {
        Team team = teamMapper.teamDTOToTeam(teamDTO);
        team.setId(null);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.teamToTeamDTO(savedTeam);
    }

    @Override
    @Transactional
    public TeamDto updateTeam(Long id, TeamDto teamDTO) {
        if (teamRepository.existsById(id)) {
            Team team = teamMapper.teamDTOToTeam(teamDTO);
            Team updatedTeam = teamRepository.save(team);
            return teamMapper.teamToTeamDTO(updatedTeam);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}