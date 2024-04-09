package fourjo.idle.goodgame.gg.web.dto.board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardPageDto {

    private int pageNum;
    private int amount;

    public BoardPageDto(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public BoardPageDto() {
        this(1,10);
    }

}
