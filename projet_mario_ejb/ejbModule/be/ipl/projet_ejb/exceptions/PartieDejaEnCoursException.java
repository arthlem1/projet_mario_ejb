package be.ipl.projet_ejb.exceptions;

@SuppressWarnings("serial")
public class PartieDejaEnCoursException extends Exception {

	public PartieDejaEnCoursException() {
	}

	public PartieDejaEnCoursException(String message) {
		super(message);
	}

	public PartieDejaEnCoursException(Throwable cause) {
		super(cause);
	}

	public PartieDejaEnCoursException(String message, Throwable cause) {
		super(message, cause);
	}

	public PartieDejaEnCoursException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
