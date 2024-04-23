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

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
@Tag(name ="Ranking Api", description = "랭킹 관련 Api 입니다.")
public class RankingApi {

    @Autowired
    private RankingService rankingService;



//    @GetMapping("/summonerV4BySummonerId")
//    public ResponseEntity<CMRespDto<?>> summonerV4BySummonerId(String summonerId) {
//
//        SummonerDto summonerDto = rankingService.summonerV4BySummonerId(summonerId);
//
//        return ResponseEntity.ok()
//                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", summonerDto));
//    }
//
    @GetMapping("/accountV1ByPuuid")
    public ResponseEntity<CMRespDto<?>> accountV1ByPuuid(String puuid) {

        AccountDto accountDto = rankingService.accountV1ByPuuid(puuid);


        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", accountDto));
    }


//
//    @GetMapping("/challengerLeaguesByQueue")
//    public ResponseEntity<CMRespDto<?>> challengerLeaguesByQueue(String queue) {
//
//        LeagueListDto challengerRanking = rankingService.challengerLeaguesByQueue(queue);
//
//        System.out.println(challengerRanking.getEntries().get(0).getSummonerId());
//        return ResponseEntity.ok()
//                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", challengerRanking));
//    }
//
//    @GetMapping("/grandmasterLeaguesByQueue")
//    public ResponseEntity<CMRespDto<?>> grandmasterLeaguesByQueue(String queue) {
//
//        LeagueListDto grandmasterRanking = rankingService.grandmasterLeaguesByQueue(queue);
//
//        return ResponseEntity.ok()
//                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", grandmasterRanking));
//    }
//
//    @GetMapping("/masterLeaguesByQueue")
//    public ResponseEntity<CMRespDto<?>> masterLeaguesByQueue(String queue) {
//
//        LeagueListDto masterRanking = rankingService.masterLeaguesByQueue(queue);
//
//        return ResponseEntity.ok()
//                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", masterRanking));
//    }
//
//    @GetMapping("/entriesLeaguesBy4param")
//    public ResponseEntity<CMRespDto<?>> entriesLeaguesBy4param(String tier, String division, String queue, int page) {
//
//        List<LeagueEntryDto> entriesRanking = rankingService.entriesLeaguesBy4param(tier, division, queue, page);
//
//        return ResponseEntity.ok()
//                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", entriesRanking));
//    }








    //검색 및 전체 리스트
    @GetMapping("/allList")
    public ResponseEntity<CMRespDto<?>> getRankingList(RankingSearchDto rankingSearchDto) {

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", rankingService.getRankingList(rankingSearchDto)));
    }

    //페이징용 토탈 카운트
    @GetMapping("/totalCount")
    public ResponseEntity<CMRespDto<?>> getRankingTotalCount(RankingSearchDto rankingSearchDto) {

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", rankingService.getRankingTotalCount(rankingSearchDto)));
    }


    //DB내 존재하는 소환사만 검색가능
    @GetMapping("/checkNick")
    public ResponseEntity<CMRespDto<?>> checkNick(String summoner) {

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", rankingService.checkNick(summoner)));
    }



    //상위랭킹 업데이트 기능(DB insert)
    @PostMapping("/insertHighRankingAll")
    public ResponseEntity<CMRespDto<?>> insertHighRankingAll() {
        rankingService.truncateTable();
        System.out.println("TruncateTable...highrank_mst");


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
                    cgm = rankingService.masterLeaguesByQueue(queue);
                }else {
                    break;
                }

                try {
                    Thread.sleep(1100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Insert... "+ "LeagueV4 Api " + queue + " " + cgm.getTier() + ":" + cgm.getEntries().size());
                //티어,큐별 유저 갯수
                for (int i = 0; i < cgm.getEntries().size(); i++) {
                    RankingDto insert = new RankingDto();
                    int wins = cgm.getEntries().get(i).getWins();
                    int losses = cgm.getEntries().get(i).getLosses();
                    String winRate = Math.round((float) wins / (wins + losses) * 100) + "%";


                    insert.setQueueType(cgm.getQueue());
                    insert.setTier(cgm.getTier());
                    insert.setRankValue(cgm.getEntries().get(i).getRank());
                    insert.setLeaguePoints(cgm.getEntries().get(i).getLeaguePoints());
                    insert.setWins(wins);
                    insert.setLosses(losses);
                    insert.setWinRate(winRate);
                    insert.setSummonerId(cgm.getEntries().get(i).getSummonerId());


                    rankingService.insertRankingLeagueV4(insert);
                }
            }
        }

        List<String> pullSummonerIdList = rankingService.pullSummonerIdList();
        for (int i = 0; i < pullSummonerIdList.size(); i++) {
            try {
                Thread.sleep(280);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating... SummonerV4 Api RankingIndex : " + (i + 1));
            SummonerDto summonerDto = rankingService.summonerV4BySummonerId(pullSummonerIdList.get(i));

            RankingDto update = new RankingDto();
            update.setRankingIndex(i + 1);
            update.setSummonerLevel(summonerDto.getSummonerLevel());
            update.setProfileIconId(summonerDto.getProfileIconId());
            update.setPuuid(summonerDto.getPuuid());

            rankingService.updateRankingSummonerV4(update);
        }


        List<String> pullPuuidList = rankingService.pullPuudList();
        for (int i = 0; i < pullPuuidList.size(); i++) {
            try {
                Thread.sleep(280);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Updating... AccountV1 Api RankingIndex : " + (i + 1));
            AccountDto accountDto = rankingService.accountV1ByPuuid(pullPuuidList.get(i));

            RankingDto update = new RankingDto();
            update.setRankingIndex(i + 1);
            update.setGameName(accountDto.getGameName());
            update.setTagLine(accountDto.getTagLine());

            rankingService.updateRankingAccountV1(update);
        }

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }


    //하위랭킹 업데이트 기능(DB insert)
    @PostMapping("/insertEntriesRankingAll")
    public ResponseEntity<CMRespDto<?>> insertEntriesRankingAll() {

        rankingService.truncateLowTable();
        System.out.println("TruncateTable...lowrank_mst");

        String[] tierArr = {"DIAMOND", "EMERALD", "PLATINUM", "GOLD", "SILVER", "BRONZE"};
        String tier = "";
        String division = "";
        String queue = "";

        for (int y = 1; y <=2; y++) {
            if (y == 1) {
                queue = "solo";
            } else if (y == 2) {
                queue = "flex";
            }

            for (int x = 0; x <= 5; x++) {
                tier = tierArr[x];
                for (int j = 1; j <= 4; j++) {
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
                        if (entriesRanking.isEmpty()) {
                            break;
                        }

                        System.out.println("Insert... " + "LeagueV4 Api " + queue + " " + tier + " " + division + " " + "Page:" + page + " , Size : " + entriesRanking.size());
                        for (int i = 0; i < entriesRanking.size(); i++) {
                            RankingDto insert = new RankingDto();
                            int wins = entriesRanking.get(i).getWins();
                            int losses = entriesRanking.get(i).getLosses();
                            String winRate = Math.round((float) wins / (wins + losses) * 100) + "%";


                            insert.setQueueType(entriesRanking.get(i).getQueueType());
                            insert.setTier(entriesRanking.get(i).getTier());
                            insert.setRankValue(entriesRanking.get(i).getRank());
                            insert.setLeaguePoints(entriesRanking.get(i).getLeaguePoints());
                            insert.setWins(wins);
                            insert.setLosses(losses);
                            insert.setWinRate(winRate);
                            insert.setSummonerId(entriesRanking.get(i).getSummonerId());


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

            RankingDto update = new RankingDto();
            update.setRankingIndex(i + 1);
            update.setSummonerLevel(summonerDto.getSummonerLevel());
            update.setProfileIconId(summonerDto.getProfileIconId());
            update.setPuuid(summonerDto.getPuuid());


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

            RankingDto update = new RankingDto();
            update.setRankingIndex(i + 1);
            update.setGameName(accountDto.getGameName());
            update.setTagLine(accountDto.getTagLine());

            rankingService.updateLowRankingAccountV1(update);
        }
        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
    }









}


