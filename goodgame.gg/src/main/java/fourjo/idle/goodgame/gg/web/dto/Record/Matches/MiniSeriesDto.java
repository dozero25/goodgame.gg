package fourjo.idle.goodgame.gg.web.dto.Record.Matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MiniSeriesDto {
    private int losses;
    private int progress;
    private int target;
    private int wins;
}
