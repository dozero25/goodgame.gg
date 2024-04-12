package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.ranking.*;
import fourjo.idle.goodgame.gg.web.service.RankingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name ="RiotApi", description = "Riot Api 입니다.")
public class RankingApi {

    @Autowired
    private RankingService rankingService;



    @GetMapping("/ranking/SummonerV4BySummonerId")
    public ResponseEntity<CMRespDto<?>> summonerV4BySummonerId(String summonerId) {

        SummonerDto summonerDto = rankingService.summonerV4BySummonerId(summonerId);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", summonerDto));
    }

    @GetMapping("/ranking/AccountV1ByPuuid")
    public ResponseEntity<CMRespDto<?>> accountV1ByPuuid(String puuid) {

        AccountDto accountDto = rankingService.accountV1ByPuuid(puuid);


        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", accountDto));
    }

    @GetMapping("/ranking/challengerLeaguesByQueue")
    public ResponseEntity<CMRespDto<?>> challengerLeaguesByQueue(String queue) {

        LeagueListDto challengerRanking = rankingService.challengerLeaguesByQueue(queue);

        System.out.println(challengerRanking.getEntries().get(0).getSummonerId());
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", challengerRanking));
    }

    @GetMapping("/ranking/grandmasterLeaguesByQueue")
    public ResponseEntity<CMRespDto<?>> grandmasterLeaguesByQueue(String queue) {

        LeagueListDto grandmasterRanking = rankingService.grandmasterLeaguesByQueue(queue);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", grandmasterRanking));
    }

    @GetMapping("/ranking/masterLeaguesByQueue")
    public ResponseEntity<CMRespDto<?>> masterLeaguesByQueue(String queue) {

        LeagueListDto masterRanking = rankingService.masterLeaguesByQueue(queue);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", masterRanking));
    }

    @GetMapping("/ranking/entriesLeaguesBy4param")
    public ResponseEntity<CMRespDto<?>> entriesLeaguesBy4param(String tier, String division, String queue, int page) {

        List<LeagueEntryDto> entriesRanking = rankingService.entriesLeaguesBy4param(tier, division, queue, page);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", entriesRanking));
    }


    @GetMapping("/ranking/searchRankingList")
    public ResponseEntity<CMRespDto<?>> searchRankingList(RankingSearchDto rankingSearchDto) {

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", rankingService.searchRankingList(rankingSearchDto)));
    }





    //랭킹 업데이트 기능(DB insert)
    @PostMapping("/ranking/insertHighRankingAll")
    public ResponseEntity<CMRespDto<?>> insertHighRankingAll() {

        rankingService.truncateTable();

        String queue = "";
        LeagueListDto cgm = null;

        for (int j = 1; j<=2; j++) {
            if (j == 1) {
                queue = "solo";
            } else if (j == 2) {
                queue = "flex";
            }
            int count = 0;
            while (true) {
                count++;
                if (count == 1) {
                    cgm = rankingService.challengerLeaguesByQueue(queue);
                } else if (count == 2) {
                    cgm = rankingService.grandmasterLeaguesByQueue(queue);
                } else if (count == 3) {
                    break;
//                cgm = rankingService.masterLeaguesByQueue(queue);
                }

                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Insert... "+ "LeagueV4 Api " + queue + " " + cgm.getTier() + ":" + cgm.getEntries().size());
                //티어,큐별 유저 갯수
                for (int i = 0; i < 2; i++) {

                    int wins = cgm.getEntries().get(i).getWins();
                    int losses = cgm.getEntries().get(i).getLosses();
                    String winRate = Math.round((float) wins / (wins + losses) * 100) + "%";

                    Map<String, Object> insert = new LinkedHashMap<>();
                    insert.put("queueType", cgm.getQueue());
                    insert.put("tier", cgm.getTier());
                    insert.put("rank", cgm.getEntries().get(i).getRank());
                    insert.put("leaguePoints", cgm.getEntries().get(i).getLeaguePoints());
                    insert.put("wins", wins);
                    insert.put("losses", losses);
                    insert.put("winRate", winRate);
                    insert.put("summonerId", cgm.getEntries().get(i).getSummonerId());

                    rankingService.insertRankingLeagueV4(insert);
                }
            }
        }

        List<String> pullSummonerIdList = rankingService.pullSummonerIdList();
        for (int i = 0; i < pullSummonerIdList.size(); i++) {
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating... SummonerV4 Api " + queue + " " + cgm.getTier() + " RankingIndex : " + (i + 1));
            SummonerDto summonerDto = rankingService.summonerV4BySummonerId(pullSummonerIdList.get(i));

            Map<String, Object> update = new LinkedHashMap<>();
            update.put("rankingIndex", i + 1);
            update.put("summonerLevel", summonerDto.getSummonerLevel());
            update.put("profileIconId", summonerDto.getProfileIconId());
            update.put("puuid", summonerDto.getPuuid());

            rankingService.updateRankingSummonerV4(update);
        }


        List<String> pullPuuidList = rankingService.pullPuudList();
        for (int i = 0; i < pullPuuidList.size(); i++) {
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating... AccountV1 Api " + queue + " " + cgm.getTier() + " RankingIndex : " + (i + 1));
            AccountDto accountDto = rankingService.accountV1ByPuuid(pullPuuidList.get(i));

            Map<String, Object> update = new LinkedHashMap<>();
            update.put("rankingIndex", i + 1);
            update.put("gameName", accountDto.getGameName());
            update.put("tagLine", accountDto.getTagLine());

            rankingService.updateRankingAccountV1(update);
        }

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }

    @PostMapping("/ranking/insertEntriesRankingAll")
    public ResponseEntity<CMRespDto<?>> insertEntriesRankingAll() {
        rankingService.truncateLowTable();
        String[] tierArr = {"DIAMOND", "EMERALD", "PLATINUM", "GOLD", "SILVER", "BRONZE"};
        String tier = "";
        String division = "";
        String queue = "";

        for (int y = 1; y<=2; y++) {
            if (y == 1) {
                queue = "solo";
            } else if (y == 2) {
                queue = "flex";
            }

            for (int x = 0; x < 1; x++) {
                tier = tierArr[x];
                for (int j = 1; j < 2; j++) {
                    if (j == 1) {
                        division = "I";
                    } else if (j == 2) {
                        division = "II";
                    } else if (j == 3) {
                        division = "III";
                    } else {
                        division = "IV";
                    }

                    int page = 0;

                    while (true) {

                        page++;
                        List<LeagueEntryDto> entriesRanking = rankingService.entriesLeaguesBy4param(tier, division, queue, page);
//                        entriesRanking.isEmpty()
                        if (page==2) {
                            break;
                        }

                        System.out.println("Insert... " + "LeagueV4 Api " + queue + " " + tier + " " + division + " " + "Page:" + page + " , Size : " + entriesRanking.size());
                        for (int i = 0; i < entriesRanking.size(); i++) {
                            int wins = entriesRanking.get(i).getWins();
                            int losses = entriesRanking.get(i).getLosses();
                            String winRate = Math.round((float) wins / (wins + losses) * 100) + "%";

                            Map<String, Object> insert = new LinkedHashMap<>();
                            insert.put("queueType", entriesRanking.get(i).getQueueType());
                            insert.put("tier", entriesRanking.get(i).getTier());
                            insert.put("rank", entriesRanking.get(i).getRank());
                            insert.put("leaguePoints", entriesRanking.get(i).getLeaguePoints());
                            insert.put("wins", wins);
                            insert.put("losses", losses);
                            insert.put("winRate", winRate);
                            insert.put("summonerId", entriesRanking.get(i).getSummonerId());

                            rankingService.insertLowRankingLeagueV4(insert);

                        }
                    }
                }
            }
        }

        List<String> pullSummonerIdList = rankingService.pullLowSummonerIdList();
        for (int i = 0; i < pullSummonerIdList.size(); i++) {
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating... SummonerV4 Api RankingIndex : " + (i + 1));
            SummonerDto summonerDto = rankingService.summonerV4BySummonerId(pullSummonerIdList.get(i));

            Map<String, Object> update = new LinkedHashMap<>();
            update.put("rankingIndex", i + 1);
            update.put("summonerLevel", summonerDto.getSummonerLevel());
            update.put("profileIconId", summonerDto.getProfileIconId());
            update.put("puuid", summonerDto.getPuuid());

            rankingService.updateLowRankingSummonerV4(update);
        }


        List<String> pullPuuidList = rankingService.pullLowPuuidList();
        for (int i = 0; i < pullPuuidList.size(); i++) {
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating... AccountV1 Api RankingIndex : " + (i + 1));
            AccountDto accountDto = rankingService.accountV1ByPuuid(pullPuuidList.get(i));

            Map<String, Object> update = new LinkedHashMap<>();
            update.put("rankingIndex", i + 1);
            update.put("gameName", accountDto.getGameName());
            update.put("tagLine", accountDto.getTagLine());

            rankingService.updateLowRankingAccountV1(update);
        }
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }






}


