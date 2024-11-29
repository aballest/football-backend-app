package es.aballest.football.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Representación de un entrenador")
public class CoachDto {

    @Schema(description = "Identificador único del entrenador", example = "1")
    private Long id;

    @Schema(description = "Nombre del entrenador", example = "John")
    private String firstName;

    @Schema(description = "Apellido del entrenador", example = "Doe")
    private String lastName;

    @Schema(description = "Nombre completo del entrenador", example = "John Doe")
    private String fullName;

    @Schema(description = "Fecha de nacimiento del entrenador", example = "1980-01-01")
    private Date dateOfBirth;

    @Schema(description = "Nacionalidad del entrenador", example = "American")
    private String nationality;

    @Schema(description = "Equipo asociado al entrenador", example = "Team A")
    private TeamDto team;
}