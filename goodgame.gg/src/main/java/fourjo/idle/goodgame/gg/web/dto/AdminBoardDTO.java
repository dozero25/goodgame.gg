package fourjo.idle.goodgame.gg.web.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class AdminBoardDTO {
    private int board_index;
    private String board_subject;
    private int user_index;
    private String board_content;
    private Date board_reg_date;
    private int board_visit;
    private int board_good;
    private int board_bad;
    private String board_upload_name;
    private Long board_upload_size;
    private String board_upload_trans;

}
