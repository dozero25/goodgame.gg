package fourjo.idle.goodgame.gg.web.dto.admin;

import lombok.Data;

import java.util.Date;

@Data
public class ReplyDto {
    private int replyIndex;
    private int boardIndex;
    private int userId;
    private String replyContent;
    private int replyGroup;
    private int replySequence;
    private Date replyRegDate;

}
