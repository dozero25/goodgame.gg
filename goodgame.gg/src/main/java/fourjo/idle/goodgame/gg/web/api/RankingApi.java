package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.Ranking.LeagueEntryDto;
import fourjo.idle.goodgame.gg.web.dto.Ranking.LeagueListDto;
import fourjo.idle.goodgame.gg.web.dto.Ranking.SummonerDto;
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

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/riot")
@Tag(name ="Riot Api", description = "Riot Api 입니다.")
public class RankingApi {

    @Autowired
    private RankingService rankingService;

    @GetMapping("/search/summonerV4")
    public ResponseEntity<CMRespDto<?>> searchSummonerInfoBySummonerName (String summonerName){

        summonerName = summonerName.replaceAll(" ", "%20");
        SummonerDto summonerDto = rankingService.searchSummonerInfoBySummonerName(summonerName);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", summonerDto));
    }

    @GetMapping("/search/leagueV4")
    public ResponseEntity<CMRespDto<?>> searchLeagueById (String encryptedSummonerId){

        List<LeagueEntryDto> leagueInfo = rankingService.searchLeagueById(encryptedSummonerId);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", leagueInfo));
    }


    @GetMapping("/search/summonerProfile")
    public ResponseEntity<CMRespDto<?>> searchSummonerProfileBySummonerName (String summonerName){
        summonerName = summonerName.replaceAll(" ", "%20");
        SummonerDto summonerDto = rankingService.searchSummonerInfoBySummonerName(summonerName);
        List<LeagueEntryDto> leagueInfo = rankingService.searchLeagueById(summonerDto.getId());
        List<Object> result = new ArrayList<>();
        result.add(summonerDto);
        result.add(leagueInfo);



        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", result));
    }


    @GetMapping("/ranking/challengerLeagues")
    public ResponseEntity<CMRespDto<?>> listChallengerLeaguesByQueue (String queue){

        LeagueListDto challengerRanking = rankingService.challengerLeaguesByQueue(queue);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", challengerRanking));
    }

    @GetMapping("/ranking/grandmasterLeagues")
    public ResponseEntity<CMRespDto<?>> listGrandmasterLeaguesByQueue (String queue){

        LeagueListDto grandmasterRanking = rankingService.grandmasterLeaguesByQueue(queue);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", grandmasterRanking));
    }

    @GetMapping("/ranking/masterLeagues")
    public ResponseEntity<CMRespDto<?>> listMasterLeaguesByQueue (String queue){

        LeagueListDto masterRanking = rankingService.masterLeaguesByQueue(queue);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", masterRanking));
    }

    @GetMapping("/ranking/entriesLeagues")
    public ResponseEntity<CMRespDto<?>> listEntriesLeagues (String tier, String division,String queue, int page){

        List<LeagueEntryDto> entriesRanking = rankingService.entriesLeagues(tier,division,queue,page);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", entriesRanking));
    }


    @PostMapping("/ranking/DBInsert")
    public ResponseEntity<CMRespDto<?>> rankingInsert (String tier, String queue) {
        String division = "";
        for (int j = 1; j<=4; j++) {
            if(j==1){
                division = "I";
            }else if (j==2){
                division = "II";
            } else if (j==3) {
                division = "III";
            }else{
                division= "IV";
            }
            int page = 0;
            while (true) {

                page++;
                List<LeagueEntryDto> entriesRanking = rankingService.entriesLeagues(tier, division, queue, page);
                System.out.println(entriesRanking);
                if (page==2) {
                    break;
                }

                for (int i = 0; i < entriesRanking.size(); i++) {
                    rankingService.rankingInsert(entriesRanking.get(i));
                }

            }
        }

            return ResponseEntity.ok()
                    .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully registered", true));
        }



}
