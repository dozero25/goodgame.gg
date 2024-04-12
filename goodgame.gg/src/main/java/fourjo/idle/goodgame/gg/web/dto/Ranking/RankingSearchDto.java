package fourjo.idle.goodgame.gg.web.dto.Ranking;

import lombok.Data;

@Data
public class RankingSearchDto {


    private String queueType;

    private String tier;

    private String gameName;

    private String tagLine;

    private String limit;

    private int index;

    private int count;

    private int page;

}
