package be.ipl.projet_ejb.exceptions;

@SuppressWarnings("serial")
public class PiocheVideException extends Exception {

	public PiocheVideException() {
	}

	public PiocheVideException(String arg0) {
		super(arg0);
	}

	public PiocheVideException(Throwable arg0) {
		super(arg0);
	}

	public PiocheVideException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PiocheVideException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
