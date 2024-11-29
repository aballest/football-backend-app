package es.aballest.football.application.service;

import es.aballest.football.application.dto.CoachDto;

import java.util.List;
import java.util.Optional;

public interface CoachService {

    List<CoachDto> getAllCoaches();

    Optional<CoachDto> getCoachById(Long id);

    CoachDto createCoach(CoachDto coach);

    CoachDto updateCoach(Long id, CoachDto coach);

    void deleteCoach(Long id);

    Optional<CoachDto> getCoachByTeamId(Long teamId);
}