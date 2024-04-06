package fourjo.idle.goodgame.gg.web.dto.Record;

import lombok.Data;

@Data
public class ChampionMasteryDto implements Comparable<ChampionMasteryDto>{
    private String puuid;
    private long championPointsUntilNextLevel;
    private boolean chestGranted;
    private long championId;
    private long lastPlayTime;
    private int championLevel;
    private String summonerId;
    private int championPoints;
    private long championPointsSinceLastLevel;
    private int tokensEarned;

    @Override
    public int compareTo(ChampionMasteryDto o) {
        return (int) ( o.championPointsUntilNextLevel- championPointsUntilNextLevel );
    }
}
