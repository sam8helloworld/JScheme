package lisp.exception;

/**
 * 予期せぬ引数を表す例外
 * @author sam0830
 *
 */
@SuppressWarnings("serial")
public class ArgumentException extends LispException {
	public ArgumentException(String msg) {
		super(msg);
	}
}
