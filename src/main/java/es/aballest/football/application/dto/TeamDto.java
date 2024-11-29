package es.aballest.football.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Representación de un equipo")
public class TeamDto {

    @Schema(description = "Identificador único del equipo", example = "1")
    private Long id;

    @Schema(description = "Nombre del equipo", example = "Real Madrid")
    private String name;

    @Schema(description = "Abreviatura del equipo", example = "RMD")
    private String tla;

    @Schema(description = "Estadio del equipo", example = "Santiago Bernabeu")
    private String stadium;

    @Schema(description = "Sitio web del equipo", example = "http://www.realmadrid.com")
    private String website;

    @Schema(description = "A�o de fundación del equipo", example = "1902")
    private Integer founded;
}