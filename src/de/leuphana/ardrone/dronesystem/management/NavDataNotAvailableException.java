/**
 * 
 */
package de.leuphana.ardrone.dronesystem.management;

/**
 * @author Florian Quadt
 * 
 */
public class NavDataNotAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5377627735876868239L;

	/**
	 * 
	 */
	public NavDataNotAvailableException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NavDataNotAvailableException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NavDataNotAvailableException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NavDataNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

}
