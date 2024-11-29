package es.aballest.football.infrastructure.webservice;

import es.aballest.football.application.dto.TeamDto;
import es.aballest.football.application.service.TeamService;
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
@RequestMapping("/teams")
@Tag(name = "Team API", description = "API para la gestión de equipos")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista de todos los equipos.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamDto> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @Operation(summary = "Obtener un equipo por ID", description = "Devuelve los detalles de un equipo dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TeamDto.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crear un nuevo equipo", description = "Crea un nuevo equipo y lo guarda en la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@Valid @RequestBody TeamDto teamDto) {
        TeamDto createdTeam = teamService.createTeam(teamDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @Operation(summary = "Actualizar un equipo existente", description = "Actualiza los datos de un equipo dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamDto teamDto) {
        TeamDto updatedTeam = teamService.updateTeam(id, teamDto);
        if (updatedTeam != null) {
            return ResponseEntity.ok(updatedTeam);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Eliminar un equipo", description = "Elimina un equipo dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        if (teamService.getTeamById(id).isPresent()) {
            teamService.deleteTeam(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}