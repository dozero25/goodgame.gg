package fourjo.idle.goodgame.gg.web.dto.Record.Matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectivesDto {

    private ObjectiveDto baron;
    private ObjectiveDto champion;
    private ObjectiveDto dragon;
    private ObjectiveDto inhibitor;
    private ObjectiveDto riftHerald;
    private ObjectiveDto tower;

}
