package fourjo.idle.goodgame.gg.web.dto.Record;

import lombok.Data;

import java.util.List;

@Data
public class TeamDto {
    private List<BanDto> bans;
    private ObjectivesDto objectivesDto;
    private int teamId;
    private boolean win;
}
