package kr.co.maptics.mapticslogin.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MapTicsLoginResponse {

    private String code;
    private String message;
    private String request_id;

    private String loginResult;

    @JsonProperty("data")
    MapTicsLoginResponseData mapTicsLoginResponseData;
}
