package kr.co.maptics.mapticslogin.exception;

import kr.co.maptics.mapticslogin.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@ControllerAdvice
public class ErrorControllerAdviser {


	@ExceptionHandler(EncryptException.class)
	public ResponseEntity encryptError(EncryptException e) {
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse()
						.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMessage(e.getMessage())
						.setTime(LocalDateTime.now()));
	}


	/**
	 * Entity를 찾을 수 없는 경우
	 */
	@ExceptionHandler(NotFoundEntityException.class)
	public ResponseEntity notFoundEntity(NotFoundEntityException e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse()
						.setStatus(HttpStatus.NOT_FOUND)
						.setMessage(e.getMessage())
						.setTime(LocalDateTime.now()));
	}


	/**
	 * 파리미터 정보가 올바르지 않은 경우
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity parameterNotValid(MethodArgumentNotValidException e) {
		List<String> collect = e.getBindingResult().getAllErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(toList());

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse()
						.setStatus(HttpStatus.BAD_REQUEST)
						.setMessage(collect.toString())
						.setTime(LocalDateTime.now()));
	}


	/**
	 * 필수 Request Header 정보가 없는 경우
	 */
/*	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity missingRequestHeader(MissingRequestHeaderException e) {
		log.warn("Missing Request Header.", e);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse()
						.setStatus(HttpStatus.BAD_REQUEST)
						.setMessage("Missing Request Header.")
						.setTime(LocalDateTime.now()));
	}*/


	/**
	 * Http Request Body 정보가 없는 경우
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity messageNoReadable(HttpMessageNotReadableException e) {
		log.warn("Message no readable.", e);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse()
						.setStatus(HttpStatus.BAD_REQUEST)
						.setMessage("Message no readable.")
						.setTime(LocalDateTime.now()));
	}


	/**
	 * 그 외 모든 Exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity exceptionHandler(Exception e) {
		log.warn("Exception", e);

		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse()
						.setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
						.setMessage("알 수 없는 오류가 발생했습니다.")
						.setTime(LocalDateTime.now()));
	}
}
