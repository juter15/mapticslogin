package kr.co.maptics.mapticslogin.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorResponse {

	private HttpStatus status;
	private String message;
	private LocalDateTime time;
}
