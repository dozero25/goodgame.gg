package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.Record.ChampionMasteryDto;
import fourjo.idle.goodgame.gg.web.dto.Record.LeagueDto;
import fourjo.idle.goodgame.gg.web.dto.Record.SummonerDto;
import fourjo.idle.goodgame.gg.web.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/record")
@Tag(name = "Record Api", description = "Record Api 입니다. 전적을 검색하면 해당 소환사의 정보가 나옵니다.")
public class RecordApi {

    @Autowired
    private RecordService recordService;

    @PostMapping("/searchSummonerName")
    @Operation(summary ="전적 검색", description = "SummonerName으로 검색합니다.")
    public ResponseEntity<CMRespDto<?>> SearchRecordAll(String summonerName){

        summonerName = summonerName.replaceAll(" ", "%20");
        SummonerDto summonerDto =  recordService.searchSummonerInfoBySummonerName(summonerName);
        List<String> matchesList = recordService.searchMatchesByMatchId(summonerDto.getPuuid());
        List<LeagueDto> leagueList = recordService.searchLeagueBySummonerName(summonerDto.getId());
        List<ChampionMasteryDto> championMasteryList = recordService.searchChampionMasteryByPuuid(summonerDto.getPuuid());

        System.out.println(summonerDto);
        System.out.println(leagueList);
        System.out.println(matchesList);
        System.out.println(championMasteryList);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully Search", championMasteryList));
    }



}
