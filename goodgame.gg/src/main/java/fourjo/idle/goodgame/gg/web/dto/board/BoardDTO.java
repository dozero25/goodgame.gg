package fourjo.idle.goodgame.gg.web.dto.board;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
public class BoardDTO {

    /*private int boardIndex;
    private String boardSubject;
    private int userIndex;
    private String boardContent;
    private LocalDateTime boardRegDate;
    private int boardVisit;
    private int boardGood;
    private int boardBad;
    private String boardUploadName;
    private BigInteger boardUploadSize;
    private String boardUploadTrans;

    /*=================================================*/

    private Long id;//글번호
    private String boardWriter;//작성자
    private String boardPass;//게시글 비밀번호
    private String boardTitle;//제목
    private String boardContents;//내용
    private int boardHits;//조회수
    private String createsAt;//작성시간

}
