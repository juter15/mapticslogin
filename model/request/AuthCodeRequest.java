package kr.co.maptics.mapticslogin.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AuthCodeRequest {

	private String authCode;

}
