package fourjo.idle.goodgame.gg.web.api;

import fourjo.idle.goodgame.gg.web.dto.CMRespDto;
import fourjo.idle.goodgame.gg.web.dto.Record.AccountDto;
import fourjo.idle.goodgame.gg.web.dto.Record.ChampionMasteryDto;
import fourjo.idle.goodgame.gg.web.dto.Record.LeagueDto;
import fourjo.idle.goodgame.gg.web.dto.Record.Matches.MatchDto;
import fourjo.idle.goodgame.gg.web.dto.Record.SummonerDto;
import fourjo.idle.goodgame.gg.web.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/searchSummonerName")
    @Operation(summary ="전적 검색", description = "gameName(String)과 tagLine(String)으로 검색합니다.")
    public ResponseEntity<CMRespDto<?>> SearchRecordAll(String gameName, String tagLine){

        gameName = gameName.replaceAll(" ", "%20");
        tagLine = tagLine.replaceAll(" ", "%20");

        AccountDto accountDto = recordService.searchSummonerInfoByGameNameAndTagLine(gameName, tagLine);
        SummonerDto summonerDto =  recordService.searchSummonerInfoByEncryptedPUUID(accountDto.getPuuid());
        List<String> matchesList = recordService.searchMatchesByPuuid(summonerDto.getPuuid());

        List<MatchDto> matchDtoList = new ArrayList<>();
        for (String index : matchesList){
            matchDtoList.add(recordService.searchMatchInfoByMatchId(index));
        }

        List<LeagueDto> leagueList = recordService.searchLeagueBySummonerName(summonerDto.getId());
        List<ChampionMasteryDto> championMasteryList = recordService.searchChampionMasteryByPuuid(summonerDto.getPuuid());

//        System.out.println(summonerDto);
//        System.out.println(matchesList);
//        System.out.println(matchDtoList);
//        System.out.println(leagueList);
//        System.out.println(championMasteryList);

        return ResponseEntity.ok()
                .body(new CMRespDto<>(HttpStatus.OK.value(), "Successfully Search", true));
    }



}
