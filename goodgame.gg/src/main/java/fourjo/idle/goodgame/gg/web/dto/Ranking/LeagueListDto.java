package fourjo.idle.goodgame.gg.web.dto.ranking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueListDto {
    private String leagueId;
    private String tier;
    private String name;
    private String queue;
    private List<LeagueItemDto> entries;
}
