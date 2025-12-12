package org.example.berserkdle.repositories;

import org.example.berserkdle.entities.PlayerStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PlayerStatisticRepository extends JpaRepository<PlayerStatistic, Long> {
    Optional<PlayerStatistic> findByUserId(String user_id);
    Optional<PlayerStatistic> findByUserUsername(String username);
}
