package es.aballest.football.infrastructure.webservice;

import es.aballest.football.application.dto.CoachDto;
import es.aballest.football.application.service.CoachService;
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
@RequestMapping("/coaches")
@Tag(name = "Coach API", description = "API para la gestión de entrenadores")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Operation(summary = "Obtener todos los entrenadores", description = "Devuelve una lista de todos los entrenadores.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito")
    @GetMapping
    public ResponseEntity<List<CoachDto>> getAllCoaches() {
        List<CoachDto> coaches = coachService.getAllCoaches();
        return ResponseEntity.ok(coaches);
    }

    @Operation(summary = "Obtener un entrenador por ID", description = "Devuelve los detalles de un entrenador dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrenador encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CoachDto.class))),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CoachDto> getCoachById(@PathVariable Long id) {
        return coachService.getCoachById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crear un nuevo entrenador", description = "Crea un nuevo entrenador y lo guarda en la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Entrenador creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<CoachDto> createCoach(@Valid @RequestBody CoachDto coachDto) {
        CoachDto createdCoach = coachService.createCoach(coachDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoach);
    }

    @Operation(summary = "Actualizar un entrenador existente", description = "Actualiza los datos de un entrenador dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrenador actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CoachDto> updateCoach(@PathVariable Long id, @Valid @RequestBody CoachDto coachDto) {
        CoachDto updatedCoach = coachService.updateCoach(id, coachDto);
        if (updatedCoach != null) {
            return ResponseEntity.ok(updatedCoach);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Eliminar un entrenador", description = "Elimina un entrenador dado su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrenador eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        if (coachService.getCoachById(id).isPresent()) {
            coachService.deleteCoach(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Obtener entrenador por ID de equipo", description = "Devuelve los detalles de un entrenador dado el ID de su equipo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entrenador encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CoachDto.class))),
            @ApiResponse(responseCode = "404", description = "Entrenador no encontrado")
    })
    @GetMapping("/team/{teamId}")
    public ResponseEntity<CoachDto> getCoachByTeamId(@PathVariable Long teamId) {
        return coachService.getCoachByTeamId(teamId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}