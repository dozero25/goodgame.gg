package fourjo.idle.goodgame.gg.web.dto;

import lombok.Data;
@Data
public class AdminUserSelectAll {



        private int page;

        private String searchValue;

        private String limit;

        private int count;

        private int index;

        public void setIndex() {
            index = (page-1) * count;
        }

    }


