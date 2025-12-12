package org.example.berserkdle.controllers.mvc.user;

import org.example.berserkdle.entities.PlayerStatistic;
import org.example.berserkdle.services.PlayerStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticController {
    private PlayerStatisticService statisticService;

    @Autowired
    public StatisticController(PlayerStatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping
    public String getStatistics(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        PlayerStatistic stats = statisticService.getStatistics(username);

        model.addAttribute("statistics", stats);
        model.addAttribute("averageAttemptsToWin", stats.getAverageAttemptsToWin());

        // Дополнительная статистика
        model.addAttribute("winRate",
                stats.getTotalGamesPlayed() > 0 ?
                        (double) stats.getGamesWon() / stats.getTotalGamesPlayed() * 100 : 0);

        return "user/statistic";
    }
}
