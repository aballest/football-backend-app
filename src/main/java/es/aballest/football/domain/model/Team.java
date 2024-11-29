package es.aballest.football.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tla")
    private String tla;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "website")
    private String website;

    @Column(name = "founded")
    private Integer founded;
}