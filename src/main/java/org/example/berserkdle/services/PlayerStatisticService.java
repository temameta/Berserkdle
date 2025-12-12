package org.example.berserkdle.services;

import org.example.berserkdle.entities.PlayerStatistic;
import org.example.berserkdle.entities.User;
import org.example.berserkdle.repositories.PlayerStatisticRepository;
import org.example.berserkdle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class PlayerStatisticService {
    private PlayerStatisticRepository statisticRepository;
    private UserRepository userRepository;
    
    @Autowired
    public PlayerStatisticService(PlayerStatisticRepository statisticRepository, UserRepository userRepository) {
        this.statisticRepository = statisticRepository;
        this.userRepository = userRepository;
    }
    

    @Transactional
    public void recordGameResult(String username, boolean won, int attempts) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlayerStatistic stats = statisticRepository.findByUserId(user.getId())
                .orElseGet(() -> new PlayerStatistic(user));

        stats.addGameResult(won, attempts);
        statisticRepository.save(stats);
    }

    public PlayerStatistic getStatistics(String username) {
        return statisticRepository.findByUserUsername(username)
                .orElseGet(() -> {
                    User user = userRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not found"));
                    return new PlayerStatistic(user);
                });
    }

    // Метод для обновления статистики при угадывании персонажа
    @Transactional
    public void updateOnWin(String username, int attempts) {
        recordGameResult(username, true, attempts);
    }

    // Метод для добавления попытки (если нужно вести статистику по незавершенным играм)
    @Transactional
    public void addAttempt(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlayerStatistic stats = statisticRepository.findByUserId(user.getId())
                .orElseGet(() -> new PlayerStatistic(user));

        stats.setTotalAttempts(stats.getTotalAttempts() + 1);
        stats.setLastPlayed(LocalDateTime.now());
        statisticRepository.save(stats);
    }
}
