package lisp.exception;

/**
 * 環境に束縛されていないエラー
 * @author sam0830
 *
 */
@SuppressWarnings("serial")
public class UnboundException extends LispException {
	public UnboundException(String msg) {
		super(msg);
	}
}
