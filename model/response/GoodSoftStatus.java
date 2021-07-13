package kr.co.maptics.mapticslogin.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GoodSoftStatus {

	private Integer status;
	private String code;
	private String message;
	private String developerMessage;
	private String moreInfo;
}
