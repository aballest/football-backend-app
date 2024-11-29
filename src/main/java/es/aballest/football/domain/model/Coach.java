package es.aballest.football.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "coach")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = true)
    private Team team;
}