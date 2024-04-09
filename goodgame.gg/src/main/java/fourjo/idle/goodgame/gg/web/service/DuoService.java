package fourjo.idle.goodgame.gg.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fourjo.idle.goodgame.gg.repository.DuoRepository;
import fourjo.idle.goodgame.gg.web.dto.duo.DuoDto;
import fourjo.idle.goodgame.gg.web.dto.duo.DuoSearchDto;
import fourjo.idle.goodgame.gg.web.dto.duo.SummonerDto;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DuoService {

    @Autowired
    DuoRepository duoRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    private final String myKey = "RGAPI-78400056-e673-440b-ac3b-04b85f9e97af";
    private final String serverUrl = "https://kr.api.riotgames.com/";
    private final String serverAsia = "https://asia.api.riotgames.com/";

    public SummonerDto searchEncryptedIdBySummonerName(String summonerName) {
        SummonerDto summonerDto = new SummonerDto();
        try {

            HttpClient c = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + myKey);
            HttpResponse response = c.execute(request);

            // riotResponseCodeError(response);

            HttpEntity entity = response.getEntity();
            summonerDto = objectMapper.readValue(entity.getContent(), SummonerDto.class);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return summonerDto;
    }
    public int duoInsert(DuoDto duoDto)
    {
        return duoRepository.duoInsert(duoDto);
    }
    public List<DuoDto> duoSearchByQueAndTierAndPosition(DuoSearchDto duoSearchDto)
    {
        return duoRepository.duoSearchByQueAndTierAndPosition(duoSearchDto);
    }

}
