package kr.co.maptics.mapticslogin.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponse {

	private Integer userNo;
	private Integer storeCode;
	private String authCode;
	private String userId;
	private String name;
	private String type;
	private String status;
	private String grade;

}
