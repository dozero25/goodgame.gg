package fourjo.idle.goodgame.gg.web.dto.board;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BoardReplyDTO{

    private int replyIndex; //id
    private int boardIndex; //bnum
    private int userIndex;
    private String userId;
    private String replyContent;
    private int replyGroup; //댓글 reference
    private int replySequence; //대댓글 step, level
    private LocalDateTime replyRegDate;
}
