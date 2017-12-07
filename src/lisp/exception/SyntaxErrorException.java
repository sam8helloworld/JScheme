package lisp.exception;

/**
 * 構文上の誤りを表す例外
 * @author tetsuya
 *
 */
@SuppressWarnings("serial")
public class SyntaxErrorException extends LispException{
	private static final String prefix = "Syntax Error";
	public SyntaxErrorException(String msg) {
		super(prefix + ":" + msg);
	}
}
