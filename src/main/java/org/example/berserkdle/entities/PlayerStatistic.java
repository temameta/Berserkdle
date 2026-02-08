package org.example.berserkdle.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "player_statistics")
public class PlayerStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int gamesWon = 0;
    private int totalAttempts = 0;
    private int totalGamesPlayed = 0;

    public PlayerStatistic() {}

    public PlayerStatistic(User user) {
        this.user = user;
    }

    public void addGameResult(boolean won, int attempts) {
        this.totalGamesPlayed++;
        this.totalAttempts += attempts;

        if (won) {
            this.gamesWon++;
        }
    }

}