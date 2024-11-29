package es.aballest.football.infrastructure.webservice;

import es.aballest.football.application.dto.PlayerDto;
import es.aballest.football.application.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@Tag(name = "Player API", description = "API para la gestión de jugadores")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Obtener todos los jugadores", description = "Devuelve una lista de todos los jugadores.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<PlayerDto> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Obtener un jugador por ID", description = "Devuelve los detalles de un jugador dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jugador encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlayerDto.class))),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crear un nuevo jugador", description = "Crea un nuevo jugador y lo guarda en la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Jugador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody PlayerDto playerDto) {
        PlayerDto createdPlayer = playerService.createPlayer(playerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @Operation(summary = "Actualizar un jugador existente", description = "Actualiza los datos de un jugador dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jugador actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDto> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDto playerDto) {
        PlayerDto updatedPlayer = playerService.updatePlayer(id, playerDto);
        if (updatedPlayer != null) {
            return ResponseEntity.ok(updatedPlayer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Eliminar un jugador", description = "Elimina un jugador dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Jugador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        if (playerService.getPlayerById(id).isPresent()) {
            playerService.deletePlayer(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Obtener jugadores por ID de equipo", description = "Devuelve una lista de jugadores pertenecientes a un equipo específico.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<PlayerDto>> getPlayersByTeamId(@PathVariable Long teamId) {
        List<PlayerDto> players = playerService.getPlayersByTeamId(teamId);
        return ResponseEntity.ok(players);
    }
}