package kr.co.maptics.mapticslogin.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapTicsLoginResponseData {
    private String token;
    private String url;

}
