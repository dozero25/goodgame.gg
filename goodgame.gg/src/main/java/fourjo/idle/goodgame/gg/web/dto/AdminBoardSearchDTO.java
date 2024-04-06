package fourjo.idle.goodgame.gg.web.dto;

import lombok.Data;

@Data
public class AdminBoardSearchDTO {
    private int page;

    private String searchKey;

    private String searchValue;

    private String limit;

    private int count;

    private int index;
}
