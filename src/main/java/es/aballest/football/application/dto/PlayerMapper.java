package es.aballest.football.application.dto;

import es.aballest.football.domain.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDto playerToPlayerDTO(Player player);

    Player playerDTOToPlayer(PlayerDto playerDTO);
}