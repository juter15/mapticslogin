package kr.co.maptics.mapticslogin.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapTicsLoginRequest {
    private String member_num;
    private String login_type;

}
