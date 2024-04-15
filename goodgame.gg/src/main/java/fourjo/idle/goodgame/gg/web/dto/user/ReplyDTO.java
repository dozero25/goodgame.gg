package fourjo.idle.goodgame.gg.web.dto.user;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ReplyDTO {

    private int replyIndex;
    private int boardIndex;
    private int userIndex;
    private String replyContent;
    private int replyGroup;
    private int replySequence;
    private LocalDateTime replyRegDate;

}
