package fourjo.idle.goodgame.gg.web.dto.admin;

import lombok.Data;

@Data
public class AdminUserSearchDTO {

int page;

    private String searchKey;
    private String searchValue;

    private String limit;

    private int count;

    private int index;

   // public void setIndex() {
  //      index = (page-1) * count;
 //   }


}
