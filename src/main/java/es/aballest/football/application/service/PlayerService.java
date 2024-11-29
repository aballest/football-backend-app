package es.aballest.football.application.service;

import es.aballest.football.application.dto.PlayerDto;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    List<PlayerDto> getAllPlayers();

    Optional<PlayerDto> getPlayerById(Long id);

    PlayerDto createPlayer(PlayerDto player);

    PlayerDto updatePlayer(Long id, PlayerDto player);

    void deletePlayer(Long id);

    List<PlayerDto> getPlayersByTeamId(Long teamId);
}