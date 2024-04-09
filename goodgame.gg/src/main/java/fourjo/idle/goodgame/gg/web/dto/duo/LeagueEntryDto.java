package fourjo.idle.goodgame.gg.web.dto.duo;

import lombok.Data;

@Data
public class LeagueEntryDto {

    private String leagueId;
    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losers;
    private boolean hotStreak;
    private boolean veteran;
    private boolean freshBlood;
    private boolean inactive;
}
