package be.ipl.projet_ejb.exceptions;

@SuppressWarnings("serial")
public class MainCarteVideException extends Exception {

	public MainCarteVideException() {
	}

	public MainCarteVideException(String message) {
		super(message);
	}

	public MainCarteVideException(Throwable cause) {
		super(cause);
	}

	public MainCarteVideException(String message, Throwable cause) {
		super(message, cause);
	}

	public MainCarteVideException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
