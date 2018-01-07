package lisp.exception;

/**
 * 環境に束縛されていないエラー
 * @author sam0830
 * @version 1.0
 */
@SuppressWarnings("serial")
public class UnboundException extends LispException {
	/**
	 * コンストラクタ
	 * @param msg エラーメッセージ
	 */
	public UnboundException(String msg) {
		super(msg);
	}
}
