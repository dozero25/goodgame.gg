package fourjo.idle.goodgame.gg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDtl {
    private int roleDtlId;
    private int empIndex;
    private int roleId;

    private RoleMst roleMst;
}
