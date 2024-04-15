package fourjo.idle.goodgame.gg.web.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private int boardIndex;
    private String boardSubject;
    private int userIndex; // 작성자 입력받으면 번호로 변환할 예정이신가봄
    private String boardContent;
    private LocalDateTime boardRegDate;
    private int boardVisit;
    private String boardUploadName; //이름
    private Long boardUploadSize; // 크기
    private String boardUploadLocation; //위치
    private int replyCount; // 댓글수
    private String userNick; //유저



}
