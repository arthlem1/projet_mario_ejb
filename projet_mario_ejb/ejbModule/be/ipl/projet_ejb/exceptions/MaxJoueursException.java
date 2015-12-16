package be.ipl.projet_ejb.exceptions;

@SuppressWarnings("serial")
public class MaxJoueursException extends Exception {

	public MaxJoueursException() {
	}

	public MaxJoueursException(String message) {
		super(message);
	}

	public MaxJoueursException(Throwable cause) {
		super(cause);
	}

	public MaxJoueursException(String message, Throwable cause) {
		super(message, cause);
	}

	public MaxJoueursException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
