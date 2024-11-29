package es.aballest.football.application.dto;

import es.aballest.football.domain.model.Coach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoachMapper {

    @Mapping(source = "team", target = "team")
    CoachDto coachToCoachDto(Coach coach);

    @Mapping(source = "team", target = "team")
    Coach coachDtoToCoach(CoachDto coachDto);
}