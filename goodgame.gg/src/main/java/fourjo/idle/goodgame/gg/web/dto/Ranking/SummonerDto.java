package fourjo.idle.goodgame.gg.web.dto.Ranking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SummonerDto {
//    private String accountId;
//    private long revisionDate;
    private String id;
    private String puuid;
    private long summonerLevel;
    private int profileIconId;
    private String name;


}
