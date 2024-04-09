package fourjo.idle.goodgame.gg.web.dto.Ranking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueEntryDto implements Comparable<LeagueEntryDto>{
//    private String leagueId;
//    private String summonerId;
    private String summonerName;
    private String queueType;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;

    @Override
    public int compareTo(LeagueEntryDto o) {
        return (o.leaguePoints - leaguePoints);
    }
}
