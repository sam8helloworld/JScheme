package lisp.exception;

/**
 * プログラムの誤りを表す例外
 * @author sam0830
 * @version 1.0
 */
@SuppressWarnings("serial")
public class LispException extends Exception {
	private static final String PREFIX_MESSAGE = "*** ERROR: ";
	
	/**
	 * コンストラクタ
	 * @param msg エラーメッセージ
	 */
	LispException(String msg) {
		super(PREFIX_MESSAGE+msg);
	}
	
	/**
	 * コンストラクタ
	 * エラーメッセージを出力しないエラー
	 */
	LispException() {
		super("");
	}
}
