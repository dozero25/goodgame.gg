package fourjo.idle.goodgame.gg.web.dto.Ranking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueItemDto implements Comparable<LeagueItemDto> {
    private String summonerId;
    private String summonerName;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;


    @Override
    public int compareTo(LeagueItemDto o) {
        return (o.leaguePoints - leaguePoints);
    }
}
