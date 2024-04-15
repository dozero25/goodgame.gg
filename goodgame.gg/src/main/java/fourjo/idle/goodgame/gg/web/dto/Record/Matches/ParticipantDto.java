package fourjo.idle.goodgame.gg.web.dto.Record.Matches;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantDto {

    // 어시스트 수
    private int assists;
    // 바론 킬
    private int baronKills;
    // 현상금 레 벨
    private int bountyLevel;
    // 챔피언 경험치
    private int champExperience;
    // 챔피언 레벨
    private int champLevel;
    // 챔피언 아이디
    private int championId;
    // 챔피언 이름
    private String championName;

    private int championTransform;

    private int consumablesPurchased;
    // 간측믈 딜
    private int damageDealtToBuildings;
    // 오브젝트 딜
    private int damageDealtToObjectives;
    // 타워딜
    private int damageDealtToTurrets;

    private int damageSelfMitigated;
    // 죽은 수
    private int deaths;

    private int detectorWardsPlaced;
    // 더블킬
    private int doubleKills;
    // 드래곤 킬
    private int dragonKills;

    // 퍼블 어시
    private boolean firstBloodAssist;
    // 퍼블
    private boolean firstBloodKill;
    // 타워 퍼블 어시스트
    private boolean firstTowerAssist;
    // 타워 퍼블
    private boolean firstTowerKill;
    // 15서렌인지 아닌지
    private boolean gameEndedInEarlySurrender;
    // 서렌인지 아닌지
    private boolean gameEndedInSurrender;
    // 획득 골드
    private int goldEarned;
    // 골드 사용
    private int goldSpent;

    // 개인 포티션
    private String individualPosition;
    // 억제기
    private int inhibitorKills;
    private int inhibitorTakedowns;
    private int inhibitorsLost;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int itemsPurchased;

    // 킬링 스프리 횟수
    private int killingSprees;
    // 킬
    private int kills;

    private String lane;
    private int largestCriticalStrike;
    private int largestKillingSpree;
    private int largestMultiKill;
    private int longestTimeSpentLiving;

    // AP 딜
    private int magicDamageDealt;
    // 챔프한테 가한 AP딜
    private int magicDamageDealtToChampions;
    private int magicDamageTaken;

    private int neutralMinionsKilled;
    private int nexusKills;
    private int nexusTakedowns;
    private int nexusLost;

    private int objectivesStolen;
    private int objectivesStolenAssists;

    private int participantId;
    // 펜타킬 유무
    private int pentaKills;
    private PerksDto perks;

    // AD 딜
    private int physicalDamageDealt;
    // 챔프한테 가한 AD딜
    private int physicalDamageDealtToChampions;
    private int physicalDamageTaken;
    private int playerAugment1;
    private int playerAugment2;
    private int playerAugment3;
    private int playerAugment4;
    private int playerSubteamId;
    private int profileIcon;
    private String puuid;

    // 쿼드라 킬
    private int quadraKills;

    private String riotIdName;
    private String riotIdTagline;
    private String role;

    private int sightWardsBoughtInGame;
    private int spell1Casts;
    private int spell2Casts;
    private int spell3Casts;
    private int spell4Casts;
    private int summoner1Casts;
    private int summoner1Id;
    private int summoner2Casts;
    private int summoner2Id;
    private String summonerId;
    private int summonerLevel;
    private String summonerName;

    private boolean teamEarlySurrendered;
    private int teamId;
    private String teamPosition;
    private int timeCCingOthers;
    private int timePlayed;
    private int totalDamageDealt;
    private int totalDamageDealtToChampions;
    private int totalDamageShieldedOnTeammates;
    // 받은 피해
    private int totalDamageTaken;
    // 전체 회복
    private int totalHeal;
    private int totalHealsOnTeammates;
    private int totalMinionsKilled;
    private int totalTimeCCDealt;
    private int totalTimeSpentDead;
    private int totalUnitsHealed;
    private int tripleKills;
    private int trueDamageDealt	;
    private int trueDamageDealtToChampions;
    private int trueDamageTaken;
    private int turretKills;
    private int turretTakedowns;
    private int turretsLost;

    private int unrealKills;

    private int visionScore;
    private int visionWardsBoughtInGame;

    private int wardsKilled;
    private int wardsPlaced;

    private String win;


}
