package es.aballest.football.infrastructure.repository;

import es.aballest.football.domain.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query("SELECT c FROM Coach c WHERE c.team.id = :teamId")
    Coach findCoachByTeamId(@Param("teamId") int teamId);
}