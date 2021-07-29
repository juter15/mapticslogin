package kr.co.maptics.mapticslogin.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MapTicsLoginResponseData {
    private String token;
    private String url;
    private String ttl;
}
