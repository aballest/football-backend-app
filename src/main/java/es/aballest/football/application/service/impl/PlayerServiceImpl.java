package es.aballest.football.application.service.impl;

import es.aballest.football.application.dto.PlayerDto;
import es.aballest.football.application.dto.PlayerMapper;
import es.aballest.football.application.service.PlayerService;
import es.aballest.football.domain.model.Player;
import es.aballest.football.infrastructure.repository.PlayerRepository;
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
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(playerMapper::playerToPlayerDTO)
                .toList();
    }

    @Override
    public Optional<PlayerDto> getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map(playerMapper::playerToPlayerDTO);
    }

    @Override
    @Transactional
    public PlayerDto createPlayer(PlayerDto playerDTO) {
        Player player = playerMapper.playerDTOToPlayer(playerDTO);
        player.setId(null);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.playerToPlayerDTO(savedPlayer);
    }

    @Override
    @Transactional
    public PlayerDto updatePlayer(Long id, PlayerDto playerDTO) {
        if (playerRepository.existsById(id)) {
            Player player = playerMapper.playerDTOToPlayer(playerDTO);
            Player updatedPlayer = playerRepository.save(player);
            return playerMapper.playerToPlayerDTO(updatedPlayer);
        }
        return null;
    }

    @Override
    @Transactional
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public List<PlayerDto> getPlayersByTeamId(Long teamId) {
        List<Player> players = playerRepository.findByTeamId(teamId);
        return players.stream()
                .map(playerMapper::playerToPlayerDTO)
                .toList();
    }
}