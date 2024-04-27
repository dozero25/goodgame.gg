package fourjo.idle.goodgame.gg.web.dto.Record.Matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanDto {
    private int championId;
    private int pickTurn;
}
