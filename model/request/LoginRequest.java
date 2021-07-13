package kr.co.maptics.mapticslogin.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginRequest {

	private String id;
	private String password;
	private String os;

}
