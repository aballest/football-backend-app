package es.aballest.football.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Representación de un jugador")
public class PlayerDto {

    @Schema(description = "Identificador único del jugador", example = "1")
    private Long id;

    @Schema(description = "Nombre del jugador", example = "Lionel")
    private String firstName;

    @Schema(description = "Apellido del jugador", example = "Messi")
    private String lastName;

    @Schema(description = "Nombre completo del jugador", example = "Lionel Messi")
    private String fullName;

    @Schema(description = "Posición del jugador", example = "Forward")
    private String position;

    @Schema(description = "Fecha de nacimiento del jugador", example = "1987-06-24")
    private Date dateOfBirth;

    @Schema(description = "Nacionalidad del jugador", example = "Argentinian")
    private String nationality;

    @Schema(description = "Número de camiseta del jugador", example = "10")
    private Integer shirtNumber;

    @Schema(description = "Equipo del jugador", example = "PSG")
    private TeamDto team;
}