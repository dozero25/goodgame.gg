package fourjo.idle.goodgame.gg.web.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
@Tag(name ="RankingApi", description = "Ranking정보를 관리 및 출력하는 Api 입니다.")
public class RankingApi {
}
