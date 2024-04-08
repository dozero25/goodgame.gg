package fourjo.idle.goodgame.gg.web.dto.admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private int replyIndex;
    private int boardIndex;
    private int userId;
    private String replyContent;
    private int replyGroup;
    private int replySequence;
    private LocalDateTime relyRegDate;

}
