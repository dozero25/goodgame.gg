package fourjo.idle.goodgame.gg.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private int replyIndex;
    private int boardIndex;
    private int userId;
    private String replyContent;
    private int replyFir;
    private int replySec;
    private LocalDateTime relyRegDate;

}
