package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fourjo.idle.goodgame.gg.exception.CustomRiotResponseCodeException;
import fourjo.idle.goodgame.gg.web.dto.Record.AccountDto;
import fourjo.idle.goodgame.gg.web.dto.Record.ChampionMasteryDto;
import fourjo.idle.goodgame.gg.web.dto.Record.LeagueDto;
import fourjo.idle.goodgame.gg.web.dto.Record.Matches.MatchDto;
import fourjo.idle.goodgame.gg.web.dto.Record.SummonerDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RecordService {

    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

    private final String mykey = "RGAPI-329e15cd-a0c4-49ff-9a61-afe89ef6b887";
    private final String serverUrl = "https://kr.api.riotgames.com";
    private final String severUrlAsia = "https://asia.api.riotgames.com/";
    private final HttpClient c = HttpClientBuilder.create().build();

    // 게임 이름 + 태그라인 검색
    public AccountDto searchSummonerInfoByGameNameAndTagLine(String gameName, String tagLine){
        AccountDto accountDto = new AccountDto();
        try {
            HttpGet request = new HttpGet(severUrlAsia + "/riot/account/v1/accounts/by-riot-id/" + gameName+"/"+tagLine+ "?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            accountDto = objectMapper.readValue(entity.getContent(), AccountDto.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return accountDto;
    }

    // encryptedPUUID로 조회
    public SummonerDto searchSummonerInfoByEncryptedPUUID(String encryptedPUUID){
        SummonerDto summonerDto = new SummonerDto();
        try {
            HttpGet request = new HttpGet(serverUrl + "/lol/summoner/v4/summoners/by-puuid/" + encryptedPUUID + "?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            summonerDto = objectMapper.readValue(entity.getContent(), SummonerDto.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return summonerDto;
    }

    // puuid로 게임정보 조회
    public List<String> searchMatchesByPuuid (String puuid){
        List<String> matchesList = new ArrayList<>();

        try {
            HttpGet request = new HttpGet(severUrlAsia + "/lol/match/v5/matches/by-puuid/"+puuid+"/ids" + "?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();

            matchesList = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchesList;
    }

    // 해당 게임 정보 가져오기
    public MatchDto searchMatchInfoByMatchId (String matchId){
        MatchDto matchDto = new MatchDto();

        try {
            HttpGet request = new HttpGet(severUrlAsia + "/lol/match/v5/matches/"+matchId+"?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();

            matchDto = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchDto;
    }

    // 리그정보 조회 - 랭크별, 티어별, 등급별, 소환사 정보 반환
    public List<LeagueDto> searchLeagueBySummonerName(String enCodeSummonerName){
        List<LeagueDto> leagueList = new ArrayList<LeagueDto>();
        try {
            HttpGet request = new HttpGet(serverUrl + "/lol/league/v4/entries/by-summoner/" + enCodeSummonerName + "?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            leagueList = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leagueList;
    }

    //CHAMPION-MASTERY-V4 - 유저, 챔피언 숙련도 조회, puuid로 검색 후 소환사의 챔피언 숙련도 정보 반환
    public List<ChampionMasteryDto> searchChampionMasteryByPuuid(String puuid){
        List<ChampionMasteryDto> championMasteryList = new ArrayList<>();

        try {
            HttpGet request = new HttpGet(serverUrl + "/lol/champion-mastery/v4/champion-masteries/by-puuid/" + puuid + "?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            championMasteryList = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});

            // Dto에 Comparable를 implements 하지 않고 사용
//            championMasteryList.sort(Comparator.comparing(ChampionMasteryDto::getChampionPointsUntilNextLevel).reversed());

        } catch (IOException e){
            e.printStackTrace();
        }
        return championMasteryList;
    }

    // 에러 메서드
    public void riotResponseCodeError(HttpResponse response){
        int code = response.getStatusLine().getStatusCode();
        if(code != 200){
            Map<String, Integer> errorMap = new HashMap<>();
            errorMap.put("Riot Response Code", code);

            throw new CustomRiotResponseCodeException(errorMap);
        }
    }

}
