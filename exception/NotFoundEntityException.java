package kr.co.maptics.mapticslogin.exception;

public class NotFoundEntityException extends RuntimeException {

	private static final long serialVersionUID = -6588686893256482820L;

	public NotFoundEntityException(String message) {
		super(message);
	}
}
