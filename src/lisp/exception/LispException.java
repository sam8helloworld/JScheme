package lisp.exception;

/**
 * プログラムの誤りを表す例外
 * @author sam0830
 *
 */
@SuppressWarnings("serial")
public class LispException extends Exception{
	LispException(String msg) {
		super(msg);
	}
}
