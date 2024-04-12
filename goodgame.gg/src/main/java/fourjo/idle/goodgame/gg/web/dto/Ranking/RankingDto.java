package fourjo.idle.goodgame.gg.web.dto.Ranking;

import lombok.Data;

@Data
public class RankingDto {
    private int rankingIndex;
    private String gameName;
    private String tagLine;
    private int summonerLevel;
    private int profileIconId;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private String winRate;
}

