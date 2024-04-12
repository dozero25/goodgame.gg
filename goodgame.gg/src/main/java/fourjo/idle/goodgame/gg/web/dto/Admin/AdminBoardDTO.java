package fourjo.idle.goodgame.gg.web.dto.admin;

import lombok.Data;

import java.util.Date;

@Data
public class AdminBoardDTO {
    private int boardIndex;
    private String boardSubject;
    private int userIndex;
    private String boardContent;
    private Date boardRegDate;
    private int boardVisit;
    private String boardUploadName;
    private Long boardUploadSize;
    private String boardUploadLocation;

}
