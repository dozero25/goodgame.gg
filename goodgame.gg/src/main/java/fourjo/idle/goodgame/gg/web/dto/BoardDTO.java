package fourjo.idle.goodgame.gg.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private int board_index;
    private String board_subject;
    private int user_index; // 작성자 입력받으면 번호로 변환할 예정이신가봄
    private String board_content;
    private LocalDateTime board_reg_date;
    private int board_visit;
    private int board_good;
    private int board_bad;
    private String board_upload_name; //이름
    private Long board_upload_size; // 크기
    private String board_upload_trans; //위치

}
