package kr.co.maptics.mapticslogin.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class GoodSoftResponse<T> {
	
	private GoodSoftStatus status;
	private T body;
}
