package lisp.exception;

/**
 * プログラムの誤りを表す例外
 * @author tetsuya
 *
 */
@SuppressWarnings("serial")
public class LispException extends Exception{
	LispException(String msg) {
		super(msg);
	}
}
