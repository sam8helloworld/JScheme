package lisp.exception;

/**
 * 入力が尽きたことを表す例外
 * @author sam0830
 * @version 1.0
 */
@SuppressWarnings("serial")
public class EndOfFileException extends LispException{
	
	/**
	 * コンストラクタ
	 */
	public EndOfFileException() {
		super();
	}
	
	/**
	 * コンストラクタ
	 * @param msg エラーメッセージ
	 */
	public EndOfFileException(String msg) {
		super(msg);
	}
}
