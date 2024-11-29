package es.aballest.football.application.dto;

import es.aballest.football.domain.model.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto teamToTeamDTO(Team team);

    Team teamDTOToTeam(TeamDto teamDTO);
}