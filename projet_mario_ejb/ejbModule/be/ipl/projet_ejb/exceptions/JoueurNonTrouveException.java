package be.ipl.projet_ejb.exceptions;

@SuppressWarnings("serial")
public class JoueurNonTrouveException extends Exception {

	public JoueurNonTrouveException() {
	}

	public JoueurNonTrouveException(String message) {
		super(message);
	}

	public JoueurNonTrouveException(Throwable cause) {
		super(cause);
	}

	public JoueurNonTrouveException(String message, Throwable cause) {
		super(message, cause);
	}

	public JoueurNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
