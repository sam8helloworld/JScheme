package lisp.exception;

/**
 * プログラムの誤りを表す例外
 * @author sam0830
 *
 */
@SuppressWarnings("serial")
public class LispException extends Exception {
	private static final String PREFIX_MESSAGE = "*** ERROR: ";
	LispException(String msg) {
		super(PREFIX_MESSAGE+msg);
	}
}
