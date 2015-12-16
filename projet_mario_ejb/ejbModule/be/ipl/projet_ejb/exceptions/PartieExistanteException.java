package be.ipl.projet_ejb.exceptions;

@SuppressWarnings("serial")
public class PartieExistanteException extends Exception {

	public PartieExistanteException() {
	}

	public PartieExistanteException(String message) {
		super(message);
	}

	public PartieExistanteException(Throwable cause) {
		super(cause);
	}

	public PartieExistanteException(String message, Throwable cause) {
		super(message, cause);
	}

	public PartieExistanteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
