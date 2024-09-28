package br.com.metascale.core.exceptions;

@SuppressWarnings("serial")
public class NestedException extends VivoException {

	protected Exception exception;

	public NestedException() {
		this.exception = null;
	}

	public NestedException(String message, Exception exception) {
		super(message);
		this.exception = exception;
	}

	public NestedException(String message) {
		this(message, null);
	}

	public NestedException(Exception exeption) {
		this(null, exeption);
	}

	public Exception getExeption() {
		return exception;
	}

	public Exception getRootCause() {
		if (this.exception instanceof NestedException && this != exception) {
			return ((NestedException) exception).getRootCause();
		}
		return exception == null ? this : exception;
	}

	public String toString() {
		if (this.exception instanceof NestedException && this != exception) {
			return ((NestedException) exception).toString();
		}
		return exception == null ? super.toString() : exception.toString();
	}

}