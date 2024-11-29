package es.aballest.football.application.service.impl;

import es.aballest.football.application.dto.CoachDto;
import es.aballest.football.application.dto.CoachMapper;
import es.aballest.football.application.service.CoachService;
import es.aballest.football.domain.model.Coach;
import es.aballest.football.infrastructure.repository.CoachRepository;
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
public class CoachServiceImpl implements CoachService {

    private final CoachRepository coachRepository;

    private final CoachMapper coachMapper;

    @Override
    public List<CoachDto> getAllCoaches() {
        List<Coach> coaches = coachRepository.findAll();
        return coaches.stream()
                .map(coachMapper::coachToCoachDto)
                .toList();
    }

    @Override
    public Optional<CoachDto> getCoachById(Long id) {
        Optional<Coach> coach = coachRepository.findById(id);
        return coach.map(coachMapper::coachToCoachDto);
    }

    @Override
    @Transactional
    public CoachDto createCoach(CoachDto coachDTO) {
        Coach coach = coachMapper.coachDtoToCoach(coachDTO);
        coach.setId(null);
        Coach savedCoach = coachRepository.save(coach);
        return coachMapper.coachToCoachDto(savedCoach);
    }

    @Override
    @Transactional
    public CoachDto updateCoach(Long id, CoachDto coachDTO) {
        if (coachRepository.existsById(id)) {
            Coach coach = coachMapper.coachDtoToCoach(coachDTO);
            Coach updatedCoach = coachRepository.save(coach);
            return coachMapper.coachToCoachDto(updatedCoach);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }

    @Override
    public Optional<CoachDto> getCoachByTeamId(Long teamId) {
        Optional<Coach> coach = coachRepository.findCoachByTeamId(teamId);
        return coach.map(coachMapper::coachToCoachDto);
    }
}