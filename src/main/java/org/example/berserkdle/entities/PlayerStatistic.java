package org.example.berserkdle.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getGamesWon() { return gamesWon; }
    public void setGamesWon(int gamesWon) { this.gamesWon = gamesWon; }

    public int getTotalAttempts() { return totalAttempts; }
    public void setTotalAttempts(int totalAttempts) { this.totalAttempts = totalAttempts; }

    public int getTotalGamesPlayed() { return totalGamesPlayed; }
    public void setTotalGamesPlayed(int totalGamesPlayed) { this.totalGamesPlayed = totalGamesPlayed; }
}