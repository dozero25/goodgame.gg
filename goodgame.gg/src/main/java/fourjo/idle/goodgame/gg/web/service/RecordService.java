package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import fourjo.idle.goodgame.gg.exception.CustomRiotResponseCodeException;
import fourjo.idle.goodgame.gg.web.dto.Record.ChampionMasteryDto;
import fourjo.idle.goodgame.gg.web.dto.Record.LeagueDto;
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

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String mykey = "RGAPI-e5b95bf4-abf3-498a-8ec6-271e47567574";
    private final String serverUrl = "https://kr.api.riotgames.com";
    private final String severUrlAsia = "https://asia.api.riotgames.com/";

    // 소한사 이름으로 검색
    public SummonerDto searchSummonerInfoBySummonerName(String summonerName){
        SummonerDto summonerDto = new SummonerDto();
        try {
            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + mykey);
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
    public List<String> searchMatchesByMatchId (String puuid){
        List<String> matchesList = new ArrayList<>();

        try {
            HttpClient c = HttpClientBuilder.create().build();
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

    // 리그정보 조회 - 랭크별, 티어별, 등급별, 소환사 정보 반환
    public List<LeagueDto> searchLeagueBySummonerName(String enCodeSummonerName){
        List<LeagueDto> leagueList = new ArrayList<LeagueDto>();
        try {
            HttpClient c = HttpClientBuilder.create().build();
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
            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/lol/champion-mastery/v4/champion-masteries/by-puuid/" + puuid + "?api_key=" + mykey);
            HttpResponse response = c.execute(request);

            riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            championMasteryList = objectMapper.readValue(entity.getContent(), new TypeReference<>() {});

            Collections.sort(championMasteryList);

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
