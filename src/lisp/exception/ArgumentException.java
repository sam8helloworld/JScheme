package lisp.exception;

/**
 * 予期せぬ引数を表す例外
 * @author sam0830
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ArgumentException extends LispException {
	/**
	 * コンストラクタ
	 * @param msg エラーメッセージ
	 */
	public ArgumentException(String msg) {
		super(msg);
	}
}
