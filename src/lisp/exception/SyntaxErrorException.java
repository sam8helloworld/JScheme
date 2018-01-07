package lisp.exception;

/**
 * 構文上の誤りを表す例外
 * @author sam0830
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SyntaxErrorException extends LispException {
	private static final String prefix = "Syntax Error";
	
	/**
	 * コンストラクタ
	 * @param msg エラーメッセージ
	 */
	public SyntaxErrorException(String msg) {
		super(prefix + ":" + msg);
	}
}
