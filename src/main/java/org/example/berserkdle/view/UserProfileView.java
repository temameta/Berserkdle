package org.example.berserkdle.view;

public class UserProfileView {
    private String username;

    private String email;

    private int gamesWon;

    private int totalAttempts;

    private int totalGamesPlayed;

    private double winRate;

    public UserProfileView() {
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public UserProfileView(String username, String email, int gamesWon, int totalAttempts, int totalGamesPlayed, double winRate) {
        this.username = username;
        this.email = email;
        this.gamesWon = gamesWon;
        this.totalAttempts = totalAttempts;
        this.totalGamesPlayed = totalGamesPlayed;
        this.winRate = winRate;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
