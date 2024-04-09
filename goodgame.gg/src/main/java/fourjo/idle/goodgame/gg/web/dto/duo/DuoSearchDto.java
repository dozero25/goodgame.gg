package fourjo.idle.goodgame.gg.web.dto.duo;

import lombok.Data;

@Data
public class DuoSearchDto {

    private String searchKey;

    private String searchQueValue;
    private String searchPositionValue;
    private String searchTierValue;

    private String limit;

    private int count;

    private int index;

}
